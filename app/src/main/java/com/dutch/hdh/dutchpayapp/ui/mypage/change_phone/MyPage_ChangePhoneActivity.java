package com.dutch.hdh.dutchpayapp.ui.mypage.change_phone;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.activity.BaseActivity;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.ActivityMyPageChangeEmailBinding;
import com.dutch.hdh.dutchpayapp.databinding.ActivityMyPageChangePhoneBinding;

public class MyPage_ChangePhoneActivity extends BaseActivity implements MyPage_ChangePhoneContract.View {

    private MyPage_ChangePhoneContract.Presenter mPresenter;
    private ActivityMyPageChangePhoneBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this , R.layout.activity_my_page_change_phone);
        mBinding.setPhoneActivity(this);
    }
}
