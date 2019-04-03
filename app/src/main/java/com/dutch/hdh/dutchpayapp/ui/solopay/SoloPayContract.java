package com.dutch.hdh.dutchpayapp.ui.solopay;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.SurfaceView;

import com.dutch.hdh.dutchpayapp.base.BaseContract;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public interface SoloPayContract {
    interface View extends BaseContract.View {
        //init
        void initData();

        //show
        void showScanView();
        void showPaymentNumberView();
        void showCamera(int RequestCameraPermissionID);
        void showPayment_InfomationDialog(Intent intent);

        //hide
        void hideScanView();
        void hidePaymentNumberView();
        void hideCamera();

        //change
        void changeScanButtonBackgroundColor(boolean state);
        void changePaymentNumberButtonBackgroundColor(boolean state);
        void changeScanButtonTextColor(boolean state);
        void changePaymentNumberButtonTextColor(boolean state);
        void changePaymentNumberTextViewBackgroundColor(int index, boolean state);

    }
    interface Presenter{

        //Override
        void onPause();
        void onResume();

        //set
        void setProcessor(BarcodeDetector barcodeDetector);
        void surfaceViewCallback(SurfaceView surfaceView);

        //click
        void clickScan();
        void clickPaymentNumber();
        void clickPaymentInfo();
        void clickTemporaryButton();

        //textChangeNotice
        void textChangeNotification(int index, String num);
    }
}
