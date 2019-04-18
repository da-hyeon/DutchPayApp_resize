package com.dutch.hdh.dutchpayapp.ui.solopay;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.ui.dialog.charge_money.ChargeMoneyDialog;
import com.dutch.hdh.dutchpayapp.ui.dialog.payment_info.Payment_InfomationDialog;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public class SoloPayPresenter implements SoloPayContract.Presenter {

    private SoloPayContract.View mView;

    private final int RequestCameraPermissionID = 1001;

    private Context mContext;
    private FragmentManager mFragmentManager;
    private Activity mActivity;
    private Payment_InfomationDialog mPayment_InfomationDialog;
    private ChargeMoneyDialog mChargeMoneyDialog;
    private MyApplication mMyApplication;
    /**
     * 생성자
     */
    SoloPayPresenter(SoloPayContract.View mView, Context mContext, FragmentManager mFragmentManager, Activity mActivity) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        this.mActivity = mActivity;
        mPayment_InfomationDialog = new Payment_InfomationDialog(this.mContext);
        mChargeMoneyDialog = new ChargeMoneyDialog(this.mContext);
        mMyApplication = MyApplication.getInstance();
    }

    /**
     * onPause 처리
     */
    @Override
    public void onPause() {
        mView.hideCamera();
    }

    /**
     * onResume 처리
     */
    @Override
    public void onResume() {
        mView.showCamera(RequestCameraPermissionID);
    }


    /**
     * barcodeDetector Processor 등록
     */
    @Override
    public void setProcessor(BarcodeDetector barcodeDetector , TextView textView) {
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrCodes = detections.getDetectedItems();
                if (qrCodes.size() != 0) {

                    mActivity.runOnUiThread(() -> {
                        mView.hideCamera();
                        Log.d("ㅇㅇ" , "인식함");
                        mView.showSuccessDialog("QR코드 인식 성공", "QR CODE : " + qrCodes.valueAt(0).displayValue);
                    });
                }
            }
        });
    }

    /**
     * surfaceViewCallback 처리
     */
    @Override
    public void surfaceViewCallback(SurfaceView surfaceView) {
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                mView.showCamera(RequestCameraPermissionID);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                //mCameraSource.stop();
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                mView.hideCamera();
            }
        });
    }


    /**
     * 스캔 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickScan() {

        mView.showScanView();
        mView.hidePaymentNumberView();
        mView.changeScanButtonBackgroundAndTextColor(true);
        mView.changePaymentNumberButtonBackgroundAndTextColor(false);
        mView.showCamera(RequestCameraPermissionID);
    }

    /**
     * 결제번호 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickPaymentNumber() {
        mView.showPaymentNumberView();
        mView.hideScanView();
        mView.changeScanButtonBackgroundAndTextColor(false);
        mView.changePaymentNumberButtonBackgroundAndTextColor(true);
        mView.hideCamera();
    }

    /**
     * 결제정보 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickPaymentInfo() {
        //if(mMyApplication.getPersonalPaymentInformation().getAmount() < mMyApplication.getUserInfo().getUserMoney()) {
            mPayment_InfomationDialog.show();
       // } else {
       //     mChargeMoneyDialog.show();
        //}
    }

    /**
     * 임시 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickTemporaryButton() {
        mView.hideCamera();
        if(mMyApplication.getPersonalPaymentInformation().getAmount() < mMyApplication.getUserInfo().getUserMoney()) {
            mPayment_InfomationDialog.show();
        } else {
            mChargeMoneyDialog.show();
        }
    }

    /**
     * EditText text 변경 이벤트 처리
     */
    @Override
    public void textChangeNotification(int index, String num) {
        if (num.equals("")) {
            mView.changePaymentNumberTextViewBackgroundColor(index, false);
        } else {
            mView.changePaymentNumberTextViewBackgroundColor(index, true);
        }
    }
}
