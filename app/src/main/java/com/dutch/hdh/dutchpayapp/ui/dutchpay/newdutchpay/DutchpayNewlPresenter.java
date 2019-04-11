package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpay;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.DutchpayNewListAdapter;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpayinfo.DutchpayNewInfoFragment;

public class DutchpayNewlPresenter implements DutchpayNewContract.Presenter {

    private DutchpayNewContract.View mView;
    private ObservableArrayList<TempNewListModel> mNewList;
    private DutchpayNewListAdapter mAdapter;

    public DutchpayNewlPresenter(DutchpayNewContract.View mView) {
        this.mView = mView;
        this.mNewList = new ObservableArrayList<>();
        this.mAdapter = new DutchpayNewListAdapter(mNewList,this);
    }

    @Override
    public void listInit() {
        //더미 데이터 셋
        mNewList.add(new TempNewListModel("","","",true));
        mNewList.add(new TempNewListModel("","","",true));
        mNewList.add(new TempNewListModel("","","",true));
        mNewList.add(new TempNewListModel("","","",true));
        mNewList.add(new TempNewListModel("","","",true));

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

    @BindingAdapter("item")
    public static void bindItem(RecyclerView view, ObservableArrayList<TempNewListModel> list){
        DutchpayNewListAdapter adapter = (DutchpayNewListAdapter) view.getAdapter();
        if(adapter != null) {
            adapter.setItem(list);
        }
    }

    public void onNextClick(){
        FragmentManager fm = mView.getFragmentManager();

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

        DutchpayNewInfoFragment dutchpayNewInfoFragment = new DutchpayNewInfoFragment();
        fragmentTransaction.replace(R.id.flFragmentContainer,dutchpayNewInfoFragment, DutchpayNewInfoFragment.class.getName());
        fragmentTransaction.addToBackStack(DutchpayNewInfoFragment.class.getName());
        fragmentTransaction.commit();
    }
}
