package com.dutch.hdh.dutchpayapp.ui.wallet.payhistory;


import android.view.SurfaceHolder;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragmentContract;


public interface PayUsageHistoryContract extends BaseFragmentContract {
    interface View extends BaseFragmentContract.View{

        //init
        void initData();
    }

    interface Presenter {


        //주기받기 탭버튼 체크
        void checkDateSortButton(LinearLayout linearLayout, boolean isCheck);

        //전체보기
        void clickPayAllStatus();
        //더치페이 보기
        void clickPayDutchPayStatus();
        //개인결제 보기
        void clickPaySoloPayStatus();
        //주기/받기
        void clickPaySendReceiveStatus();
        //1주 사용내역 요청
        void clickOneWeekPayList();
        //1개월 사용내역 요청
        void clickOneMonthPayList();
        //3개월 사용내역 요청
        void clickThreeMonthPayList();
        //1주 사용내역 요청
        void clickAllPayList();

    }
}
