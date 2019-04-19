package com.dutch.hdh.dutchpayapp.ui.wallet.mycard;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.dutch.hdh.dutchpayapp.base.activity.BaseActivityContract;
import com.dutch.hdh.dutchpayapp.data.db.CardRegisterList;

import java.util.ArrayList;

public interface MyCardContract extends BaseActivityContract {

    interface View {
        void initData();

        void showRepresentativeCardDialog(String mainCardCode, String subCardCode);

        void setRegisterCardList(ArrayList<CardRegisterList.CardRegisterListResult> cardRegisterListResultArrayList);


    }

    interface Presenter {

        //set
        void getRegisterCardList();

        //click
        void clickCancel();

        void clickLeft(ViewPager viewPager, TabLayout tabLayout);

        void clickRight(ViewPager viewPager, TabLayout tabLayout);

        //카드등록
        void clickCardAdd();

        //카드등록관리
        void clickCardManagement();

        //대표카드 선택
        void setRepresentativeCard(String mainCardCode, String subCardCode);

        //뷰페이저

        void checkViewPager(ViewPager viewPager, TabLayout tabLayout);
    }
}
