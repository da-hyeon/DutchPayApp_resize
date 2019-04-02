package com.dutch.hdh.dutchpayapp.ui.dutchpay.start;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.DutchpayStartListAdapter;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.detail.DutchpayDetailFragment;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpay.DutchpayNewFragment;
import com.dutch.hdh.dutchpayapp.ui.register.password.Register_PaymentPasswordFragment;

public class DutchpayStartPresenter implements DutchpayStartContract.Presenter{

    private DutchpayStartContract.View mView;

    private ObservableArrayList<TempStartListModel> mStartList;
    private DutchpayStartListAdapter mAdapter;

    public DutchpayStartPresenter(DutchpayStartContract.View mView) {
        this.mView = mView;
        this.mStartList = new ObservableArrayList<>();
        this.mAdapter = new DutchpayStartListAdapter(mStartList,this);
    }


    @Override
    public void listInit() {
        //더미 데이터 셋
        mStartList.add(new TempStartListModel("","","","",0));
        mStartList.add(new TempStartListModel("","","","",0));
        mStartList.add(new TempStartListModel("","","","",0));
        mStartList.add(new TempStartListModel("","","","",0));
        mStartList.add(new TempStartListModel("","","","",0));

        mAdapter.notifyDataSetChanged();

        //binding
        mView.adapterInit();
    }

    //내역 클릭
    @Override
    public void onItemClick(TempStartListModel item) {
            //임시 이동
        FragmentManager fm = mView.getFragmentManager();

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

        DutchpayDetailFragment dutchpayDetailFragment = new DutchpayDetailFragment();
        fragmentTransaction.replace(R.id.flFragmentContainer,dutchpayDetailFragment , DutchpayDetailFragment.class.getName());
        fragmentTransaction.addToBackStack(DutchpayDetailFragment.class.getName());
        fragmentTransaction.commit();
    }

    //더치페이 새로 시작하기
    public void onNewClick(){
        FragmentManager fm = mView.getFragmentManager();

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

        DutchpayNewFragment dutchpayNewFragment = new DutchpayNewFragment();
        fragmentTransaction.replace(R.id.flFragmentContainer,dutchpayNewFragment, DutchpayNewFragment.class.getName());
        fragmentTransaction.addToBackStack(DutchpayNewFragment.class.getName());
        fragmentTransaction.commit();
    }

    public DutchpayStartListAdapter getmAdapter() {
        return mAdapter;
    }

    public ObservableArrayList<TempStartListModel> getmStartList() {
        return mStartList;
    }

    @BindingAdapter("bind:item")
    public static void bindItem(RecyclerView view, ObservableArrayList<TempStartListModel> list){
        DutchpayStartListAdapter adapter = (DutchpayStartListAdapter) view.getAdapter();
        if(adapter != null) {
            adapter.setItem(list);
        }
    }

}
