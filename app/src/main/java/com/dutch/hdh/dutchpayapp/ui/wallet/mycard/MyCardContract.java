package com.dutch.hdh.dutchpayapp.ui.wallet.mycard;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

public interface MyCardContract {

    interface View {
        void initData();
    }

    interface Presenter {

        //set
        void setAdapter(ViewPager viewPager, TabLayout tabLayout);

        //click
        void clickCancel();
        void clickLeft(ViewPager viewPager, TabLayout tabLayout);
        void clickRight(ViewPager viewPager, TabLayout tabLayout);
        //카드등록
        void clickCardAdd();
        //카드등록관리
        void clickCardManagement();
    }
}
