package com.dutch.hdh.dutchpayapp.ui.personal_payment.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentPersonalPaymentMainBinding;

public class PersonalPayment_MainFragment extends BaseFragment implements PersonalPayment_MainContract.View {

    private FragmentPersonalPaymentMainBinding mBinding;
    private PersonalPayment_MainContract.Presenter mPresenter;
    private boolean mQRCodeIsVisiable;
    private boolean mBarCodeIsVisiable;
    private boolean mIsRunning;

    private EditText mEditText[];

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_personal_payment_main, container, false);
        mPresenter = new PersonalPayment_MainPresenter(this, getContext(), getFragmentManager(), getActivity());
        initData();


        //결제코드 레이아웃 클릭
        mBinding.llPaymentCode.setOnClickListener(v ->
                mPresenter.clickPaymentCode()
        );

        //결제번호 레이아웃 클릭
        mBinding.llPaymentNumber.setOnClickListener(v ->
                mPresenter.clickPaymentNumber()
        );

        //결제정보 확인하기 클릭
        mBinding.btPaymentInfoCheck.setOnClickListener(v ->
                mPresenter.clickPaymentInfo()
        );

        //스캔하기 버튼 클릭
        mBinding.btScan.setOnClickListener(v ->
                mPresenter.clickScan()
        );

        //QR 코드 클릭
        mBinding.ivQRCode.setOnClickListener(v -> {
            if (mQRCodeIsVisiable) {
                if (!mIsRunning) {
                    mPresenter.clickQRCode();
                }
            }
        });


        //Barcode 클릭
        mBinding.ivBarcode.setOnClickListener(v -> {
            if (mBarCodeIsVisiable) {
                if (!mIsRunning) {
                    mPresenter.clickBarCode();
                }
            }
        });

        //EditText 입력
        for (int i = 0; i < mEditText.length; i++) {
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
                    mPresenter.textChangeNotification(index, mEditText[index].getText().toString());
                }
            });
        }

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

        mQRCodeIsVisiable = true;
        mBarCodeIsVisiable = true;
        mPresenter.initView(mBinding.ivQRCode, mBinding.ivBarcode);
    }

    /**
     * 결제코드 View 보이기
     */
    @Override
    public void showPaymentCodeView() {
        mBinding.clPaymentCode.setVisibility(View.VISIBLE);
    }

    /**
     * PaymentNumberView 보이기
     */
    @Override
    public void showPaymentNumberView() {
        mBinding.clPaymentNumberView.setVisibility(View.VISIBLE);
    }

    /**
     * QRCode 보이기
     */
    @Override
    public void showQRCodeView() {
        mIsRunning = true;

        // mBinding.ivQRCode.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in_code);
        mBinding.ivQRCode.startAnimation(animation);
        mQRCodeIsVisiable = true;

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mIsRunning = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * BarCode 보이기
     */
    @Override
    public void showBarCodeView() {
        mIsRunning = true;

        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in_code);
        mBinding.ivBarcode.startAnimation(animation);
        mBinding.tvBarCodeNumber.startAnimation(animation);
        mBarCodeIsVisiable = true;

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
               mIsRunning = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * QRcodeView 사이즈 업 + 아래로 이동
     */
    @Override
    public void showQRCodeViewScaleUp() {
        mIsRunning = true;

        Animation scaleUP = AnimationUtils.loadAnimation(getContext(), R.anim.scale_up);
        Animation moveDown = AnimationUtils.loadAnimation(getContext(), R.anim.translater_qrcode_down);
        AnimationSet animation = new AnimationSet(false);
        animation.setFillAfter(true);
        animation.addAnimation(scaleUP);
        animation.addAnimation(moveDown);
        mBinding.ivQRCode.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mIsRunning = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * QRCodeView 사이즈 다운 + 위로 이동
     */
    @Override
    public void showQRCodeViewScaleDown() {
        mIsRunning = true;

        Animation scaleDown = AnimationUtils.loadAnimation(getContext(), R.anim.scale_down);
        Animation moveUp = AnimationUtils.loadAnimation(getContext(), R.anim.translate_qrcode_up);

        AnimationSet animation = new AnimationSet(false);

        animation.setFillAfter(true);
        animation.addAnimation(scaleDown);
        animation.addAnimation(moveUp);

        mBinding.ivQRCode.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mIsRunning = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * BarCodeView 사이즈 업 + 위로 이동
     */
    @Override
    public void showBarCodeViewScaleUp() {
        mIsRunning = true;

        Animation scaleUP = AnimationUtils.loadAnimation(getContext(), R.anim.scale_up);
        Animation moveUp = AnimationUtils.loadAnimation(getContext(), R.anim.translate_barcode_up);

        AnimationSet animation = new AnimationSet(false);
        animation.setFillAfter(true);
        animation.addAnimation(scaleUP);
        animation.addAnimation(moveUp);

        mBinding.ivBarcode.startAnimation(animation);
        mBinding.tvBarCodeNumber.startAnimation(moveUp);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mIsRunning = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    /**
     * BarCodeView 사이즈 다운 + 아래로 이동
     */
    @Override
    public void showBarCodeViewScaleDown() {
        mIsRunning = true;

        Animation scaleDown = AnimationUtils.loadAnimation(getContext(), R.anim.scale_down);
        Animation moveDown = AnimationUtils.loadAnimation(getContext(), R.anim.translater_barcode_down);

        AnimationSet animation = new AnimationSet(false);
        animation.setFillAfter(true);
        animation.addAnimation(scaleDown);
        animation.addAnimation(moveDown);

        mBinding.ivBarcode.startAnimation(animation);
        mBinding.tvBarCodeNumber.startAnimation(moveDown);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mIsRunning = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 결제코드 View 숨기기
     */
    @Override
    public void hideScanView() {
        mBinding.clPaymentCode.setVisibility(View.GONE);
    }

    /**
     * PaymentNumberView 숨기기
     */
    @Override
    public void hidePaymentNumberView() {
        mBinding.clPaymentNumberView.setVisibility(View.GONE);
    }

    /**
     * QRCode 숨기기
     */
    @Override
    public void hideQRCodeView() {
        mIsRunning = true;
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out_code);
        mBinding.ivQRCode.startAnimation(animation);
        mQRCodeIsVisiable = false;

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mIsRunning = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * BarCode 숨기기
     */
    @Override
    public void hideBarCodeView() {
        mIsRunning = true;
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out_code);
        mBinding.ivBarcode.startAnimation(animation);
        mBinding.tvBarCodeNumber.startAnimation(animation);
        mBarCodeIsVisiable = false;

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mIsRunning = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    /**
     * ScanButtonBackgroundAndTextColor 변경하기
     */
    @Override
    public void changeScanButtonBackgroundAndTextColor(boolean state) {
        if (state) {
            mBinding.llPaymentCode.setBackgroundResource(R.color.solopay_buttonSelect);
            mBinding.tvPaymentCode.setTextColor(getResources().getColor(R.color.solopay_textSelect));

        } else {
            mBinding.llPaymentCode.setBackgroundResource(R.color.buttonDefault);
            mBinding.tvPaymentCode.setTextColor(getResources().getColor(R.color.textDefault));
        }
    }

    /**
     * PaymentNumberButtonBackgroundAndTextColor 변경하기
     */
    @Override
    public void changePaymentNumberButtonBackgroundAndTextColor(boolean state) {
        if (state) {
            mBinding.llPaymentNumber.setBackgroundResource(R.color.solopay_buttonSelect);
            mBinding.tvPaymentNumber.setTextColor(getResources().getColor(R.color.solopay_textSelect));

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
        if (state) {
            mEditText[index].setBackgroundResource(R.drawable.outline_round_in);
            if (index < mEditText.length - 1) {
                mEditText[index + 1].requestFocus();
            }
        } else {
            mEditText[index].setBackgroundResource(R.drawable.outline_round_out);
            if (index > 0) {
                mEditText[index - 1].requestFocus();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mQRCodeIsVisiable = true;
        mBarCodeIsVisiable = true;
        mIsRunning = false;
    }
}
