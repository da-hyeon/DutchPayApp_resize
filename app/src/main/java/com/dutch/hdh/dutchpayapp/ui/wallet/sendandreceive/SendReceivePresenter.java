package com.dutch.hdh.dutchpayapp.ui.wallet.sendandreceive;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.SendPoint;
import com.dutch.hdh.dutchpayapp.data.util.LogUtils;
import com.dutch.hdh.dutchpayapp.databinding.FragmentSendReceiveBinding;
import com.dutch.hdh.dutchpayapp.ui.main.activity.MainActivity;
import com.dutch.hdh.dutchpayapp.ui.mygroup.directinput.MyGroup_DirectInputFragment;
import com.dutch.hdh.dutchpayapp.ui.mygroup.main.MyGroup_MainFragment;
import com.dutch.hdh.dutchpayapp.ui.mygroup.telephonedirectory.MyGroup_TelephoneDirectoryFragment;
import com.dutch.hdh.dutchpayapp.ui.view.CommonDialogView;
import com.dutch.hdh.dutchpayapp.ui.wallet.mycard.MyCardActivity;
import com.dutch.hdh.dutchpayapp.util.Trace;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.zxing.common.BitMatrix;

import java.io.UnsupportedEncodingException;
import java.util.logging.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendReceivePresenter implements SendReceiveContract.Presenter {


    private SendReceiveContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private FragmentSendReceiveBinding mFragmentSendReceiveBinding;


    public SendReceivePresenter(SendReceiveContract.View view, Context context, FragmentManager fragmentManager, FragmentSendReceiveBinding fragmentSendReceiveBinding) {
        this.mView = view;
        this.mContext = context;
        this.mFragmentManager = fragmentManager;
        this.mFragmentSendReceiveBinding = fragmentSendReceiveBinding;
    }

    @Override
    public void checkTabButton(LinearLayout linearLayout, TextView textView, boolean isCheck) {
        if (isCheck) {
            linearLayout.setBackgroundResource(R.color.buttonSelect);
            textView.setTextColor(mContext.getResources().getColor(android.R.color.white));
        } else {
            linearLayout.setBackgroundResource(R.color.buttonDefault);
            textView.setTextColor(mContext.getResources().getColor(R.color.buttonUnSelect));
        }
    }

    @Override
    public void clickBarcodeTab(View view) {
        mView.showBarcodeAndQRView(view);
    }

    @Override
    public void clickQRCodeTab(View view) {
        mView.showBarcodeAndQRView(view);
    }

    @Override
    public void clickDirectlyInputTab() {
        mView.showDirectlyInputView();
    }

    @Override
    public void clickSendTab() {
        mView.showSendView();
    }

    @Override
    public void clickReceiveTab() {
        mView.showReceiveView();
    }

    @Override
    public Bitmap getQrCodeAndBarcodeBitmap(BitMatrix matrix) {
        int height = matrix.getHeight();
        int width = matrix.getWidth();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bmp.setPixel(x, y, matrix.get(x, y) ? Color.BLACK : Color.WHITE);
            }
        }
        return bmp;
    }

//    @Override
//    public void clickContactAdd() {
//        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
//        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);
//        MyGroup_TelephoneDirectoryFragment myGroup_telephoneDirectoryFragment = new MyGroup_TelephoneDirectoryFragment();
//        fragmentTransaction.replace(R.id.flFragmentContainer, myGroup_telephoneDirectoryFragment, MyGroup_TelephoneDirectoryFragment.class.getName());
//        fragmentTransaction.addToBackStack(MyGroup_TelephoneDirectoryFragment.class.getName());
//        fragmentTransaction.commit();
//    }
//
//    @Override
//    public void clickGroupAdd() {
//        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
//        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);
//        MyGroup_MainFragment myGroup_mainFragment = new MyGroup_MainFragment();
//        fragmentTransaction.replace(R.id.flFragmentContainer, myGroup_mainFragment, MyGroup_MainFragment.class.getName());
//        fragmentTransaction.addToBackStack(MyGroup_MainFragment.class.getName());
//        fragmentTransaction.commit();
//    }
//
//    @Override
//    public void clickDirectlyInputAdd() {
//        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
//        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);
//        MyGroup_DirectInputFragment myGroup_directInputFragment = new MyGroup_DirectInputFragment();
//        fragmentTransaction.replace(R.id.flFragmentContainer, myGroup_directInputFragment, MyGroup_DirectInputFragment.class.getName());
//        fragmentTransaction.addToBackStack(MyGroup_DirectInputFragment.class.getName());
//        fragmentTransaction.commit();
//    }


    /**
     * barcodeDetector Processor 등록
     */
    @Override
    public void setProcessor(BarcodeDetector barcodeDetector, CameraSource cameraSource) {
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
                Trace.e("release");

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrcodes = detections.getDetectedItems();
                if (qrcodes.size() != 0) {
                    //qr코드 인식시 처리
                    String barcodeContents = null; // 바코드 인식 결과물
                    try {
                        barcodeContents = new String(qrcodes.valueAt(0).displayValue.getBytes("utf-8"), "ISO-8859-1");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    Trace.e(barcodeContents);
//                    barcodeDetector.release();
                    mView.showReceiveComplete(barcodeContents);
                    cameraSource.stop();

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
                mView.showReceiveView();
                mView.showCameraView(holder);
                Trace.e("테스트", "surfaceCreated");

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                Trace.e("테스트", "surfaceChanged");
                mView.showReceiveView();
                mView.showCameraView(holder);
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                Trace.e("테스트", "surfaceDestroyed");

            }
        });
    }

    @Override
    public void sendPoint(SendPoint sendPoint, View view) {

        Call<Void> setPointSend = MyApplication.getInstance()
                .getRestAdapter()
                .setPointSend(
                        sendPoint.getGiveAmount(),
                        sendPoint.getButtonnumber(),
                        sendPoint.getUsercode(),
                        sendPoint.getPointqrcode(),
                        sendPoint.getPhonenumber(),
                        sendPoint.getUsername()
                );

        setPointSend.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    mView.showGeneratorCode(response.isSuccessful(), view);
                } else {
                    mView.showGeneratorCode(response.isSuccessful(), view);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    @Override
    public void getPoint(String giftCode, SurfaceView surfaceView, CameraSource cameraSource) {
        Call<Void> getPointSend = MyApplication.getInstance()
                .getRestAdapter()
                .getPointReceive(giftCode);

        getPointSend.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
//                    if (mContext instanceof MainActivity){
//                        ((MainActivity) mContext).runOnUiThread(() -> {
//                            mView.showCommonDialog("알림", "상품권 전달이 완료되었습니다.", false);
//                        });
//                    }

                    Dialog build = new Dialog(mContext);
                    build.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    build.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    build.setContentView(new CommonDialogView(mContext, "알림", "상품권을 전달 받았습니다.", true, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (v.getId() == R.id.ivConfirm) {
                                build.dismiss();
                                mView.showSendView();
                            }
                        }
                    }));
                    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                    lp.copyFrom(build.getWindow().getAttributes());
                    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                    lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                    lp.gravity = Gravity.TOP;
                    build.show();
                    Window window = build.getWindow();
                    window.setAttributes(lp);
                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                } else {

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("테스트", t.toString() + "");
            }
        });
    }

    @Override
    public boolean isValidateAmount() {
        return !"".equals(mFragmentSendReceiveBinding.etSendPrice.getText().toString().trim());
    }
}
