package com.dutch.hdh.dutchpayapp.ui.main.activity;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public interface MainActivityContract {
    interface View{
        //init
        void initData();

        //show
        void showDrawerLayout();
        void showToast(String content);
        void showUserInfo(boolean state);
        void showBell();
        void showExit();
        void showFailDialog(String title ,String content);

        //change
        void changeTitle(String title);

        //hide
        void hideDrawerLayout();
        void hideBell();
        void hideExit();

        //remove
        void removeActivity();
    }

    interface Presenter{
        void onFragmentResume();

        //init
        void initMainFragment();
        void initLoginState();

//        튜토리얼
//        void initToturial(ImageView imageView, String title , String content);
//        void initToturial(LinearLayout linearLayout, String title , String content);
//        void finishRegisterToturial();

        //click
        void clickMenu();
        void clickExit();
        void clickBack();
        void clickLogin();
        void clickRegister();
        void clickSolopayStart();
        void clickDutchpayStart();
        void clickEvent();
        void clickMyPage();
        void clickMyGroup();
        void clickMyWallet();
        void clickNotice();
        void clickService();
        void clickCustomerCenter();
        void clickLogout();

    }
}
