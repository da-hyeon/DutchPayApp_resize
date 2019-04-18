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
import com.dutch.hdh.dutchpayapp.data.db.DirectInputParticipants;
import com.dutch.hdh.dutchpayapp.data.db.Dutchpayhistory;
import com.dutch.hdh.dutchpayapp.data.db.GroupParticipants;
import com.dutch.hdh.dutchpayapp.data.db.MyGroup;
import com.dutch.hdh.dutchpayapp.data.db.TelephoneDirectory;
import com.dutch.hdh.dutchpayapp.databinding.ItemDutchpayNewMemberBinding;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchaddgroup.DutchpayNewAddGroupFragment;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchaddgroup.DutchpayNewAddGroupModel;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpayinfo.DutchpayNewInfoFragment;
import com.dutch.hdh.dutchpayapp.ui.mygroup.directinput.MyGroup_DirectInputFragment;
import com.dutch.hdh.dutchpayapp.ui.mygroup.main.MyGroup_MainFragment;
import com.dutch.hdh.dutchpayapp.ui.mygroup.telephonedirectory.MyGroup_TelephoneDirectoryFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DutchpayNewPresenter implements DutchpayNewContract.Presenter {

    private DutchpayNewContract.View mView;

    private ObservableArrayList<TempNewListModel> mNewList;
    private DutchpayNewListAdapter mAdapter;
    private MyApplication mMyApplication;

    private String oldCost;
    private int myCost;
    private int lastCost;
    private int lastMem;
    private boolean dutchFlag = true;
    private Gson gson;

    public DutchpayNewPresenter(DutchpayNewContract.View mView) {
        this.mView = mView;
        this.mNewList = new ObservableArrayList<>();
        this.mAdapter = new DutchpayNewListAdapter(mNewList,this);
        this.mMyApplication = MyApplication.getInstance();
        this.oldCost = "";
        this.myCost = 0;
        this.lastCost = -1;
        this.lastMem = -1;
        this.gson = new Gson();
    }

    @Override
    public void listInit(Bundle bundle) {

        if( !(bundle.getString("dutchpayListData").equals("")) ) { //이전 리스트 유무 체크
            mNewList.clear();
            String oldList = bundle.getString("dutchpayListData");

            List<TempNewListModel> oldlist = gson.fromJson(oldList, new TypeToken<List<TempNewListModel>>(){}.getType());
            for(TempNewListModel model : oldlist){
                if( !(model.getName().equals("")) ) {
                    mNewList.add(new TempNewListModel(model.getName(),model.getPhone(),model.getCost(),model.isCompleteFlag(),model.isEditableFlag(),model.editedCheck));
                }
            }
        }

        if(bundle.getParcelableArrayList("InputMember") != null) { //직접 추가 리스트 유무 체크
            ArrayList<DirectInputParticipants> directInputParticipantsArrayList = bundle.getParcelableArrayList("InputMember");
            String tmpJson = gson.toJson(directInputParticipantsArrayList);

            List<DirectInputParticipants> list = gson.fromJson(tmpJson, new TypeToken<List<DirectInputParticipants>>() {}.getType());
            for (DirectInputParticipants model : list) {
                mNewList.add(new TempNewListModel(model.getName(), model.getPhoneNumber()));
            }

        }

        if(bundle.getString("InputGroupMember") != null){ //그룹 추가 리스트 유무 체크
            List<DutchpayNewAddGroupModel> list = gson.fromJson(bundle.getString("InputGroupMember"),new TypeToken<List<DutchpayNewAddGroupModel>>(){}.getType());
            for(DutchpayNewAddGroupModel model : list) {
                List<GroupParticipants> members = gson.fromJson(model.getGmember(),new TypeToken<List<GroupParticipants>>(){}.getType());
                for(GroupParticipants member : members) {
                    mNewList.add(new TempNewListModel(member.getName(),member.getPhoneNumber()));
                }
            }
        }

        if(bundle.getParcelableArrayList("telephoneInputMember") != null){ //연락처 추가 리스트 유무 체크
            ArrayList<TelephoneDirectory> telephoneDirectories = bundle.getParcelableArrayList("telephoneInputMember");
            String tmpJson = gson.toJson(telephoneDirectories);

            List<TelephoneDirectory> list = gson.fromJson(tmpJson, new TypeToken<List<TelephoneDirectory>>() {}.getType());
            for (TelephoneDirectory model : list) {
                mNewList.add(new TempNewListModel(model.getName(), model.getPhoneNumber()));
            }
        }

        if(mNewList.size() != 0){ //다음 버튼 생성용
            mNewList.add(new TempNewListModel("", ""));
        }

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

        if(mNewList.size() != 0) { //리스트 여부 확인
            int memcount = mNewList.size();
            if (cost < memcount) { //최소값 체크
                return false;
            } else {
                int memCost = cost / memcount;
                myCost = cost - (memCost * (memcount - 1));

                for (int i = 0; i < memcount; i++) {
                    mNewList.get(i).setEditedCheck(false);
                    mNewList.get(i).setCost(String.valueOf(memCost));
                }

                mNewList.get(mNewList.size() - 1).setCost(String.valueOf(myCost));
                mView.setMyCost(String.valueOf(myCost));
                mView.setMyCostEditable(false);
                return true;
            }
        }
        return false;
    }

    @Override
    public void reDutchpayLogic() {
        int payedcost = 0;
        int cost = Integer.parseInt(oldCost);

        //더치할 멤버_포지션 리스트 생성
        ArrayList<Integer> dutchList = new ArrayList<>();
        for(int i=0; i<mNewList.size(); i++){
            if( !(mNewList.get(i).getCost().equals(""))) { //오류 보정_멤버 중 더치페이 금액 달성자가 있을시 변동 유무 플래그 초기화
                if (Integer.parseInt(mNewList.get(i).getCost()) == cost) {
                    mNewList.get(i).setCost("0");
                    dutchList.add(i);
                }
            }
            if(i == mNewList.size()-1){ //오류 보정_1/n버튼 클릭시 변동 유무 플래그 초기화
                if(dutchFlag){ dutchList.add(i); }
            }

            //더치페이 계산
            if( !(mNewList.get(i).isEditedCheck()) ){
                if(!(dutchList.contains(i))) { //오류 보정_삭제시에 리스트업 중복 방지
                    dutchList.add(i);
                }
                Log.e("dutchList ->",dutchList.toString());
            } else {
                int inputcost = 0;
                if( (!mNewList.get(i).getCost().equals("")) ){
                    inputcost = Integer.parseInt(mNewList.get(i).getCost());
                    if(inputcost >= cost){
                        inputcost = cost;

                        for(int j=0; j< mNewList.size();j++){
                            mNewList.get(j).setCost("0");
                            mNewList.get(j).setEditedCheck(false);
                        }
                        mNewList.get(i).setCost(String.valueOf(cost));
                    }
                }
               payedcost = payedcost + inputcost;
            }
        }

        int newcost = cost - payedcost;
        if(newcost < 0){ newcost = 0; }

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

            if(dutchList.size() == 1){ //더치페이 마지막 멤버의 변화값 저장
                lastCost = Integer.parseInt( mNewList.get(dutchList.get(0)).getCost());
                lastMem = mNewList.indexOf(mNewList.get(dutchList.get(0)));
            }
        } else { //금액 변동 가능한 더치페이 멤버가 없음_값 변동 금지
            if(mNewList.size() == 1){ //완전 삭제 보정
                lastCost = Integer.parseInt(oldCost);
            }

            if (lastMem == mNewList.size() - 1) {
                mView.setMyCost(String.valueOf(lastCost));
            } else {
                mNewList.get(lastMem).setCost(String.valueOf(lastCost));
            }

        }

    }

    @Override
    public void notifyListRemoved() {
        for(int i=0; i<mNewList.size(); i++){
            if(mNewList.get(i).isEditedCheck()){
                if(i == mNewList.size()-1){ //변동 가능 멤버가 없을시, 내 금액 변동
                    mNewList.get(i).setEditedCheck(false);
                }
            } else {
                break;
            }
        }

        reDutchpayLogic();
        mAdapter.notifyDataSetChanged();
        mView.setMemCount(mNewList.size());

        if(mNewList.size() == 1){ //모든 더치멤버 삭제시, 내 금액 변동 금지
            mView.setMyCostEditable(false);
        }

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
        Bundle bundle = new Bundle();
        String jList = gson.toJson(mNewList);
        bundle.putString("oldList",jList);
        bundle.putString("total",oldCost);

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
        dutchFlag = true;

        mView.setDutchBtColor(true);
        mView.setTypeBtColor(false);

        //1/n값으로 초기화
        dutchpayLogic();

        //리스트 내 금액 클릭 금지
        listEditableSet(dutchFlag);
    }

    public void onTypingClick(){
        dutchFlag = false;

        mView.setDutchBtColor(false);
        mView.setTypeBtColor(true);

        //리스트 내 금액 클릭 가능
        listEditableSet(dutchFlag);
        //예외처리
        solopayCheck();
    }

    public void phoneListCallClick(){
        mMyApplication.setDutchpayGroup(true);

        Bundle bundle = makeListBundle();

        //연락처 페이지로 이동
        moveTelephoneDirectoryFragment(bundle);
    }

    public void groupListCallClick(){
        mMyApplication.setDutchpayGroup(true);
        //리스트 초기화
        mNewList.clear();
        Bundle bundle = makeListBundle();

        //그룹 입력페이지로 이동
        moveGroupAddFragment(bundle);
    }

    public void makeListClick(){
        mMyApplication.setDutchpayGroup(true);
        Bundle bundle = makeListBundle();

        //직접 입력페이지로 이동
        moveDirectInputFramgent(bundle);
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

    public void listEditableSet(boolean dutchflag){

        if(dutchflag){ //리스트 내 금액 클릭 금지
            memCostEditable(false);
            mView.setMyCostEditable(false);
            dutchFlag = true;
        } else { //리스트 내 금액 클릭 가능
            memCostEditable(true);
            mView.setMyCostEditable(true);
            dutchFlag = false;
        }
    }

    public void solopayCheck(){
        if(mNewList.size() <= 1){
            mView.setMyCostEditable(false);
        }
    }

    public MyApplication getmMyApplication() {
        return mMyApplication;
    }

    /**
     * 뒤로가기 이벤트 처리
     */
    @Override
    public void clickBackPressed() {
        mMyApplication.setDutchpayGroup(false);
        mMyApplication.setDutchpayNewFragment(null);

        FragmentManager fm = mView.getFragmentManager();
        fm.popBackStack();
    }

    private void moveDirectInputFramgent(Bundle bundle){
        FragmentManager fm = mView.getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

        MyGroup_DirectInputFragment myGroup_directInputFragment = new MyGroup_DirectInputFragment();
        myGroup_directInputFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.flFragmentContainer,myGroup_directInputFragment, MyGroup_DirectInputFragment.class.getName());
        fragmentTransaction.addToBackStack(MyGroup_DirectInputFragment.class.getName());
        fragmentTransaction.commit();
    }

    private void moveGroupAddFragment(Bundle bundle){
        FragmentManager fm = mView.getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

        DutchpayNewAddGroupFragment dutchpayNewAddGroupFragment = new DutchpayNewAddGroupFragment();
        dutchpayNewAddGroupFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.flFragmentContainer,dutchpayNewAddGroupFragment, MyGroup_DirectInputFragment.class.getName());
        fragmentTransaction.addToBackStack(MyGroup_DirectInputFragment.class.getName());
        fragmentTransaction.commit();
    }

    private void moveTelephoneDirectoryFragment(Bundle bundle){
        FragmentManager fm = mView.getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

        MyGroup_TelephoneDirectoryFragment myGroup_telephoneDirectoryFragment = new MyGroup_TelephoneDirectoryFragment();
        myGroup_telephoneDirectoryFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.flFragmentContainer,myGroup_telephoneDirectoryFragment, MyGroup_DirectInputFragment.class.getName());
        fragmentTransaction.addToBackStack(MyGroup_DirectInputFragment.class.getName());
        fragmentTransaction.commit();
    }

    private Bundle makeListBundle(){
        Bundle bundle = new Bundle(1);

        if(mNewList.size() != 0) {
            String oldList = gson.toJson(mNewList);
            bundle.putString("dutchpayListData",oldList); //이전 리스트 저장
        } else {
            bundle.putString("dutchpayListData",""); //저장할 리스트 없음
        }

        return bundle;
    }

    public boolean isDutchFlag() {
        return dutchFlag;
    }
}
