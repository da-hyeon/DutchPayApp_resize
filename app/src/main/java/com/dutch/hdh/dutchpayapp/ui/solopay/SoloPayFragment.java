package com.dutch.hdh.dutchpayapp.ui.solopay;

import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentSoloPayBinding;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class SoloPayFragment extends BaseFragment implements SoloPayContract.View {

    private FragmentSoloPayBinding mBinding;
    private SoloPayContract.Presenter mPresenter;


    private BarcodeDetector mBarcodeDetector;
    private CameraSource mCameraSource;
    private EditText mEditText[];

    private boolean onceLifetimeEntry;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_solo_pay, container, false);
        mPresenter = new SoloPayPresenter(this, getContext(), getFragmentManager() , getActivity());
        initData();

        //스캔 클릭
        mBinding.llScan.setOnClickListener(v ->
                mPresenter.clickScan()
        );

        //결제번호 클릭
        mBinding.llPaymentNumber.setOnClickListener(v ->
                mPresenter.clickPaymentNumber()
        );

        //결제정보 클릭
        mBinding.btPaymentInfoCheck.setOnClickListener(v ->
                mPresenter.clickPaymentInfo()
        );

        //EditText 입력
        for(int i = 0; i < mEditText.length; i++){
            int index = i;
            mEditText[i].addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    mPresenter.textChangeNotification(index , mEditText[index].getText().toString());
                }
            });
        }

        mBinding.svCamera.setOnClickListener(v->
            mPresenter.clickTemporaryButton()
        );

        return mBinding.getRoot();
    }

    /**
     * 객체생성 및 데이터초기화
     */
    @Override
    public void initData() {
        mEditText = new EditText[]{
                mBinding.etPaymentNumber0,
                mBinding.etPaymentNumber1,
                mBinding.etPaymentNumber2,
                mBinding.etPaymentNumber3
        };

        mBarcodeDetector = new BarcodeDetector.Builder(getContext())
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();

        mCameraSource = new CameraSource
                .Builder(getContext(), mBarcodeDetector)
                .setRequestedPreviewSize(640, 480)
                .build();

       // mPresenter.surfaceViewCallback(mBinding.svCamera);
       // mPresenter.setProcessor(mBarcodeDetector);

    }

    /**
     * ScanView 보이기
     */
    @Override
    public void showScanView() {
        mBinding.clScanView.setVisibility(View.VISIBLE);
    }

    /**
     * PaymentNumberView 보이기
     */
    @Override
    public void showPaymentNumberView() {
        mBinding.clPaymentNumberView.setVisibility(View.VISIBLE);
    }

    /**
     * 카메라 보이기
     */
    @Override
    public void showCamera(int RequestCameraPermissionID) {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //Request permission
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, RequestCameraPermissionID);
            return;
        }
        try {
            mCameraSource.start(mBinding.svCamera.getHolder());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ScanView 숨기기
     */
    @Override
    public void hideScanView() {
        mBinding.clScanView.setVisibility(View.GONE);
    }

    /**
     * PaymentNumberView 숨기기
     */
    @Override
    public void hidePaymentNumberView() {
        mBinding.clPaymentNumberView.setVisibility(View.GONE);
    }

    /**
     * 카메라 숨기기
     */
    @Override
    public void hideCamera() {
            mCameraSource.stop();
    }

    /**
     * ScanButtonBackgroundAndTextColor 변경하기
     */
    @Override
    public void changeScanButtonBackgroundAndTextColor(boolean state) {
        if (state) {
            mBinding.llScan.setBackgroundResource(R.color.buttonSelect);
            mBinding.tvScan.setTextColor(getResources().getColor(R.color.textSelect));

        } else {
            mBinding.llScan.setBackgroundResource(R.color.buttonDefault);
            mBinding.tvScan.setTextColor(getResources().getColor(R.color.textDefault));

        }
    }

    /**
     * PaymentNumberButtonBackgroundAndTextColor 변경하기
     */
    @Override
    public void changePaymentNumberButtonBackgroundAndTextColor(boolean state) {
        if (state) {
            mBinding.llPaymentNumber.setBackgroundResource(R.color.buttonSelect);
            mBinding.tvPaymentNumber.setTextColor(getResources().getColor(R.color.textSelect));

        } else {
            mBinding.llPaymentNumber.setBackgroundResource(R.color.buttonDefault);
            mBinding.tvPaymentNumber.setTextColor(getResources().getColor(R.color.textDefault));

        }
    }

    /**
     * PaymentNumberTextViewBackgroundColor 변경하기
     */
    @Override
    public void changePaymentNumberTextViewBackgroundColor(int index, boolean state) {
        if(state){
            mEditText[index].setBackgroundResource(R.drawable.outline_round_in);
            if(index < mEditText.length-1) {
                mEditText[index+1].requestFocus();
            }
        } else {
            mEditText[index].setBackgroundResource(R.drawable.outline_round_out);
            if(index > 0) {
                mEditText[index-1].requestFocus();
            }
        }
    }

    /**
     * Presenter 에게 onPause 역할 위임.
     */
    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    /**
     * Presenter 에게 onResume 역할 위임.
     */
    @Override
    public void onResume() {
        super.onResume();
        Log.d("onResumeCheck" , "in");
        if(!onceLifetimeEntry) {
            mPresenter.surfaceViewCallback(mBinding.svCamera);
            mPresenter.setProcessor(mBarcodeDetector);
            onceLifetimeEntry = true;
         } else {
            mPresenter.onResume();
        }
    }
}
