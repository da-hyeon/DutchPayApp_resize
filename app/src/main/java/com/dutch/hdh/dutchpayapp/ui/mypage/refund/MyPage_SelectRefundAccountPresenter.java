package com.dutch.hdh.dutchpayapp.ui.mypage.refund;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ListView;

import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.adapter.Listview_AccountAdapter;
import com.dutch.hdh.dutchpayapp.data.db.AccountList;
import com.dutch.hdh.dutchpayapp.ui.mypage.withdrawal.MyPage_WithdrawalActivity;
import com.kinda.alert.KAlertDialog;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPage_SelectRefundAccountPresenter implements MyPage_SelectRefundAccountContract.Presenter {

    private MyPage_SelectRefundAccountContract.View mView;
    private Context mContext;
    private MyApplication mMyApplication;
    private Listview_AccountAdapter mListview_AccountAdapter;

    private AccountList mAccountList;

    private int representativeAccountTypeCode;
    private String representativeAccountTypeName;
    private String representativeAccountNumber;


    /**
     * 생성자
     */
    MyPage_SelectRefundAccountPresenter(MyPage_SelectRefundAccountContract.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
        mMyApplication = MyApplication.getInstance();
    }

    /**
     * 뷰 초기화
     */
    @Override
    public void initView(ListView listView) {
        Call<AccountList> changePassword = MyApplication
                .getRestAdapter()
                .selectAccount(mMyApplication.getUserInfo().getUserCode());

        changePassword.enqueue(new Callback<AccountList>() {
            @Override
            public void onResponse(@NonNull Call<AccountList> call, @NonNull Response<AccountList> response) {
                if (response.isSuccessful()) {
                    mAccountList = response.body();
                    int count = 0;
                    if (mAccountList != null) {
                        count = mAccountList.getAccountArrayList().size();
                    }

                    for (int i = 0; i < count; i++) {
                        //대표계좌 찾아내기
                        if (mAccountList.getAccountArrayList().get(i).getAccountChoice().equals("0")) {

                            //계좌정보 변수 초기화
                            int accountTypeCode = Integer.parseInt(mAccountList.getAccountArrayList().get(i).getAccountTypeCode());
                            String accountTypeName = mAccountList.getAccountArrayList().get(i).getAccountTypeName();
                            String accountNumber = mAccountList.getAccountArrayList().get(i).getAccountNumber();

                            //은행 이미지 변경 요청
                            mView.changeBankImage(Constants.backImageID(accountTypeCode));

                            //은행컬러 변경 요청
                            mView.changeBankBackground(Constants.backColorID(accountTypeCode));

                            //계좌번호 변경 요청
                            mView.changeAccountNumber(accountNumber);

                            //대표계좌 정보 저장
                            representativeAccountTypeCode = accountTypeCode;
                            representativeAccountTypeName = accountTypeName;
                            representativeAccountNumber = accountNumber;

                            //대표계좌 삭제 후 adapter 초기화
                            mAccountList.getAccountArrayList().remove(i);
                            mListview_AccountAdapter = new Listview_AccountAdapter(mView, mContext, mAccountList.getAccountArrayList());

                            //ListView 세팅
                            listView.setAdapter(mListview_AccountAdapter);
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

    /**
     * 대표계좌 클릭 이벤트 처리
     */
    @Override
    public void clickRepresentativeAccount() {

        new KAlertDialog(Objects.requireNonNull(mContext), KAlertDialog.WARNING_TYPE)
                .setTitleText("주의")
                .setContentText(representativeAccountTypeName + " " + representativeAccountNumber + "\n환불계좌를 위의 계좌로 변경하시겠습니까?")
                .setConfirmText("확인")
                .setConfirmClickListener(sDialog -> {
                    sDialog.dismissWithAnimation();

                    //액티비티 이동
                    Intent intent = new Intent(mContext, MyPage_WithdrawalActivity.class);
                    intent.putExtra(Constants.ACCOUNT_TYPE_CODE, representativeAccountTypeCode);
                    intent.putExtra(Constants.ACCOUNT_NUMBER, representativeAccountNumber);
                    mContext.startActivity(intent);
                    mView.finish();

                })
                .setCancelText("취소")
                //다이얼로그 닫기
                .setCancelClickListener(KAlertDialog::dismissWithAnimation)
                .show();
    }

    /**
     * 뒤로가기 처리
     */
    @Override
    public void clickCancel() {
        mView.finish();
    }
}
