package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchaddgroup;

import android.databinding.BindingConversion;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.adapter.DutchpayNewGroupListAdapter;
import com.dutch.hdh.dutchpayapp.data.db.GroupList;
import com.dutch.hdh.dutchpayapp.data.db.MyGroup;
import com.google.gson.Gson;
import com.kinda.alert.KAlertDialog;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

public class DutchpayNewAddGroupPresenter implements DutchpayNewAddGroupContract.Presenter {

    DutchpayNewAddGroupContract.View mView;

    private MyApplication mMyApplication;

    private ObservableArrayList<DutchpayNewAddGroupModel> mGList;
    private DutchpayNewGroupListAdapter mAdapter;

    public ObservableField<String> memCount = new ObservableField<>();
    private Bundle mBundle;


    public DutchpayNewAddGroupPresenter(DutchpayNewAddGroupContract.View mView,Bundle bundle) {
        this.mView = mView;
        this.mGList = new ObservableArrayList<>();
        this.mAdapter = new DutchpayNewGroupListAdapter(mGList,this);
        this.mMyApplication = MyApplication.getInstance();
        this.mBundle = bundle;
        this.memCount.set("0");
    }

    public ObservableArrayList<DutchpayNewAddGroupModel> getGList() {
        return mGList;
    }

    public DutchpayNewGroupListAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void listInit() {
        //그룹불러오기
        Call<MyGroup> getGroupList2 = MyApplication
                .getRestAdapter()
                .getGroupList2(mMyApplication.getUserInfo().getUserCode());

        getGroupList2.enqueue(new Callback<MyGroup>() {
            @Override
            public void onResponse(Call<MyGroup> call, Response<MyGroup> response) {
                if(response.isSuccessful()) {
                    MyGroup myGroup = response.body();

                    if (myGroup != null) {
                        ArrayList<GroupList> list =  myGroup.getGroupLists();

                        for(int i=0; i<list.size(); i++){
                            GroupList item = list.get(i);
                            int icon = i%6;
                            Log.e("check ->",item.getGroup_content_from_json());
                            mGList.add(new DutchpayNewAddGroupModel(item.getGroupName(),icon,item.getParticipantsCount()-1,item.getGroup_content_from_json()));
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<MyGroup> call, @NonNull Throwable t) {
                Log.e("fail",t.getMessage());
            }
        });

        mAdapter.notifyDataSetChanged();

        //binding
        mView.adapterInit();
    }

    public void addMemCount(int count){
        int memcount = Integer.parseInt(memCount.get());
        memcount += count;
        memCount.set(memcount+"");
    }

    @Override
    public void onPlusClick() {
        Bundle bundle = makeBundle();

        mMyApplication.getDutchpayNewFragment().setArguments(bundle);

        //현재 프래그먼트 제거
        FragmentManager fm = mView.getFragmentManager();
        fm.popBackStack();
    }

    private Bundle makeBundle(){
        Bundle bundle = new Bundle();

        //이전 리스트 넘기기
        bundle.putString("dutchpayListData",mBundle.getString("dutchpayListData"));

        if(Integer.parseInt(memCount.get()) != 0){ //추가 데이터 존재
            ObservableArrayList<DutchpayNewAddGroupModel> checkedList = new ObservableArrayList<>();
            for(int i=0; i<mGList.size(); i++){
                if(mGList.get(i).isGcheck()){ //체크 리스트로 업데이트
                    checkedList.add(mGList.get(i));
                }
            }

            Gson gson = new Gson();
            String Json = gson.toJson(checkedList);

            //Log.e("AddList ->",Json);
            bundle.putString("InputGroupMember",Json);
        } else { //추가 데이터 없음

            bundle.putString("InputGroupMember",null);
        }

        bundle.putParcelableArrayList("InputMember",null);

        return bundle;
    }
}
