package com.dutch.hdh.dutchpayapp.ui.dutchpay.photo;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.DutchpayPhotoListAdapter;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.photodetail.DutchpayPhotoDetailFragment;

public class DutchpayPhotoPresenter implements DutchpayPhotoContract.Presenter {

    private DutchpayPhotoContract.View mView;
    private ObservableArrayList<TempPhotoListModel> mPhotoList;
    private DutchpayPhotoListAdapter mAdapter;

    public DutchpayPhotoPresenter(DutchpayPhotoContract.View mView) {
        this.mView = mView;
        this.mPhotoList = new ObservableArrayList<>();
        this.mAdapter = new DutchpayPhotoListAdapter(mPhotoList,this);
    }

    @Override
    public void listInit() {
        //더미 데이터 셋
        mPhotoList.add(new TempPhotoListModel(""));
        mPhotoList.add(new TempPhotoListModel(""));
        mPhotoList.add(new TempPhotoListModel(""));

        mAdapter.notifyDataSetChanged();

        //binding
        mView.adapterInit();
    }

    public ObservableArrayList<TempPhotoListModel> getmPhotoList() {
        return mPhotoList;
    }

    public DutchpayPhotoListAdapter getmAdapter() {
        return mAdapter;
    }

    @BindingAdapter("bind:item")
    public static void bindItem(RecyclerView view, ObservableArrayList<TempPhotoListModel> list){
        DutchpayPhotoListAdapter adapter = (DutchpayPhotoListAdapter) view.getAdapter();
        if(adapter != null) {
            adapter.setItem(list);
        }
    }

    public void onItemClick(){
        FragmentManager fm = mView.getFragmentManager();

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

        DutchpayPhotoDetailFragment dutchpayPhotoDetailFragment = new DutchpayPhotoDetailFragment();
        fragmentTransaction.replace(R.id.flFragmentContainer, dutchpayPhotoDetailFragment, DutchpayPhotoDetailFragment.class.getName());
        fragmentTransaction.addToBackStack(DutchpayPhotoDetailFragment.class.getName());
        fragmentTransaction.commit();
    }
}
