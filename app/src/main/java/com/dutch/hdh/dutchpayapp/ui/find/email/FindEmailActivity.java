package com.dutch.hdh.dutchpayapp.ui.find.email;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.activity.BaseActivity;
import com.dutch.hdh.dutchpayapp.databinding.ActivityFindEmailBinding;

public class FindEmailActivity extends BaseActivity implements FindEmailContract.View {

    private ActivityFindEmailBinding mBinding;
    private FindEmailContract.Presenter mPresenter;
    private EditText[] mEditTextArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_find_email);
        mBinding.setFindEmailActivity(this);
        mPresenter = new FindEmailPresenter(this, this);

        initData();

        //취소클릭
        mBinding.vCancel.setOnClickListener(v ->
                mPresenter.clickCancel()
        );

        //확인클릭
        mBinding.btOK.setOnClickListener(v ->
                mPresenter.clickOKButton(mEditTextArray)
        );
    }

    /**
     * 객체생성 및 데이터초기화
     */
    public void initData() {
        mEditTextArray = new EditText[]{
                mBinding.etName,
                mBinding.etPhoneNumber,
                mBinding.etAuthNumber
        };
    }

    @Override
    public void showEmailLayout() {
        mBinding.llFindEmail.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmailLayout() {
        mBinding.llFindEmail.setVisibility(View.GONE);
    }

    @Override
    public void changeEmailText(String email) {
        mBinding.tvEmail.setText(email);
    }
}
