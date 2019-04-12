package com.dutch.hdh.dutchpayapp.ui.mygroup.directinput;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentMyGroupDirectInputBinding;
import com.kinda.alert.KAlertDialog;

import java.util.Objects;

public class MyGroup_DirectInputFragment extends BaseFragment implements MyGroup_DirectInputContract.View {

    private MyGroup_DirectInputContract.Presenter mPresenter;
    private FragmentMyGroupDirectInputBinding mBinding;

    /**
     * 생성자
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_group_direct_input, container, false);

        initData();

        mBinding.ibAddMember.setOnClickListener(v ->
            mPresenter.clickAddMember()
        );

        mBinding.btMemberAdditionConfirmed.setOnClickListener(v ->
            mPresenter.clickMemberAdditionConfirmed(getArguments())
        );

        return mBinding.getRoot();
    }

    /**
     * 객체생성 및 데이터초기화
     */
    private void initData(){
        mPresenter = new MyGroup_DirectInputPresenter(this, getContext(), getFragmentManager());
        mPresenter.initListViewData(mBinding.lvDirectInputMember);

    }

    /**
     * 추가인원 Count 변경
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void changeAddMemberCount(int count) {
        mBinding.tvAddMemberCount.setText(count+"");
    }


    /**
     * 경고 다이얼로그 보여주기
     */
    @Override
    public void showWarningDialog(String title, String content) {
        new KAlertDialog(Objects.requireNonNull(getContext()), KAlertDialog.WARNING_TYPE)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText("확인")
                .setConfirmClickListener(sDialog -> {
                    sDialog.dismissWithAnimation();
                    mPresenter.clickWarningDialogOK(getArguments());
                })
                .setCancelText("취소")
                .setCancelClickListener(KAlertDialog::dismissWithAnimation)
                .show();
    }


    /**
     * 뒤로가기 처리
     */
    public void onBackPressed(){
        mPresenter.clickBackPressed();
    }
}