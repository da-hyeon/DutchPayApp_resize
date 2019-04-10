package com.dutch.hdh.dutchpayapp.ui.register.allview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentRegisterViewAllTermsConditionsBinding;


public class Register_ViewAllTermsConditionsFragment extends BaseFragment implements Register_ViewAllTermsConditionsContract.View {

    private FragmentRegisterViewAllTermsConditionsBinding mBinding;
    private Register_ViewAllTermsConditionsContract.Presenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_register_view_all_terms_conditions, container, false);

        //객체생성 및 데이터초기화
        initData();

        //체크박스 클릭
        mBinding.agreeCheck.setOnClickListener(v->
                mPresenter.clickTOS(mBinding.agreeCheck.isChecked())
        );

        return mBinding.getRoot();
    }

    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {
        mPresenter = new Register_ViewAllTermsConditionsPresenter(this , getContext() , getFragmentManager(), getActivity() );
        mPresenter.getData(getArguments());
    }

    /**
     * 뒤로가기 클릭
     */
    public void onBackPress(){
        mPresenter.clickBackPressed();
    }

    /**
     * 타이틀 변경
     */
    @Override
    public void changeTitle(String title) {
        mBinding.txtTitle.setText(title);
    }

    /**
     * 내용 변경
     */
    @Override
    public void changeContent(String content) {
        mBinding.txtContent.setText(content);
    }

    /**
     * 약관동의 Image 변경
     */
    @Override
    public void changeTOS(boolean state) {
        mBinding.agreeCheck.setChecked(state);
    }
}
