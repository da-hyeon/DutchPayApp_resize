package com.dutch.hdh.dutchpayapp.ui.mygroup.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentMyGroupBinding;

public class MyGroup_MainFragment extends BaseFragment implements MyGroup_MainContract.View{

    private FragmentMyGroupBinding mBinding;
    private MyGroup_MainContract.Presenter mPresenter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_group, container, false);
        mPresenter = new MyGroup_MainPresenter(this, getContext());

        initData();

        return mBinding.getRoot();
    }

    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {
        mPresenter.initListViewData(mBinding.lvMyGroup);
    }
}
