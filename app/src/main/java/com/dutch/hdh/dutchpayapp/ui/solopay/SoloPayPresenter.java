package com.dutch.hdh.dutchpayapp.ui.solopay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

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

    /**
     * 생성자
     */
    public SoloPayPresenter(SoloPayContract.View mView, Context mContext, FragmentManager mFragmentManager, Activity mActivity) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        this.mActivity = mActivity;
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
    public void setProcessor(BarcodeDetector barcodeDetector) {
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrcodes = detections.getDetectedItems();
                if (qrcodes.size() != 0) {
                    //qr코드 인식시 처리
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
        mView.changeScanButtonBackgroundColor(true);
        mView.changePaymentNumberButtonBackgroundColor(false);
        mView.changeScanButtonTextColor(true);
        mView.changePaymentNumberButtonTextColor(false);

        mView.showCamera(RequestCameraPermissionID);
    }

    /**
     * 결제번호 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickPaymentNumber() {
        mView.showPaymentNumberView();
        mView.hideScanView();
        mView.changeScanButtonBackgroundColor(false);
        mView.changePaymentNumberButtonBackgroundColor(true);
        mView.changeScanButtonTextColor(false);
        mView.changePaymentNumberButtonTextColor(true);

        mView.hideCamera();
    }

    /**
     * 결제정보 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickPaymentInfo() {

    }

    /**
     * 임시 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickTemporaryButton() {
        mView.hideCamera();

        Intent intent = new Intent(mContext, Payment_InfomationDialog.class);
        intent.putExtra("amount" , 200000);
        mView.showPayment_InfomationDialog(intent);
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
