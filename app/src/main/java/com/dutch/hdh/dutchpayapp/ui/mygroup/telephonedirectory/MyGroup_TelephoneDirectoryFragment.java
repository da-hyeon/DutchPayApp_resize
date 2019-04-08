package com.dutch.hdh.dutchpayapp.ui.mygroup.telephonedirectory;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentMyGroupTelephoneDirectoryBinding;


public class MyGroup_TelephoneDirectoryFragment extends BaseFragment implements MyGroup_TelephoneDirectoryContract.View{

    private FragmentMyGroupTelephoneDirectoryBinding mBinding;
    private MyGroup_TelephoneDirectoryContract.Presenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_group_telephone_directory, container, false);
        mPresenter = new MyGroup_TelephoneDirectoryPresenter(this, getContext());

        initData();

        return mBinding.getRoot();
    }

    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {
        mPresenter.callingTelephoneDirectory(getActivity().getContentResolver());
        mPresenter.initListViewData(mBinding.lvTelephoneDirectory);
    }

}
