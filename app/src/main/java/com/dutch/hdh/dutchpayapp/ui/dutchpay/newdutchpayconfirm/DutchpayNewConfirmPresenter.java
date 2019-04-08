package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpayconfirm;

import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.util.Log;

import com.dutch.hdh.dutchpayapp.adapter.DutchpayConfirmListAdapter;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpay.TempNewListModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DutchpayNewConfirmPresenter implements DutchpayNewConfirmContract.Presenter{

    DutchpayNewConfirmContract.View mView;
    ObservableArrayList<TempConfirmListModel> mConfirmList;
    DutchpayConfirmListAdapter mAdapter;
    Bundle mData;

    public DutchpayNewConfirmPresenter(DutchpayNewConfirmContract.View mView,Bundle data) {
        this.mView = mView;
        this.mConfirmList = new ObservableArrayList<>();
        this.mAdapter = new DutchpayConfirmListAdapter(mConfirmList,this);
        this.mData = data;
    }

    @Override
    public void listInit() {
        Gson gson = new Gson();
        Log.e("check-->", mData.getString("JList"));

        List<TempConfirmListModel> list = gson.fromJson(mData.getString("JList"),new TypeToken<List<TempConfirmListModel>>(){}.getType());
        for(TempConfirmListModel model : list){
            mConfirmList.add(model);
        }
        mAdapter.notifyDataSetChanged();

        //binding
        mView.adapterInit();
    }

    public ObservableArrayList<TempConfirmListModel> getmConfirmList() {
        return mConfirmList;
    }

    public DutchpayConfirmListAdapter getmAdapter() {
        return mAdapter;
    }
}
