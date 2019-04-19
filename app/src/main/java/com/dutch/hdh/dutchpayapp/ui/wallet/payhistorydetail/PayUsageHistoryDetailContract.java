package com.dutch.hdh.dutchpayapp.ui.wallet.payhistorydetail;


import android.widget.LinearLayout;

import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragmentContract;
import com.dutch.hdh.dutchpayapp.data.db.PayHistoryList;

import java.util.ArrayList;


public interface PayUsageHistoryDetailContract extends BaseFragmentContract {
    interface View extends BaseFragmentContract.View{

        //init
        void initData();
    }

    interface Presenter {

        int getPayStatus(String payType);
    }
}
