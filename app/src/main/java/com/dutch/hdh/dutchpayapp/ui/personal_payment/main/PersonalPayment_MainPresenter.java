package com.dutch.hdh.dutchpayapp.ui.personal_payment.main;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.ui.dialog.charge_money.ChargeMoneyDialog;
import com.dutch.hdh.dutchpayapp.ui.dialog.payment_info.Payment_InfomationDialog;
import com.dutch.hdh.dutchpayapp.ui.personal_payment.scan.PersonalPayment_ScanFragment;
import com.google.android.gms.fitness.data.MapValue;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

import static android.content.Context.WINDOW_SERVICE;

public class PersonalPayment_MainPresenter implements PersonalPayment_MainContract.Presenter {

    private PersonalPayment_MainContract.View mView;


    private Context mContext;
    private FragmentManager mFragmentManager;
    private Activity mActivity;
    private Payment_InfomationDialog mPayment_InfomationDialog;
    private ChargeMoneyDialog mChargeMoneyDialog;
    private MyApplication mMyApplication;
    private boolean qrcodeState;
    private boolean barcodeState;
    /**
     * 생성자
     */
    PersonalPayment_MainPresenter(PersonalPayment_MainContract.View mView, Context mContext, FragmentManager mFragmentManager, Activity mActivity) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        this.mActivity = mActivity;
        mPayment_InfomationDialog = new Payment_InfomationDialog(this.mContext);
        mChargeMoneyDialog = new ChargeMoneyDialog(this.mContext);
        mMyApplication = MyApplication.getInstance();
        qrcodeState = true;
        barcodeState = true;
    }

    /**
     * View 세팅
     */
    @Override
    public void initView(ImageView qrCodeImage , ImageView barcodeImage) {
        WindowManager manager = (WindowManager) mContext.getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int smallerDimension = width < height ? width : height;
        smallerDimension = smallerDimension * 3 / 4;

        //QR코드 생성
        QRGEncoder qrgEncoder = new QRGEncoder(
                1+"", null,
                QRGContents.Type.TEXT,
                smallerDimension);
        try {
            Bitmap bitmap = qrgEncoder.encodeAsBitmap();
            qrCodeImage.setImageBitmap(bitmap);
        } catch (WriterException e) {
            Log.v("errorCode", e.toString());
        }

        //바코드 생성
        Bitmap barcode = createBarcode("8000 1234 5678 9012 3456 7890");
        barcodeImage.setImageBitmap(barcode);
        barcodeImage.invalidate();
    }



    /**
     * 결제코드 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickPaymentCode() {
        mView.showPaymentCodeView();
        mView.hidePaymentNumberView();
        mView.changeScanButtonBackgroundAndTextColor(true);
        mView.changePaymentNumberButtonBackgroundAndTextColor(false);
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
        if(mMyApplication.getPersonalPaymentInformation().getAmount() < mMyApplication.getUserInfo().getUserMoney()) {
            mPayment_InfomationDialog.show();
        } else {
            mChargeMoneyDialog.show();
        }
    }

    /**
     * 스캔하기 버튼 이벤트 처리
     */
    @Override
    public void clickScan() {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

        PersonalPayment_ScanFragment personalPayment_scanFragment = new PersonalPayment_ScanFragment();
        fragmentTransaction.replace(R.id.flFragmentContainer, personalPayment_scanFragment , PersonalPayment_ScanFragment.class.getName());
        fragmentTransaction.addToBackStack(PersonalPayment_ScanFragment.class.getName());
        fragmentTransaction.commit();
    }

    /**
     * QR 코드 버튼 이벤트 처리
     */
    @Override
    public void clickQRCode() {

        //바코드 이미지가 떠있으면
        if(qrcodeState){
            qrcodeState = false;
            mView.hideBarCodeView();
            mView.showQRCodeViewScaleUp();
        }

        //클릭한 이미지가 떠있지 않으면
        else {
            qrcodeState = true;
            mView.showBarCodeView();
            mView.showQRCodeViewScaleDown();
        }
    }

    /**
     * BarCode 버튼 이벤트 처리
     */
    @Override
    public void clickBarCode() {

        //QRCode 이미지가 떠있으면
        if(barcodeState){
            barcodeState = false;
            mView.hideQRCodeView();
            mView.showBarCodeViewScaleUp();
        }

        //QRCode 이미지가 떠있지 않으면
        else {
            barcodeState = true;
            mView.showQRCodeView();
            mView.showBarCodeViewScaleDown();
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

    /**
     * barcode 생성
     */
    private Bitmap createBarcode(String code){
        Bitmap bitmap = null;

        MultiFormatWriter gen = new MultiFormatWriter();
        try {
            final int WIDTH  = 840;
            final int HEIGHT = 160;
            BitMatrix bytemp = gen.encode(code , BarcodeFormat.CODE_128 , WIDTH , HEIGHT);
            bitmap = Bitmap.createBitmap(WIDTH , HEIGHT , Bitmap.Config.ARGB_8888);
            for(int i = 0; i < WIDTH; i++){
                for(int j = 0; j < HEIGHT; j++){
                    bitmap.setPixel(i , j , bytemp.get(i , j) ? Color.BLACK : Color.WHITE);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return bitmap;
    }
}
