package com.dutch.hdh.dutchpayapp.ui.wallet.addcard;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.BaseActivity;
import com.dutch.hdh.dutchpayapp.databinding.ActivityCardAddBinding;

public class AddCardActivity extends BaseActivity implements AddCardContract.View {

    private ActivityCardAddBinding mBinding;
    private AddCardContract.Presenter mPresenter;
    private String mCardCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_card_add);
        mPresenter = new AddCardPresenter(this, this, mBinding);
        initData();
    }


    @Override
    public void initData() {


        mBinding.tvCancel.setOnClickListener(v ->
                mPresenter.cancelClick()
        );
        mBinding.tvCardSelect.setOnClickListener(v ->
                mPresenter.getCardSelectList()
        );

        mBinding.ivCardAdd.setOnClickListener(v ->
                mPresenter.cardAddConfirm(mCardCode)
        );

        mBinding.etCardNum1.addTextChangedListener(mPresenter.getTextWatcher(mBinding.etCardNum1, 4));
        mBinding.etCardNum2.addTextChangedListener(mPresenter.getTextWatcher(mBinding.etCardNum2, 4));
        mBinding.etCardNum3.addTextChangedListener(mPresenter.getTextWatcher(mBinding.etCardNum3, 4));
        mBinding.etCardNum4.addTextChangedListener(mPresenter.getTextWatcher(mBinding.etCardNum4, 4));
        mBinding.etCardYear.addTextChangedListener(mPresenter.getTextWatcher(mBinding.etCardYear, 2));
        mBinding.etCardMonth.addTextChangedListener(mPresenter.getTextWatcher(mBinding.etCardMonth, 2));
    }

    @Override
    public void cardData(String cardName, String cardCode) {
        mBinding.tvCardSelect.setText(cardName);
        mCardCode = cardCode;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
