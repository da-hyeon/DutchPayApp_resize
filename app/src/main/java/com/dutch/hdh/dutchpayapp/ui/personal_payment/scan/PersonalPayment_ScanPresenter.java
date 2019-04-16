package com.dutch.hdh.dutchpayapp.ui.personal_payment.scan;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public class PersonalPayment_ScanPresenter implements PersonalPayment_ScanContract.Presenter {

    private final int REQUEST_CAMERA_PERMISSION_ID = 1001;


    private PersonalPayment_ScanContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private Activity mActivity;

    PersonalPayment_ScanPresenter(PersonalPayment_ScanContract.View mView, Context mContext, FragmentManager mFragmentManager, Activity mActivity) {
        this.mView = mView;
        this.mContext = mContext;
        this.mActivity = mActivity;
        this.mFragmentManager = mFragmentManager;
    }

    /**
     * barcodeDetector Processor 등록
     */
    @Override
    public void setProcessor(BarcodeDetector barcodeDetector, TextView textView) {
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
                        Log.d("ㅇㅇ", "인식함");
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
                mView.showCamera(REQUEST_CAMERA_PERMISSION_ID);
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
        mView.showCamera(REQUEST_CAMERA_PERMISSION_ID);
    }

}
