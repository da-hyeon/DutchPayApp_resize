package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpayconfirm;

import android.databinding.ObservableArrayList;

import com.dutch.hdh.dutchpayapp.adapter.DutchpayConfirmListAdapter;

public class DutchpayNewConfirmPresenter implements DutchpayNewConfirmContract.Presenter{

    DutchpayNewConfirmContract.View mView;
    ObservableArrayList<TempConfirmListModel> mConfirmList;
    DutchpayConfirmListAdapter mAdapter;

    public DutchpayNewConfirmPresenter(DutchpayNewConfirmContract.View mView) {
        this.mView = mView;
        this.mConfirmList = new ObservableArrayList<>();
        this.mAdapter = new DutchpayConfirmListAdapter(mConfirmList,this);
    }

    @Override
    public void listInit() {
        //더미 데이터 셋
        mConfirmList.add(new TempConfirmListModel("","","",false));
        mConfirmList.add(new TempConfirmListModel("","","",false));
        mConfirmList.add(new TempConfirmListModel("","","",false));
        mConfirmList.add(new TempConfirmListModel("","","",false));

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
