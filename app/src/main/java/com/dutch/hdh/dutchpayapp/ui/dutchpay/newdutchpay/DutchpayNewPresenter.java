package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpay;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.DutchpayNewListAdapter;
import com.dutch.hdh.dutchpayapp.adapter.Listview_MyGroupAdapter;
import com.dutch.hdh.dutchpayapp.data.db.Dutchpayhistory;
import com.dutch.hdh.dutchpayapp.data.db.MyGroup;
import com.dutch.hdh.dutchpayapp.databinding.ItemDutchpayNewMemberBinding;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpayinfo.DutchpayNewInfoFragment;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DutchpayNewPresenter implements DutchpayNewContract.Presenter {

    private DutchpayNewContract.View mView;

    private ObservableArrayList<TempNewListModel> mNewList;
    private DutchpayNewListAdapter mAdapter;

    private String oldCost;
    private int myCost;
    private int lastCost;
    private int lastMem;

    public DutchpayNewPresenter(DutchpayNewContract.View mView) {
        this.mView = mView;
        this.mNewList = new ObservableArrayList<>();
        this.mAdapter = new DutchpayNewListAdapter(mNewList,this);
        this.oldCost = "";
        this.myCost = 0;
        this.lastCost = -1;
        this.lastMem = -1;
    }

    @Override
    public void listInit() {

        //더미 데이터 셋
        mNewList.add(new TempNewListModel("박소영","0","010-1111-2222",false));
        mNewList.add(new TempNewListModel("최윤미","0","010-3333-4444",false));
        mNewList.add(new TempNewListModel("박현주","0","010-5555-6666",false));
        mNewList.add(new TempNewListModel("","","",false));

        //binding
        mView.adapterInit();
    }

    @Override
    public void checkCost(String newcost) {
        if(oldCost.equals("")){ //금액 미입력 확인
            oldCost = "0";
        } else if( !(oldCost.equals(newcost)) ){ //금액 변동 확인
            oldCost = newcost;
            Log.e("change --> ",newcost+"");

            if(mNewList.size() >0) { //리스트 존재 여부 확인
                dutchpayLogic();
            }
        }
    }

    public int getMyCost() {
        return myCost;
    }

    @Override
    public boolean dutchpayLogic() {
        checkCost(oldCost); //금액 미입력 방지

        int cost = Integer.parseInt(oldCost);
        int memcount = mNewList.size();

        if(cost < memcount){ //최소값 체크
            return false;
        } else {
            int memCost = cost/memcount;
            myCost = cost - (memCost*(memcount-1));

            for(int i = 0; i<memcount; i++){
                mNewList.get(i).setCost(String.valueOf(memCost));
            }

            mAdapter.setItem(mNewList);

            mNewList.get(mNewList.size()-1).setCost(String.valueOf(myCost));
            mView.setMyCost(String.valueOf(myCost));
            return true;
        }
    }

    @Override
    public void reDutchpayLogic() {
        int payedcost = 0;
        int cost = Integer.parseInt(oldCost);

        Log.e("Logic ->","start");

        //더치할 멤버_포지션 리스트 생성
        ArrayList<Integer> dutchList = new ArrayList<>();
        for(int i=0; i<mNewList.size(); i++){
            if(Integer.parseInt(mNewList.get(i).getCost()) == cost){
                mNewList.get(i).setCost("0");
                dutchList.add(i);
            }

            if( !(mNewList.get(i).isEditedCheck()) ){
                dutchList.add(i);

                Log.e("dutch ->",dutchList.toString());
            } else {
                int inputcost = 0;
                if( (!mNewList.get(i).getCost().equals("")) ){
                    inputcost = Integer.parseInt(mNewList.get(i).getCost());
                    if(inputcost >= cost){
                        inputcost = cost;

                        for(int j=0; j< mNewList.size();j++){
                            Log.e("setEditedCheck",j+"");
                            mNewList.get(j).setCost("0");
                            mNewList.get(j).setEditedCheck(false);
                        }
                        mNewList.get(i).setCost(String.valueOf(cost));
                    }
                }
               payedcost = payedcost + inputcost;
            }
        }

        if(dutchList.size() == 1){
            lastCost = Integer.parseInt( mNewList.get(dutchList.get(0)).getCost());
            lastMem = mNewList.indexOf(mNewList.get(dutchList.get(0)));
        }

        int newcost = cost - payedcost;
        if(newcost < 0){
            newcost = 0;
        }

        int newmemcount = dutchList.size();
        if(newmemcount != 0){
            int memCost = newcost / (newmemcount);
            myCost = newcost - (memCost * (newmemcount - 1));

            if(dutchList.contains(mNewList.size()-1)){ //방장에게 남은 돈 몰아주기

                for (int i = 0; i < dutchList.size(); i++) {
                    mNewList.get(dutchList.get(i)).setCost(String.valueOf(memCost));
                }
                mNewList.get(mNewList.size()-1).setCost(String.valueOf(myCost));
            } else { //더치 멤버중 마지막 사람에게 남은 돈 몰아주기
                for (int i = 0; i < dutchList.size()-1; i++) {
                    mNewList.get(dutchList.get(i)).setCost(String.valueOf(memCost));
                }
                mNewList.get(dutchList.get(dutchList.size()-1)).setCost(String.valueOf(myCost));
            }
            mView.setMyCost(String.valueOf(mNewList.get(mNewList.size()-1).getCost()));
        } else {
            mNewList.get(lastMem).setCost(String.valueOf(lastCost));
        }
    }

    @Override
    public void changeMyCost(String cost) {
        //값 변동 확인

    }

    public ObservableArrayList<TempNewListModel> getmNewList() {
        return mNewList;
    }

    public DutchpayNewListAdapter getmAdapter() {
        return mAdapter;
    }

    @BindingAdapter("item")
    public static void bindItem(RecyclerView view, ObservableArrayList<TempNewListModel> list){
        DutchpayNewListAdapter adapter = (DutchpayNewListAdapter) view.getAdapter();
        if(adapter != null) {
            adapter.setItem(list);
        }
    }

    public void onNextClick(){
        mNewList.remove(mNewList.size()-1); //버튼용_더미 데이터 삭제

        Bundle bundle = new Bundle(1);
        Gson gson = new Gson();
        String jList = gson.toJson(mNewList);
        bundle.putString("JList",jList);

        FragmentManager fm = mView.getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

        DutchpayNewInfoFragment dutchpayNewInfoFragment = new DutchpayNewInfoFragment();
        dutchpayNewInfoFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.flFragmentContainer,dutchpayNewInfoFragment, DutchpayNewInfoFragment.class.getName());
        fragmentTransaction.addToBackStack(DutchpayNewInfoFragment.class.getName());
        fragmentTransaction.commit();
    }

    public void onDutchClick(){
        mView.setDutchBtColor(true);
        mView.setTypeBtColor(false);

        //1/n값으로 초기화
        dutchpayLogic();

        //리스트 내 금액 클릭 금지
        memCostEditable(false);
        mView.setMyCostEditable(false);
    }

    public void onTypingClick(){
        mView.setDutchBtColor(false);
        mView.setTypeBtColor(true);

        //리스트 내 금액 클릭 가능
        memCostEditable(true);
        mView.setMyCostEditable(true);
    }

    public void phoneListCallClick(){

    }

    public void groupListCallClick(){

    }

    public void makeListClick(){
        listInit();
    }

    public void setOldCost(String oldCost) {
        this.oldCost = oldCost;
    }

    private void memCostEditable(boolean flag){

        for(int i = 0; i<mNewList.size(); i++){
            mNewList.get(i).setEditableFlag(flag);
        }
        mAdapter.setItem(mNewList);
    }
}
