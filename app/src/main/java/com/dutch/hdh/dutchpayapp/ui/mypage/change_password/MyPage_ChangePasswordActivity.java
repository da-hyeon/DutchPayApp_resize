package com.dutch.hdh.dutchpayapp.ui.mypage.change_password;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.activity.BaseActivity;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.ActivityMyPageChangeEmailBinding;
import com.dutch.hdh.dutchpayapp.databinding.ActivityMyPageChangePasswordBinding;

public class MyPage_ChangePasswordActivity extends BaseActivity implements MyPage_ChangePasswordContract.View {

    private MyPage_ChangePasswordContract.Presenter mPresenter;
    private ActivityMyPageChangePasswordBinding mBinding;
    private EditText[] mEditTextArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_my_page_change_password);
        mPresenter = new MyPage_ChangePasswordPresenter(this, this, getSupportFragmentManager());

        initData();
        //취소클릭
        mBinding.vCancel.setOnClickListener(v ->
                mPresenter.clickCancel()
        );

        mBinding.btChange.setOnClickListener(v ->
                mPresenter.clickChange(mEditTextArray)
        );

        mBinding.setPasswordActivity(this);
    }

    /**
     * 객체생성 및 데이터초기화
     */
    public void initData() {
        mEditTextArray = new EditText[] {
                mBinding.etMyPassword,
                mBinding.etNewPassword,
                mBinding.etNewPasswordCheck
        };
    }
    @Override
    public void onBackPressed() {
        mPresenter.clickCancel();
    }
}
