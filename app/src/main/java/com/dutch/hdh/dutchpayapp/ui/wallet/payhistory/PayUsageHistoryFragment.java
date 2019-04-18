package com.dutch.hdh.dutchpayapp.ui.wallet.payhistory;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.data.db.SendPoint;
import com.dutch.hdh.dutchpayapp.databinding.FragmentSendReceiveBinding;
import com.dutch.hdh.dutchpayapp.databinding.FragmentUsageHistoryBinding;
import com.dutch.hdh.dutchpayapp.ui.wallet.sendandreceive.SendReceiveContract;
import com.dutch.hdh.dutchpayapp.ui.wallet.sendandreceive.SendReceivePresenter;
import com.dutch.hdh.dutchpayapp.util.Trace;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;

public class PayUsageHistoryFragment extends BaseFragment implements PayUsageHistoryContract.View {

    private FragmentUsageHistoryBinding mBinding;
    private PayUsageHistoryContract.Presenter mPresenter;
    private MyApplication mMyApplication;
    private BarcodeDetector mBarcodeDetector;
    private CameraSource mCameraSource;
    private SendPoint mSendPoint;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mMyApplication = MyApplication.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_usage_history, container, false);
        mPresenter = new PayUsageHistoryPresenter(this, mMyApplication.getActivity(), getFragmentManager());
        initData();
        return mBinding.getRoot();
    }

    @Override
    public void initData() {

        //전체보기
        mBinding.ivPayAllStatus.setOnClickListener(v ->
                mPresenter.clickPayAllStatus()
                );
        //더치페이 항목만 보기
        mBinding.ivPayDutchPayStatus.setOnClickListener(v ->
                mPresenter.clickPayDutchPayStatus()
        );
        //개인결제 항목만 보기
        mBinding.ivPaySoloPayStatus.setOnClickListener(v->
                mPresenter.clickPaySoloPayStatus()
                );
        //주기/받기 항목만 보기
        mBinding.ivPaySendReceiveStatus.setOnClickListener(v->
                mPresenter.clickPaySendReceiveStatus()
        );

        //1주
        mBinding.llWeek.setOnClickListener(v ->
                mPresenter.clickOneWeekPayList()
                );

        //1개월
        mBinding.llOneMonth.setOnClickListener(v ->
                mPresenter.clickOneMonthPayList()
        );

        //3개월
        mBinding.llThreeMonth.setOnClickListener(v ->
                mPresenter.clickThreeMonthPayList()
        );

        //전체
        mBinding.llWeek.setOnClickListener(v ->
                mPresenter.clickAllPayList()
        );

        mBinding.tvMyMoney.setText(String.format("%,d", mMyApplication.getUserInfo().getUserMoney()));
    }

    @Override
    public void showCommonDialog(String title, String content, boolean isBack) {
        super.showCommonDialog(title, content, isBack);
    }

}
