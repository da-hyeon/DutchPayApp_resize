package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpay;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.DutchpayNewListAdapter;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpayinfo.DutchpayNewInfoFragment;
import com.google.gson.Gson;

public class DutchpayNewPresenter implements DutchpayNewContract.Presenter {

    private DutchpayNewContract.View mView;
    private ObservableArrayList<TempNewListModel> mNewList;
    private DutchpayNewListAdapter mAdapter;

    private String oldCost;
    private int myCost;

    public DutchpayNewPresenter(DutchpayNewContract.View mView) {
        this.mView = mView;
        this.mNewList = new ObservableArrayList<>();
        this.mAdapter = new DutchpayNewListAdapter(mNewList,this);
        this.oldCost = "";
        this.myCost = 0;
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
        if( !(oldCost.equals(newcost)) ){ //금액 변동 확인
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
            mView.setMyCost(String.valueOf(myCost));

            return true;
        }
    }

    @Override
    public void reDutchpayLogic(TempNewListModel item) {
        int cost = Integer.parseInt(oldCost);
        int inputcost = 0;
        if( (!item.getCost().equals("")) ){
            inputcost = Integer.parseInt(item.getCost());
        }

        int newcost = cost - inputcost;

        int newmemcount = mNewList.size()-1;

        int memCost = newcost/newmemcount;
        myCost = newcost - (memCost*(newmemcount-1));

        for(int i = 0; i<newmemcount; i++){
            mNewList.get(i).setCost(String.valueOf(memCost));
        }

        mAdapter.setItem(mNewList);
        mView.setMyCost(String.valueOf(myCost));

    }

    public ObservableArrayList<TempNewListModel> getmNewList() {
        return mNewList;
    }

    public DutchpayNewListAdapter getmAdapter() {
        return mAdapter;
    }

    @BindingAdapter("bind:item")
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
    }

    public void onTypingClick(){
        mView.setDutchBtColor(false);
        mView.setTypeBtColor(true);

        //리스트 내 금액 클릭 가능
        memCostEditable(true);
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
