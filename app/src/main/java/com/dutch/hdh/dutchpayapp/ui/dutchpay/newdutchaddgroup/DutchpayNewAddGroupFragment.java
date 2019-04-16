package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchaddgroup;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.DutchpayNewGroupListAdapter;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentDutchpayAddGroupBinding;

public class DutchpayNewAddGroupFragment extends BaseFragment implements DutchpayNewAddGroupContract.View{

    FragmentDutchpayAddGroupBinding mBinding;
    DutchpayNewAddGroupPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), R.layout.fragment_dutchpay_add_group,container,false);
        mBinding.setFragment(this);
        mPresenter = new DutchpayNewAddGroupPresenter(this);

        //리스트 초기 생성
        mPresenter.listInit();

        return mBinding.getRoot();
    }

    @BindingAdapter("item")
    public static void bindItem(RecyclerView view, ObservableArrayList<DutchpayNewAddGroupModel> list){
        DutchpayNewGroupListAdapter adapter = (DutchpayNewGroupListAdapter) view.getAdapter();
        if(adapter != null) {
            adapter.setItem(list);
        }
    }

    @Override
    public void adapterInit() {
        //어댑터 셋팅
        mBinding.setGroupList(mPresenter.getGList());
        mBinding.rvGroupList.setAdapter(mPresenter.getAdapter());
        mBinding.rvGroupList.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
    }
}
