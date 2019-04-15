package com.dutch.hdh.dutchpayapp.ui.mypage.refund;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ListView;

import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.adapter.Listview_AccountAdapter;
import com.dutch.hdh.dutchpayapp.adapter.Listview_MyGroupAdapter;
import com.dutch.hdh.dutchpayapp.data.db.AccountList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPage_SelectRefundAccountPresenter implements MyPage_SelectRefundAccountContract.Presenter{
    private MyPage_SelectRefundAccountContract.View mView;
    private Context mContext;
    private MyApplication mMyApplication;
    private Listview_AccountAdapter mListview_AccountAdapter;

    public MyPage_SelectRefundAccountPresenter(MyPage_SelectRefundAccountContract.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
        mMyApplication = MyApplication.getInstance();
    }

    @Override
    public void initView(ListView listView) {
        Call<AccountList> changePassword = MyApplication
                .getRestAdapter()
                .selectAccount(mMyApplication.getUserInfo().getUserCode());

        changePassword.enqueue(new Callback<AccountList>() {
            @Override
            public void onResponse(@NonNull Call<AccountList> call, @NonNull Response<AccountList> response) {
                if (response.isSuccessful()) {
                    AccountList accountList = response.body();
                    int count = accountList.getAccountArrayList().size();
                    mListview_AccountAdapter = new Listview_AccountAdapter( mContext, accountList.getAccountArrayList());
                    listView.setAdapter(mListview_AccountAdapter);
                    for(int i = 0; i < count; i++){
                        if(accountList.getAccountArrayList().get(i).getAccountChoice().equals("0")){
                            //대표계좌라면
                            mView.changeBankImage(
                                    Constants.backImageID(
                                            Integer.parseInt(
                                                    accountList.getAccountArrayList().get(i).getAccountTypeCode()
                                            )
                                    )
                            );
                            mView.changeBankBackground(
                                    Constants.backColorID(
                                            Integer.parseInt(
                                                    accountList.getAccountArrayList().get(i).getAccountTypeCode()
                                            )
                                    )
                            );
                            mView.changeAccountNumber(accountList.getAccountArrayList().get(i).getAccountNumber());

                            return;
                        }
                    }
                } else {
                    mView.showFailDialog("실패", "데이터 전송에 실패했습니다.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<AccountList> call, @NonNull Throwable t) {
                mView.showFailDialog("실패", t.getMessage());
            }
        });
    }

    @Override
    public void clickCancel() {

    }
}
