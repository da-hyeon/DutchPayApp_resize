package com.dutch.hdh.dutchpayapp.ui.wallet.payhistory;


import android.view.SurfaceHolder;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragmentContract;
import com.dutch.hdh.dutchpayapp.data.db.PayHistoryList;

import java.util.ArrayList;


public interface PayUsageHistoryContract extends BaseFragmentContract {
    interface View extends BaseFragmentContract.View{

        //init
        void initData();
        //사용내역 리스트 보여주기?
        void setPayUsageHistoryList(ArrayList<PayHistoryList.PayHistoryListResult> payHistoryListResultArrayList);
    }

    interface Presenter {


        //주기받기 탭버튼 체크
        void checkDateSortButton(LinearLayout linearLayout, boolean isCheck);


        void getPayUsageHistoryList(String buttonType, String userCode, String payType);

        void clickUsagePayDetail(PayHistoryList.PayHistoryListResult payHistoryListResult);

    }
}
