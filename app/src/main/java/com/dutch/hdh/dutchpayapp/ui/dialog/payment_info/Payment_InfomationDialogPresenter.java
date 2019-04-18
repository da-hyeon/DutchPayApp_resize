package com.dutch.hdh.dutchpayapp.ui.dialog.payment_info;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.ui.personal_payment.scan.PersonalPayment_ScanContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Payment_InfomationDialogPresenter implements Payment_InfomationDialogContract.Presenter {

    private Payment_InfomationDialogContract.View mView;
    private PersonalPayment_ScanContract.View mScanView;
    private Context mContext;
    private Activity mActivity;

    private MyApplication mMyApplication;
    private String mProductCode;
    private int mProductAmount;

    private boolean isSuccessPayment;

    /**
     * 생성자
     */
    Payment_InfomationDialogPresenter(Payment_InfomationDialogContract.View mView, Context mContext , String mProductCode , int mProductAmount) {
        this.mView = mView;
        this.mContext = mContext;
        this.mProductCode = mProductCode;
        this.mProductAmount = mProductAmount;

        mMyApplication = MyApplication.getInstance();
    }

    /**
     * scanView 받아오기
     */
    @Override
    public void setScanView(PersonalPayment_ScanContract.View mScanView) {
        this.mScanView = mScanView;
    }

    /**
     * View 세팅
     */
    @Override
    public void initVIew() {
        mView.changeMyMoney(mMyApplication.getUserInfo().getUserMoney());
        mView.changePaymentAmount(mProductAmount);

    }

    /**
     * 다이얼로그 종료 이벤트 처리
     */
    @Override
    public void onDismiss() {
        if (mScanView != null && !isSuccessPayment) {
            mScanView.showCamera(1001);
        }
    }

    /**
     * 확인버튼 처리
     */
    @Override
    public void clickOK() {


        Call<String> productPayment;
        productPayment = MyApplication
                .getRestAdapter()
                .updateQRCodePayment(mProductCode,
                        mMyApplication.getUserInfo().getUserCode());


        productPayment.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful()) {
                    //결제 성공
                    isSuccessPayment = true;

                    //오류검출
                    if (response.body() != null && response.body().equals("alreadyPayment")) {
                        mView.showFailDialog("실패", "해당 상품은 결제가 완료 되었습니다.");
                        return;
                    }

                    mView.showSuccessDialog("성공", "결제 완료");
                    mMyApplication.getUserInfo().setUserMoney(mMyApplication.getUserInfo().getUserMoney() - mProductAmount);

                }
                //DB 접근 오류
                else {
                    mView.showFailDialog("실패", "해당 상품을 찾을 수 없습니다.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                //서버통신 오류
                mView.showFailDialog("실패", "서버와 통신할 수 없습니다.");
            }
        });
    }
}
