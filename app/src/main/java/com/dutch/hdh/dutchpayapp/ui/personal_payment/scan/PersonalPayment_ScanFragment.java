package com.dutch.hdh.dutchpayapp.ui.personal_payment.scan;

import android.Manifest;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentPersonalPaymentScanBinding;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.util.Objects;

public class PersonalPayment_ScanFragment extends BaseFragment implements PersonalPayment_ScanContract.View {

    private FragmentPersonalPaymentScanBinding mBinding;
    private PersonalPayment_ScanContract.Presenter mPresenter;

    private CameraSource mCameraSource;
    private BarcodeDetector mBarcodeDetector;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_personal_payment_scan, container, false);
        mPresenter = new PersonalPayment_ScanPresenter(this, getContext(), getFragmentManager(), getActivity());

        SurfaceView temp = mBinding.svCamera;
        ViewGroup.LayoutParams lp = temp.getLayoutParams();
        lp.width = getResources().getDisplayMetrics().widthPixels;
        lp.height = (int) (lp.width * 0.8f);

        initData();

        return mBinding.getRoot();
    }

    /**
     * 객체생성 및 데이터초기화
     */

    private void initData() {
        mBarcodeDetector = new BarcodeDetector.Builder(getContext())
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();

        mCameraSource = new CameraSource
                .Builder(Objects.requireNonNull(getContext()), mBarcodeDetector)
                .setRequestedPreviewSize(640, 480)
                .build();

        mBinding.tvTitle.setSelected(true);

        mPresenter.setProcessor(mBarcodeDetector , mBinding.tvTitle);
        mPresenter.surfaceViewCallback(mBinding.svCamera);
    }

    /**
     * 카메라 보이기
     */
    @Override
    public void showCamera(int RequestCameraPermissionID) {
        if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //Request permission
            ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()), new String[]{Manifest.permission.CAMERA}, RequestCameraPermissionID);
            return;
        }
        try {
            mCameraSource.start(mBinding.svCamera.getHolder());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 카메라 숨기기
     */
    @Override
    public void hideCamera() {
        mCameraSource.stop();
    }

    /**
     * Presenter 에게 onPause 역할 위임.
     */
    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
