package com.dutch.hdh.dutchpayapp.ui.wallet.myaccount;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.AccountRegisterList;
import com.dutch.hdh.dutchpayapp.ui.wallet.addaccount.AddAccountActivity;
import com.dutch.hdh.dutchpayapp.util.Trace;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyAccountPresenter implements MyAccountContract.Presenter {

    private Context mContext;
    private MyAccountContract.View mView;
    private MyApplication mMyApplication;

    public MyAccountPresenter(Context context, MyAccountContract.View view) {
        this.mContext = context;
        this.mView = view;
        mMyApplication = MyApplication.getInstance();
    }


    @Override
    public void clickCancel() {
        if (mContext instanceof MyAccountActivity) {
            ((MyAccountActivity) mContext).onBackPressed();
        }
    }

    @Override
    public void clickAccountAdd() {
        Log.e("clickAccountAdd", "clickAccountAdd");
        Intent intent = new Intent(mContext, AddAccountActivity.class);
        mContext.startActivity(intent);
        if (mContext instanceof MyAccountActivity) {
            ((MyAccountActivity) mContext).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
    }

    @Override
    public void getRegisterAccountList() {
        Call<AccountRegisterList> cardRegisterList = MyApplication.getInstance()
                .getRestAdapter()
                .getAccountRegisterList(mMyApplication.getUserInfo().getUserCode());

        cardRegisterList.enqueue(new Callback<AccountRegisterList>() {
            @Override
            public void onResponse(Call<AccountRegisterList> call, Response<AccountRegisterList> response) {
                if (response.isSuccessful()) {
                    mView.setRegisterAccountList(response.body().getAccountlist());
                }
            }

            @Override
            public void onFailure(Call<AccountRegisterList> call, Throwable t) {

            }
        });
    }

    @Override
    public void setRepresentativeAccount(String mainAccountCode, String subAccountCode) {
        Call<Void> accountRegisterList = MyApplication.getInstance()
                .getRestAdapter()
                .setAccountRepresentativeCard(mainAccountCode, subAccountCode);

        accountRegisterList.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.body() == null) {
                    getRegisterAccountList();
                    Trace.e("setRepresentativeAccount", "setRepresentativeAccount");
                } else {
                    Trace.e("setRepresentativeAccount", "setRepresentativeAccount");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    @Override
    public void setAccountDelete(String accountCode) {
        Call<Void> accountDelete = MyApplication.getInstance()
                .getRestAdapter()
                .setAccountDelete(accountCode);
        accountDelete.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (response.isSuccessful()) {
                    getRegisterAccountList();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }

    @Override
    public int getBankImage(String accountTypeCode) {
        switch (accountTypeCode) {
            case "0":
                return R.drawable.bs_bank;
            case "1":
                return R.drawable.citi_bank;
            case "2":
                return R.drawable.dg_bank;
            case "3":
                return R.drawable.gj_bank;
            case "4":
                return R.drawable.gn_bank;
            case "5":
                return R.drawable.hana_bank;
            case "6":
                return R.drawable.ibk_bank;
            case "7":
                return R.drawable.jb_bank;
            case "8":
                return R.drawable.jj_bank;
            case "9":
                return R.drawable.k_bank;
            case "10":
                return R.drawable.kakao_bank;
            case "11":
                return R.drawable.kb_bank1;
            case "12":
                return R.drawable.kdb_bank;
            case "13":
                return R.drawable.nh_bank;
            case "14":
                return R.drawable.sc_bank;
            case "15":
                return R.drawable.sh_bank;
            case "16":
                return R.drawable.shinhan_bank;
            case "17":
                return R.drawable.wr_bank;
        }
        return 0;
    }

    @Override
    public String getBankBackgroundColor(String accountTypeCode) {
        switch (accountTypeCode) {
            case "0":
                return "#0165b3";
            case "1":
                return "#f7b500";
            case "2":
                return "#0f479e";
            case "3":
                return "#ea1d24";
            case "4":
                return "#212021";
            case "5":
                return "#288479";
            case "6":
                return "#ea1d24";
            case "7":
                return "#1899d3";
            case "8":
                return "#f7b500";
            case "9":
                return "#288479";
            case "10":
                return "#212021";
        }
        return "#ffffff";
    }

    @Override
    public boolean isRepresentativeAccountNumber(String accountChoice) {
        if ("0".equals(accountChoice)) {
            return true;
        }
        return false;
    }
}
