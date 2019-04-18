package com.dutch.hdh.dutchpayapp.ui.personal_payment.scan;

import android.view.SurfaceView;
import android.widget.TextView;

import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragmentContract;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public interface PersonalPayment_ScanContract {
    interface View extends BaseFragmentContract.View {

        void showCamera(int RequestCameraPermissionID);

        void hideCamera();
        void hideFragment();
    }
    interface Presenter{
        //Override
        void onPause();
        void onResume();

        void setProcessor(BarcodeDetector barcodeDetector , TextView textView);
        void surfaceViewCallback(SurfaceView surfaceView);
    }
}
