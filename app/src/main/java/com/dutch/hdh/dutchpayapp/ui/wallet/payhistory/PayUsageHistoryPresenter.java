package com.dutch.hdh.dutchpayapp.ui.wallet.payhistory;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.LinearLayout;

import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.PayHistoryList;
import com.dutch.hdh.dutchpayapp.ui.wallet.payhistorydetail.PayUsageHistoryDetailFragment;
import com.dutch.hdh.dutchpayapp.util.Trace;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayUsageHistoryPresenter implements PayUsageHistoryContract.Presenter {


    private PayUsageHistoryContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;


    public PayUsageHistoryPresenter(PayUsageHistoryContract.View view, Context context, FragmentManager fragmentManager) {
        this.mView = view;
        this.mContext = context;
        this.mFragmentManager = fragmentManager;
    }

    @Override
    public void checkDateSortButton(LinearLayout linearLayout, boolean isCheck) {
        if (isCheck) {
            linearLayout.setBackgroundResource(R.color.PayUsageTabSelectColor);
        } else {
            linearLayout.setBackgroundResource(android.R.color.white);
        }
    }


    @Override
    public void getPayUsageHistoryList(String buttonType, String userCode, String payTypes) {
        Call<PayHistoryList> getPayHistory = MyApplication.getInstance()
                .getRestAdapter()
                .getUsageHistoryList(buttonType, userCode);

        getPayHistory.enqueue(new Callback<PayHistoryList>() {
            @Override
            public void onResponse(Call<PayHistoryList> call, Response<PayHistoryList> response) {
                if (response.isSuccessful()) {
                    Trace.e("getPayUsageHistoryList", "getPayUsageHistoryList");
                    ArrayList<PayHistoryList.PayHistoryListResult> mPayHistoryListResultArrayList = new ArrayList<>();

                    if (response.body().getTotallist().size() != 0) {
                        for (int i = 0; i < response.body().getTotallist().size(); i++) {
                            switch (payTypes) {
                                case "단독":
                                    if (payTypes.equals(response.body().getTotallist().get(i).getPay_Types())) {
                                        mPayHistoryListResultArrayList.add(response.body().getTotallist().get(i));
                                    }
                                    break;
                                case "더치페이":
                                    if (payTypes.equals(response.body().getTotallist().get(i).getPay_Types())) {
                                        mPayHistoryListResultArrayList.add(response.body().getTotallist().get(i));
                                    }
                                    break;
                                case "0":
                                    mPayHistoryListResultArrayList.add(response.body().getTotallist().get(i));
                                    break;
                            }
                        }
                    }
                    mView.setPayUsageHistoryList(mPayHistoryListResultArrayList);

                } else {

                }
            }

            @Override
            public void onFailure(Call<PayHistoryList> call, Throwable t) {

            }
        });
    }

    @Override
    public void clickUsagePayDetail(PayHistoryList.PayHistoryListResult payHistoryListResult) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.PAY_USAGE_HISTORY, new Gson().toJson(payHistoryListResult).toString());
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);
        PayUsageHistoryDetailFragment payUsageHistoryDetailFragment = new PayUsageHistoryDetailFragment();
        payUsageHistoryDetailFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.flFragmentContainer, payUsageHistoryDetailFragment, PayUsageHistoryDetailFragment.class.getName());
        fragmentTransaction.addToBackStack(PayUsageHistoryDetailFragment.class.getName());
        fragmentTransaction.commit();
    }
}
