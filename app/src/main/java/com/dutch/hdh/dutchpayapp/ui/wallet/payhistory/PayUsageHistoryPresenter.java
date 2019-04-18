package com.dutch.hdh.dutchpayapp.ui.wallet.payhistory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.SendPoint;
import com.dutch.hdh.dutchpayapp.databinding.FragmentSendReceiveBinding;
import com.dutch.hdh.dutchpayapp.ui.mygroup.directinput.MyGroup_DirectInputFragment;
import com.dutch.hdh.dutchpayapp.ui.mygroup.main.MyGroup_MainFragment;
import com.dutch.hdh.dutchpayapp.ui.mygroup.telephonedirectory.MyGroup_TelephoneDirectoryFragment;
import com.dutch.hdh.dutchpayapp.ui.wallet.sendandreceive.SendReceiveContract;
import com.dutch.hdh.dutchpayapp.util.Trace;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.zxing.common.BitMatrix;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayUsageHistoryPresenter implements PayUsageHistoryContract.Presenter {


    private PayUsageHistoryContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private FragmentSendReceiveBinding mFragmentSendReceiveBinding;


    public PayUsageHistoryPresenter(PayUsageHistoryContract.View view, Context context, FragmentManager fragmentManager) {
        this.mView = view;
        this.mContext = context;
        this.mFragmentManager = fragmentManager;
    }

    @Override
    public void checkDateSortButton(LinearLayout linearLayout, boolean isCheck) {
        if (isCheck) {
            linearLayout.setBackgroundResource(R.color.PayUsageTabSelectColor);
        } else {
            linearLayout.setBackgroundResource(android.R.color.white);
        }
    }

    @Override
    public void clickPayAllStatus() {

    }

    @Override
    public void clickPayDutchPayStatus() {

    }

    @Override
    public void clickPaySoloPayStatus() {

    }

    @Override
    public void clickPaySendReceiveStatus() {

    }

    @Override
    public void clickOneWeekPayList() {

    }

    @Override
    public void clickOneMonthPayList() {

    }

    @Override
    public void clickThreeMonthPayList() {

    }

    @Override
    public void clickAllPayList() {

    }
}
