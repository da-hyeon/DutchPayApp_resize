package com.dutch.hdh.dutchpayapp.ui.mypage.refund;

import android.os.Bundle;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.activity.BaseActivity;

public class MyPage_SelectRefundAccountActivity extends BaseActivity implements MyPage_SelectRefundAccountContract.View{

    private MyPage_SelectRefundAccountContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page_select_refund_account);

    }
}
