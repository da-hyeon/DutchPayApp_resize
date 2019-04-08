package com.dutch.hdh.dutchpayapp.ui.dialog.payment_info;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.Window;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.databinding.DialogPaymentInfomationBinding;

public class Payment_InfomationDialog extends Dialog implements  Payment_InfomationDialogContract.View{

    private DialogPaymentInfomationBinding mBinding;
    private Payment_InfomationDialogContract.Presenter mPresenter;

    public Payment_InfomationDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_payment_infomation, null , false);
        setContentView(mBinding.getRoot());

        mPresenter = new Payment_InfomationDialogPresenter(this, getContext());

    }
    /**
     * 객체생성 및 데이터초기화
     */
    @Override
    public void initData() {

    }
}
