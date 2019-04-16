package com.dutch.hdh.dutchpayapp.ui.mypage.withdrawal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.AccountList;
import com.dutch.hdh.dutchpayapp.ui.mypage.refund.MyPage_SelectRefundAccountActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPage_WithdrawalPresenter implements MyPage_WithdrawalContract.Presenter {
    private MyPage_WithdrawalContract.View mView;
    private Context mContext;
    private MyApplication mMyApplication;
    private Activity mActivity;

    /**
     * 생성자
     */
    MyPage_WithdrawalPresenter(MyPage_WithdrawalContract.View mView, Context mContext, Activity mActivity) {
        this.mView = mView;
        this.mContext = mContext;
        this.mActivity = mActivity;
        mMyApplication = MyApplication.getInstance();
    }

    /**
     * 뷰 초기화
     */
    @Override
    public void initView(Intent intent) {
        int accountTypeCode = intent.getIntExtra(Constants.ACCOUNT_TYPE_CODE , 100);
        String accountNumber = intent.getStringExtra(Constants.ACCOUNT_NUMBER);

        //선택한 계좌가 없을때
        if(accountTypeCode == 100){
            //대표계좌설정
            Call<AccountList> changePassword = MyApplication
                    .getRestAdapter()
                    .selectAccount(mMyApplication.getUserInfo().getUserCode());

            changePassword.enqueue(new Callback<AccountList>() {
                @Override
                public void onResponse(@NonNull Call<AccountList> call, @NonNull Response<AccountList> response) {
                    if (response.isSuccessful()) {
                        AccountList accountList = response.body();
                        int count = 0;
                        if (accountList != null) {
                            count = accountList.getAccountArrayList().size();
                        }
                        //대표계좌 찾아내기
                        for (int i = 0; i < count; i++) {
                            if (accountList.getAccountArrayList().get(i).getAccountChoice().equals("0")) {

                                //은행이미지 변경 요청
                                mView.changeBankImage(
                                        Constants.backImageID(
                                                Integer.parseInt(
                                                        accountList.getAccountArrayList().get(i).getAccountTypeCode()
                                                )
                                        )
                                );

                                //은행컬러 변경 요청
                                mView.changeBankBackground(
                                        Constants.backColorID(
                                                Integer.parseInt(
                                                        accountList.getAccountArrayList().get(i).getAccountTypeCode()
                                                )
                                        )
                                );

                                //계좌번호 변경 요청
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
        //선택한 계좌가 있을때
        else {
            //은행이미지 변경 요청
            mView.changeBankImage(Constants.backImageID(accountTypeCode));
            //은행 컬러 변경 요청
            mView.changeBankBackground(Constants.backColorID(accountTypeCode));
            //계좌번호 변경 요청
            mView.changeAccountNumber(accountNumber);
        }
    }

    /**
     * 대표계좌 클릭 이벤트 처리
     */
    @Override
    public void clickChangeRefundAccount() {
        //액티비티 이동
        Intent intent = new Intent(mContext, MyPage_SelectRefundAccountActivity.class);
        mContext.startActivity(intent);
        mView.finish();
        mActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    /**
     * 뒤로가기 처리
     */
    @Override
    public void clickCancel() {
        mView.finish();
    }
}
