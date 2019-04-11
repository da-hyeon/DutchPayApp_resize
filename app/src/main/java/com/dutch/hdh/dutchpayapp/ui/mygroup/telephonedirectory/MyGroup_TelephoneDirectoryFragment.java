package com.dutch.hdh.dutchpayapp.ui.mygroup.telephonedirectory;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentMyGroupTelephoneDirectoryBinding;
import com.kinda.alert.KAlertDialog;


public class MyGroup_TelephoneDirectoryFragment extends BaseFragment implements MyGroup_TelephoneDirectoryContract.View{

    private FragmentMyGroupTelephoneDirectoryBinding mBinding;
    private MyGroup_TelephoneDirectoryContract.Presenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_group_telephone_directory, container, false);
        mPresenter = new MyGroup_TelephoneDirectoryPresenter(this, getContext(), getFragmentManager());

        initData();

        mBinding.ibAddMember.setOnClickListener(v->
            mPresenter.clickAdd(getArguments())
        );

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
        mPresenter.callingTelephoneDirectory(getActivity().getContentResolver() , getArguments());
        mPresenter.initListViewData(mBinding.lvTelephoneDirectory);
    }

    @Override
    public void showFailDialog(String title ,String content) {
        new KAlertDialog(getContext(), KAlertDialog.WARNING_TYPE)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText("확인")
                .setConfirmClickListener(sDialog -> sDialog.dismissWithAnimation())
                .show();
    }

    @Override
    public void showWarningDialog(String title, String content) {
        new KAlertDialog(getContext(), KAlertDialog.WARNING_TYPE)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText("확인")
                .setConfirmClickListener(sDialog -> {
                    sDialog.dismissWithAnimation();
                    mPresenter.clickWarningDialogOK(getArguments());

                })
                .setCancelText("취소")
                .setCancelClickListener(sDialog -> sDialog.dismissWithAnimation())
                .show();
    }

    @Override
    public void showErrorDialog(String title, String content) {
        new KAlertDialog(getContext(), KAlertDialog.WARNING_TYPE)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText("확인")
                .setConfirmClickListener(sDialog -> {
                    sDialog.dismissWithAnimation();
                    getFragmentManager().popBackStack();
                })
                .show();
    }

    @Override
    public void changeAddMember(int count) {
        mBinding.tvAddMemberCount.setText(count+"");
    }

    public void onBackPressed(){
        mPresenter.clickBackPressed();
    }
}
