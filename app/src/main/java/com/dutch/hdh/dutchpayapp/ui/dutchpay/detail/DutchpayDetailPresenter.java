package com.dutch.hdh.dutchpayapp.ui.dutchpay.detail;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.support.v7.widget.RecyclerView;

import com.dutch.hdh.dutchpayapp.adapter.DutchpayDetailListAdapter;

public class DutchpayDetailPresenter implements DutchpayDetailContract.Presenter {

    private DutchpayDetailContract.View mView;
    private ObservableArrayList<TempDetailListModel> mDetailList;
    private DutchpayDetailListAdapter mAdapter;

    public DutchpayDetailPresenter(DutchpayDetailContract.View mView) {
        this.mView = mView;
        this.mDetailList = new ObservableArrayList<>();
        this.mAdapter = new DutchpayDetailListAdapter(mDetailList,this);
    }

    @Override
    public void listInit() {
        //더미 데이터 셋
        mDetailList.add(new TempDetailListModel("",false,"",0));
        mDetailList.add(new TempDetailListModel("",true,"",0));
        mDetailList.add(new TempDetailListModel("",false,"",0));
        mDetailList.add(new TempDetailListModel("",false,"",0));
        mDetailList.add(new TempDetailListModel("",true,"",0));
        mDetailList.add(new TempDetailListModel("",false,"",0));

        mAdapter.notifyDataSetChanged();

        //binding
        mView.adapterInit();
    }

    public ObservableArrayList<TempDetailListModel> getmDetailList() {
        return mDetailList;
    }

    public DutchpayDetailListAdapter getmAdapter() {
        return mAdapter;
    }

    @BindingAdapter("bind:item")
    public static void bindItem(RecyclerView view, ObservableArrayList<TempDetailListModel> list){
        DutchpayDetailListAdapter adapter = (DutchpayDetailListAdapter) view.getAdapter();
        if(adapter != null) {
            adapter.setItem(list);
        }
    }
}
