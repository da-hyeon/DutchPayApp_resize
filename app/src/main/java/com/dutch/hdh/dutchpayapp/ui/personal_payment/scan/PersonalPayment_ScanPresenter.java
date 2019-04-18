package com.dutch.hdh.dutchpayapp.ui.personal_payment.scan;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.data.db.Product;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonalPayment_ScanPresenter implements PersonalPayment_ScanContract.Presenter {

    private final int REQUEST_CAMERA_PERMISSION_ID = 1001;


    private PersonalPayment_ScanContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private Activity mActivity;

    private boolean cameraResumeCheck;

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
                        cameraResumeCheck = true;
                        mView.hideCamera();
                        //서버통신 후 데이터가 있다면 Dialog 띄우기
                        Call<Product> productInfo = MyApplication
                                .getRestAdapter()
                                .selectQRCodeProduct(qrCodes.valueAt(0).displayValue);

                        productInfo.enqueue(new Callback<Product>() {
                            @Override
                            public void onResponse(@NonNull Call<Product> call, @NonNull Response<Product> response) {
                                if (response.isSuccessful()) {
                                    if (response.body() != null) {
                                        if (response.body().getProductName() != null) {
                                            Product product = response.body();
                                            MyApplication myApplication = MyApplication.getInstance();
                                            if (!myApplication.getPaymentDialog(mContext).isShowing()) {
                                                myApplication.getPaymentDialog(mContext).setDialog(mView, mFragmentManager, product.getProductCode(), product.getProductPrice());
                                                myApplication.getPaymentDialog(mContext).show();
                                            }
                                        } else {
                                            mView.showFailDialog("실패", "해당 상품을 찾을 수 없습니다.");
                                        }
                                    }
                                } else {
                                    //DB 접근 오류 (해당 상품을 찾지 못함)
                                    mView.showFailDialog("실패", "해당 상품을 찾을 수 없습니다.");
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<Product> call, @NonNull Throwable t) {
                                //서버통신 오류
                                mView.showFailDialog("실패", "서버와 통신할 수 없습니다.");
                            }
                        });

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
        if (cameraResumeCheck) {
            mView.showCamera(REQUEST_CAMERA_PERMISSION_ID);
        }
    }

}
