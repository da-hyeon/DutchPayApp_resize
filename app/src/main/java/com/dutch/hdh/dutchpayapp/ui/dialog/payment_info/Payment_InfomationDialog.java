package com.dutch.hdh.dutchpayapp.ui.dialog.payment_info;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.databinding.CustomDialogBinding;

public class Payment_InfomationDialog extends Activity implements  Payment_InfomationDialogContract.View{

    private CustomDialogBinding mBinding;
    private Payment_InfomationDialogContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mBinding = DataBindingUtil.setContentView(this , R.layout.custom_dialog);
        mBinding.setDialog(this);
        mPresenter = new Payment_InfomationDialogPresenter(this, this , this);

        mPresenter.getIntent(getIntent());
    }
    /**
     * 객체생성 및 데이터초기화
     */
    @Override
    public void initData() {

    }
}
