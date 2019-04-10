package com.dutch.hdh.dutchpayapp.ui.main.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragmentContract;

public interface MainFragmentContract {
    interface View extends BaseFragmentContract.View {
        //init
        void initData();

        //show
        void showUserInfo(String userName , int userDutchMoney , boolean state);
    }
    interface Presenter{
        //init
        void initLoginState();

        //set
        void setAdapter(ViewPager viewPager , TabLayout tabLayout);

        //click
        void clickSolopay();
        void clickDutchpay();
    }
}
