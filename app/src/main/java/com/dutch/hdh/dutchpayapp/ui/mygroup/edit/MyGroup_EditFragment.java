package com.dutch.hdh.dutchpayapp.ui.mygroup.edit;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.BaseFragment;
import com.dutch.hdh.dutchpayapp.data.db.GroupParticipants;
import com.dutch.hdh.dutchpayapp.databinding.FragmentMyGroupBinding;
import com.dutch.hdh.dutchpayapp.databinding.FragmentMyGroupEditBinding;
import com.kinda.alert.KAlertDialog;

import java.util.ArrayList;

public class MyGroup_EditFragment extends BaseFragment implements MyGroup_EditContract.View {

    private FragmentMyGroupEditBinding mBinding;
    private MyGroup_EditContract.Presenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_group_edit, container, false);

        initData();

        //전화부호부 클릭
        mBinding.btTelephoneDirectory.setOnClickListener(v -> {
            mPresenter.clickTelephoneDirectory();
        });

        //직접입력 클릭
        mBinding.btDirectoryInput.setOnClickListener(v -> {
            mPresenter.clickDirectInput();
        });

        return mBinding.getRoot();
    }

    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {
        mPresenter = new MyGroup_EditPresenter(this, getContext(), getFragmentManager());

        mPresenter.initListViewData(mBinding.lvParticipantsList);
    }

    @Override
    public void showWarningDialog(String title, String content) {
        new KAlertDialog(getContext(), KAlertDialog.WARNING_TYPE)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText("확인")
                .setConfirmClickListener(sDialog -> {
                    sDialog.dismissWithAnimation();
                    getFragmentManager().popBackStack();
                })
                .setCancelText("취소")
                .setCancelClickListener(sDialog -> sDialog.dismissWithAnimation())
                .show();
    }

    @Override
    public void changePersonCount(int num) {
        mBinding.tvPersonCount.setText("( "+ num +"명 )");
    }

    public void onBackPressed() {
        mPresenter.clickBackPressed();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.refreshData(getArguments());
    }
}
