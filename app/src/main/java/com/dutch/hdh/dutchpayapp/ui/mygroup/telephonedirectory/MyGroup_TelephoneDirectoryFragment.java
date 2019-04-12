package com.dutch.hdh.dutchpayapp.ui.mygroup.telephonedirectory;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentMyGroupTelephoneDirectoryBinding;
import com.kinda.alert.KAlertDialog;

import java.util.Objects;


public class MyGroup_TelephoneDirectoryFragment extends BaseFragment implements MyGroup_TelephoneDirectoryContract.View{

    private FragmentMyGroupTelephoneDirectoryBinding mBinding;
    private MyGroup_TelephoneDirectoryContract.Presenter mPresenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_group_telephone_directory, container, false);
        mPresenter = new MyGroup_TelephoneDirectoryPresenter(this, getContext(), getFragmentManager() , getArguments());

        initData();

        //멤버추가버튼 클릭
        mBinding.ibAddMember.setOnClickListener(v->
            mPresenter.clickAdd()
        );

        //검색
        mBinding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPresenter.searchTelephoneDirectories(mBinding.etSearch.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return mBinding.getRoot();
    }

    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {
        mPresenter.callingTelephoneDirectory(Objects.requireNonNull(getActivity()).getContentResolver());
        mPresenter.initListViewData(mBinding.lvTelephoneDirectory);
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
                    mPresenter.clickWarningDialogOK();

                })
                .setCancelText("취소")
                .setCancelClickListener(KAlertDialog::dismissWithAnimation)
                .show();
    }

    /**
     * 에러 다이얼로그 보여주기
     */
    @Override
    public void showErrorDialog(String title, String content) {
        new KAlertDialog(Objects.requireNonNull(getContext()), KAlertDialog.WARNING_TYPE)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText("확인")
                .setConfirmClickListener(sDialog -> {
                    sDialog.dismissWithAnimation();
                    if (getFragmentManager() != null) {
                        getFragmentManager().popBackStack();
                    }
                })
                .show();
    }

    /**
     * 멤버추가 Count 변경하기
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void changeAddMember(int count) {
        mBinding.tvAddMemberCount.setText(count+"");
    }

    /**
     * 뒤로가기 처리
     */
    public void onBackPressed(){
        mPresenter.clickBackPressed();
    }
}
