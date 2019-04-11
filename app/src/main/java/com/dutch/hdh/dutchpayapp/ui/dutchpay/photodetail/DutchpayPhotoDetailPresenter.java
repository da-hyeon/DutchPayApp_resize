package com.dutch.hdh.dutchpayapp.ui.dutchpay.photodetail;

import android.databinding.ObservableArrayList;

import com.dutch.hdh.dutchpayapp.adapter.DutchpayPhotoDetailListAdapter;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.photo.DutchpayPhotoContract;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.startdetail.TempDetailListModel;

public class DutchpayPhotoDetailPresenter implements DutchpayPhotoDetailContract.Presenter {

    DutchpayPhotoContract.View mView;
    private ObservableArrayList<TempDetailListModel> mPDetailList;
    private DutchpayPhotoDetailListAdapter mAdapter;

    public DutchpayPhotoDetailPresenter(DutchpayPhotoContract.View mView) {
        this.mView = mView;
        this.mPDetailList = new ObservableArrayList<>();
        this.mAdapter = new DutchpayPhotoDetailListAdapter(mPDetailList,this);
    }

    public ObservableArrayList<TempDetailListModel> getmPDetailList() {
        return mPDetailList;
    }

    public DutchpayPhotoDetailListAdapter getmAdapter() {
        return mAdapter;
    }

    @Override
    public void listInit() {
        //더미 데이터 셋
        mPDetailList.add(new TempDetailListModel("",false,"",0));
        mPDetailList.add(new TempDetailListModel("",true,"",0));
        mPDetailList.add(new TempDetailListModel("",false,"",0));
        mPDetailList.add(new TempDetailListModel("",false,"",0));

        mAdapter.notifyDataSetChanged();

        //binding
        mView.adapterInit();
    }
}
