package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpayconfirm;

import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.adapter.DutchpayConfirmListAdapter;
import com.dutch.hdh.dutchpayapp.data.db.UserList;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpay.TempNewListModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DutchpayNewConfirmPresenter implements DutchpayNewConfirmContract.Presenter{

    DutchpayNewConfirmContract.View mView;
    ObservableArrayList<TempConfirmListModel> mConfirmList;
    DutchpayConfirmListAdapter mAdapter;
    Bundle mData;

    MyApplication mMyApplication;
    ArrayList<UserList> user_list;

    private String json;

    public DutchpayNewConfirmPresenter(DutchpayNewConfirmContract.View mView,Bundle data) {
        this.mView = mView;
        this.mConfirmList = new ObservableArrayList<>();
        this.mAdapter = new DutchpayConfirmListAdapter(mConfirmList,this);
        this.mData = data;
        this.mMyApplication = MyApplication.getInstance();
        this.user_list = new ArrayList<>();
    }

    @Override
    public void listInit() {
        Gson gson = new Gson();
        Log.e("check-->", mData.getString("JList"));

        List<TempConfirmListModel> list = gson.fromJson(mData.getString("JList"),new TypeToken<List<TempConfirmListModel>>(){}.getType());
        for(TempConfirmListModel model : list){
            mConfirmList.add(model);

            //flag 보완완
           String tmp = "";
           tmp = model.completeFlag ? "Y" : "N";

            user_list.add(new UserList(model.getName(),model.getPhone(),model.getCost(),tmp));
        }

        json = gson.toJson(user_list);
        Log.e("make list ->",json);

        //binding
        mView.adapterInit();
    }

    @Override
    public void onPayClick() {
        ArrayList<String> data = mData.getStringArrayList("Info");

        //더치페이 개설 및 금액 결제
        Call<Void> setnewDutchpay = MyApplication
                .getRestAdapter()
                .setNewDutchpay(Integer.parseInt(mMyApplication.getUserInfo().getUserCode()),
                        data.get(0),
                        Integer.parseInt(data.get(3)),
                        data.get(1),
                        data.get(2),
                        json);

        setnewDutchpay.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.e("response",response.message());
//                mView.showSuccessDialog("성공", groupName + "그룹이 변경되었습니다.");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("fail", t.getMessage());
            }
        });

        int newMoney = mMyApplication.getUserInfo().getUserMoney() - Integer.parseInt(data.get(3));
        mMyApplication.getUserInfo().setUserMoney(newMoney);

        //더치페이 완료
        mMyApplication.setDutchpayGroup(false);
        mMyApplication.setDutchpayNewFragment(null);
        //메인으로 이동
        mView.setDefaultMainStack();
    }

    public ObservableArrayList<TempConfirmListModel> getmConfirmList() {
        return mConfirmList;
    }

    public DutchpayConfirmListAdapter getmAdapter() {
        return mAdapter;
    }
}
