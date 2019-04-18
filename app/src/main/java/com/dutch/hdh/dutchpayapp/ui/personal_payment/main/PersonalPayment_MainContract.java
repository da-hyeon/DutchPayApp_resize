package com.dutch.hdh.dutchpayapp.ui.personal_payment.main;

import android.view.SurfaceView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragmentContract;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public interface PersonalPayment_MainContract {
    interface View extends BaseFragmentContract.View {
        //init
        void initData();

        //show
        void showPaymentCodeView();
        void showPaymentNumberView();
        void showQRCodeView();
        void showBarCodeView();
        void showQRCodeViewScaleUp();
        void showBarCodeViewScaleUp();
        void showQRCodeViewScaleDown();
        void showBarCodeViewScaleDown();

        //hide
        void hideScanView();
        void hidePaymentNumberView();
        void hideQRCodeView();
        void hideBarCodeView();

        //change
        void changeScanButtonBackgroundAndTextColor(boolean state);
        void changePaymentNumberButtonBackgroundAndTextColor(boolean state);
        void changePaymentNumberTextViewBackgroundColor(int index, boolean state);

    }
    interface Presenter{
        //set
        void initView(ImageView qrCodeImage , ImageView barcodeImage);


        //click
        void clickPaymentCode();
        void clickPaymentNumber();
        void clickPaymentInfo(EditText mEditText[]);
        void clickScan();
        void clickQRCode();
        void clickBarCode();

        //textChangeNotice
        void textChangeNotification(int index, String num);
    }
}
