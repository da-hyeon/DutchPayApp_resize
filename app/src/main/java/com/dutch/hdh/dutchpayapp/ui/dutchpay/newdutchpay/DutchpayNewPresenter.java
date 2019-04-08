package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpay;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.DutchpayNewListAdapter;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpayinfo.DutchpayNewInfoFragment;
import com.google.gson.Gson;

public class DutchpayNewPresenter implements DutchpayNewContract.Presenter {

    private DutchpayNewContract.View mView;
    private ObservableArrayList<TempNewListModel> mNewList;
    private DutchpayNewListAdapter mAdapter;

    public DutchpayNewPresenter(DutchpayNewContract.View mView) {
        this.mView = mView;
        this.mNewList = new ObservableArrayList<>();
        this.mAdapter = new DutchpayNewListAdapter(mNewList,this);
    }

    @Override
    public void listInit() {
        //더미 데이터 셋
        mNewList.add(new TempNewListModel("박소영","10000원","010-1111-2222",false));
        mNewList.add(new TempNewListModel("최윤미","10000원","010-3333-4444",false));
        mNewList.add(new TempNewListModel("박현주","10000원","010-5555-6666",false));
        mNewList.add(new TempNewListModel("","","",false));

        mAdapter.notifyDataSetChanged();

        //binding
        mView.adapterInit();
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
}
