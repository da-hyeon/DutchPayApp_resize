package com.dutch.hdh.dutchpayapp.ui.mypage.change_password;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.activity.BaseActivity;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.ActivityMyPageChangeEmailBinding;
import com.dutch.hdh.dutchpayapp.databinding.ActivityMyPageChangePasswordBinding;

public class MyPage_ChangePasswordActivity extends BaseActivity implements MyPage_ChangePasswordContract.View {

    private MyPage_ChangePasswordContract.Presenter mPresenter;
    private ActivityMyPageChangePasswordBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this , R.layout.activity_my_page_change_password);
        mBinding.setPasswordActivity(this);
    }

}
