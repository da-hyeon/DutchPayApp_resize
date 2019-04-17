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

        //change
        void changeEventTitle(String title);
        void changeUserMoney(int money);
    }
    interface Presenter{
        //init
        void initLoginState();
        void onResume();

        //set
        void setAdapter(ViewPager viewPager , TabLayout tabLayout);

        //click
        void clickSolopay();
        void clickDutchpay();
        void clickAllEvent();

        //slide
        void slideViewPagerAction(int position);
    }
}
