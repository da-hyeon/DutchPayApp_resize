package com.dutch.hdh.dutchpayapp.ui.wallet.sendandreceive;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.data.db.SendPoint;
import com.dutch.hdh.dutchpayapp.databinding.FragmentSendReceiveBinding;
import com.dutch.hdh.dutchpayapp.util.Trace;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.util.UUID;

public class SendReceiveFragment extends BaseFragment implements SendReceiveContract.View {

    private FragmentSendReceiveBinding mBinding;
    private SendReceiveContract.Presenter mPresenter;
    private MyApplication mMyApplication;
    private BarcodeDetector mBarcodeDetector;
    private CameraSource mCameraSource;
    private SendPoint mSendPoint;
//    private ArrayList<Integer> mBarcodeArrayList = new ArrayList<Integer>();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mMyApplication = MyApplication.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_send_receive, container, false);
        mPresenter = new SendReceivePresenter(this, mMyApplication.getActivity(), getFragmentManager(), mBinding);
        initData();
        return mBinding.getRoot();
    }

    @Override
    public void initData() {

        //주기 메뉴
        mBinding.llSend.setOnClickListener(v -> {
                    mPresenter.clickSendTab();
                }
        );
        //받기 메뉴
        mBinding.llReceive.setOnClickListener(v ->
                {
                    mPresenter.clickReceiveTab();
                }
        );

        //바코드 탭
        mBinding.llBarcodeTab.setOnClickListener(v ->
                mPresenter.clickBarcodeTab(v)
        );

        //QR 코드 탭
        mBinding.llQRcodeTab.setOnClickListener(v ->
                mPresenter.clickQRCodeTab(v)
        );

        //직접입력 탭
        mBinding.llDirectlyInputTab.setOnClickListener(v ->
                mPresenter.clickDirectlyInputTab()
        );
//
//        //연락처 추가
//        mBinding.ivContactAdd.setOnClickListener(v ->
//                mPresenter.clickContactAdd()
//        );
//        //그룹 추가
//        mBinding.ivGroupAdd.setOnClickListener(v ->
//                mPresenter.clickGroupAdd()
//        );
//        //직접 추가
//        mBinding.ivDirectlyInputAdd.setOnClickListener(v ->
//                mPresenter.clickDirectlyInputAdd()
//        );

        mBarcodeDetector = new BarcodeDetector.Builder(getContext())
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();

        mCameraSource = new CameraSource
                .Builder(getContext(), mBarcodeDetector)
                .setRequestedPreviewSize(1920, 1080)
                .setAutoFocusEnabled(true)
                .setRequestedFps(10.0f)
                .build();

        mBinding.tvMyMoney.setText(String.format("%,d", mMyApplication.getUserInfo().getUserMoney()));
    }

    @Override
    public void showSendView() {
        if (mBinding.llScanView.getVisibility() == View.VISIBLE) {
            mBinding.llScanView.setVisibility(View.GONE);
            mPresenter.checkTabButton(mBinding.llReceive, mBinding.tvReceive, false);
        }
        if (mBinding.llSendLayout.getVisibility() == View.GONE) {
            mBinding.llSendLayout.setVisibility(View.VISIBLE);
            mPresenter.checkTabButton(mBinding.llSend, mBinding.tvSend, true);
        }
    }

    @Override
    public void showReceiveView() {
        if (mBinding.llSendLayout.getVisibility() == View.VISIBLE) {
            mBinding.llSendLayout.setVisibility(View.GONE);
            mPresenter.checkTabButton(mBinding.llSend, mBinding.tvSend, false);
        }
        if (mBinding.llScanView.getVisibility() == View.GONE) {
            mBinding.llScanView.setVisibility(View.VISIBLE);
            mPresenter.checkTabButton(mBinding.llReceive, mBinding.tvReceive, true);
        }
    }


    @Override
    public void showBarcodeAndQRView(View view) {

        Trace.e(mPresenter.isValidateAmount());
        if (!mPresenter.isValidateAmount()) {
            showCommonDialog("알림", "금액을 입력하세요,", false);
            mBinding.etSendPrice.requestFocus();
            return;
        }
        if (mBinding.llDirectlyInput.getVisibility() == View.VISIBLE) {
            mBinding.llDirectlyInput.setVisibility(View.GONE);
        }
        if (mBinding.llQRAndBarcode.getVisibility() == View.GONE) {
            mBinding.llQRAndBarcode.setVisibility(View.VISIBLE);
        }

        //API 요청후 바코드생성
        mSendPoint = new SendPoint();
        mSendPoint.setGiveAmount(mBinding.etSendPrice.getText().toString().trim());
        mSendPoint.setButtonnumber("1");
        mSendPoint.setUsercode(mMyApplication.getUserInfo().getUserCode());
        mSendPoint.setPointqrcode(UUID.randomUUID().toString());
        mSendPoint.setUsername("park");
        mSendPoint.setPhonenumber("1111");


        //포인트 전달 API
        mPresenter.sendPoint(mSendPoint, view);
    }

    @Override
    public void showGeneratorCode(boolean isCheck, View view) {
        try {
            if (view.getId() == mBinding.llQRcodeTab.getId()) {
                mBinding.ivQrAndBarcode.setImageBitmap(mPresenter.getQrCodeAndBarcodeBitmap(new QRCodeWriter().encode(mSendPoint.getPointqrcode(), BarcodeFormat.QR_CODE, 806, 800)));
            } else {
                mBinding.ivQrAndBarcode.setImageBitmap(mPresenter.getQrCodeAndBarcodeBitmap(new MultiFormatWriter().encode(mSendPoint.getPointqrcode(), BarcodeFormat.CODE_128, 1160, 488)));
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showDirectlyInputView() {
        if (mBinding.llQRAndBarcode.getVisibility() == View.VISIBLE) {
            mBinding.llQRAndBarcode.setVisibility(View.GONE);
        }
        if (mBinding.llDirectlyInput.getVisibility() == View.GONE) {
            mBinding.llDirectlyInput.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showCameraView(SurfaceHolder holder) {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //Request permission
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 99);
            return;
        }
        try {
            mCameraSource.start(holder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showReceiveComplete(String giftCode) {
        mPresenter.getPoint(giftCode, mBinding.svScanView, mCameraSource);
    }


    @Override
    public void showCommonDialog(String title, String content, boolean isBack) {
        super.showCommonDialog(title, content, isBack);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.setProcessor(mBarcodeDetector, mCameraSource);
        mPresenter.surfaceViewCallback(mBinding.svScanView);
    }
}
