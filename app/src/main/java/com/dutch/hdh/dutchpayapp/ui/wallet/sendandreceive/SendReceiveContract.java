package com.dutch.hdh.dutchpayapp.ui.wallet.sendandreceive;


import android.graphics.Bitmap;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dutch.hdh.dutchpayapp.base.activity.BaseActivityContract;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragmentContract;
import com.dutch.hdh.dutchpayapp.data.db.SendPoint;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.zxing.common.BitMatrix;


public interface SendReceiveContract extends BaseFragmentContract {
    interface View extends BaseFragmentContract.View{

        //init
        void initData();

        //주기 화면
        void showSendView();
        //받기 화면
        void showReceiveView();
        //바코드 화면
        void showBarcodeAndQRView(android.view.View view);
        //직접입력
        void showDirectlyInputView();
        //카메라
        void showCameraView(SurfaceHolder holder);
    }

    interface Presenter {

        //주기
        void clickSendTab();
        //받기
        void clickReceiveTab();
        //주기받기 탭버튼 체크
        void checkTabButton(LinearLayout linearLayout, TextView textView, boolean isCheck);
        //바코드 탭
        void clickBarcodeTab(android.view.View view);
        //QR 코드 탭
        void clickQRCodeTab(android.view.View view);
        //직접입력
        void clickDirectlyInputTab();
        //바코드 QR 코드 가져오기
        Bitmap getQrCodeAndBarcodeBitmap(BitMatrix matrix);
        //연락처 클릭
        void clickContactAdd();
        //연락처 클릭
        void clickGroupAdd();
        //연락처 클릭
        void clickDirectlyInputAdd();

        void setProcessor(BarcodeDetector barcodeDetector);
        void surfaceViewCallback(SurfaceView surfaceView);
        //포인트 보내기
        void sendPoint(SendPoint sendPoint);

        boolean isValidateAmount();
    }
}
