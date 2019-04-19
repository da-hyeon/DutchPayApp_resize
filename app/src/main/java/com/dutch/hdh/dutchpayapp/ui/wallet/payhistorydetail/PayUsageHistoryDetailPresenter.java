package com.dutch.hdh.dutchpayapp.ui.wallet.payhistorydetail;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.widget.LinearLayout;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.PayHistoryList;
import com.dutch.hdh.dutchpayapp.util.Trace;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayUsageHistoryDetailPresenter implements PayUsageHistoryDetailContract.Presenter {


    private PayUsageHistoryDetailContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;



    public PayUsageHistoryDetailPresenter(PayUsageHistoryDetailContract.View view, Context context, FragmentManager fragmentManager) {
        this.mView = view;
        this.mContext = context;
        this.mFragmentManager = fragmentManager;
    }

    @Override
    public int getPayStatus(String payType) {
        switch (payType) {
            case "단독":
                return R.drawable.wallet16_5;
            case "더치페이":
                return R.drawable.wallet16_6;
        }
        return R.drawable.wallet16_5;
    }
}
