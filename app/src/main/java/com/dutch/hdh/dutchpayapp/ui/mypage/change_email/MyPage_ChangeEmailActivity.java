package com.dutch.hdh.dutchpayapp.ui.mypage.change_email;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.activity.BaseActivity;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.ActivityMyPageChangeEmailBinding;


public class MyPage_ChangeEmailActivity extends BaseActivity implements MyPage_ChangeEmailContract.View{

    private MyPage_ChangeEmailContract.Presenter mPresenter;
    private ActivityMyPageChangeEmailBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this , R.layout.activity_my_page_change_email);
        mBinding.setEmailActivity(this);
    }
}
