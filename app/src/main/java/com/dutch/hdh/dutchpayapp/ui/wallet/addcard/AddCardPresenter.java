package com.dutch.hdh.dutchpayapp.ui.wallet.addcard;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.data.db.CardCompanyList;
import com.dutch.hdh.dutchpayapp.databinding.ActivityCardAddBinding;
import com.dutch.hdh.dutchpayapp.ui.view.CardSelectView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddCardPresenter implements AddCardContract.Presenter {

    private Context mContext;
    private AddCardContract.View mView;
    private ActivityCardAddBinding mBinding;

    public AddCardPresenter(Context context, AddCardContract.View view, ActivityCardAddBinding activityAddCardBinding) {
        this.mContext = context;
        this.mView = view;
        this.mBinding = activityAddCardBinding;
    }

    /**
     * 취소하기
     */
    @Override
    public void cancelClick() {
        if (mContext instanceof AddCardActivity) {
            ((AddCardActivity) mContext).onBackPressed();
        }
    }

    /**
     * 입력필드 포커싱
     */

    @Override
    public TextWatcher getTextWatcher(EditText editText, int maxCount) {

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.e("beforeTextChanged", "beforeTextChanged");

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("onTextChanged", "onTextChanged");

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e("afterTextChanged", "afterTextChanged");


                switch (maxCount) {
                    case 2:
                        if (s.length() == maxCount) {
                            if (editText.getId() == mBinding.etCardYear.getId()) {
                                mBinding.etCardMonth.requestFocus();
                            } else if (editText.getId() == mBinding.etCardMonth.getId()) {
                                mBinding.etCardCVC.requestFocus();
                            }
                        }
                        break;
                    case 4:
                        if (s.length() == maxCount) {
                            if (editText.getId() == mBinding.etCardNum1.getId()) {
                                mBinding.etCardNum2.requestFocus();
                            } else if (editText.getId() == mBinding.etCardNum2.getId()) {
                                mBinding.etCardNum3.requestFocus();
                            } else if (editText.getId() == mBinding.etCardNum3.getId()) {
                                mBinding.etCardNum4.requestFocus();
                            } else if (editText.getId() == mBinding.etCardNum4.getId()) {
                                mBinding.etCardYear.requestFocus();
                            }
                        }
                        break;
                }
            }
        };
        return textWatcher;
    }

    /**
     * 서버쪽으로 data 전달
     *
     * @param cardTypeCode 카드회사 코드
     */
    @Override
    public void cardAddConfirm(String cardTypeCode) {
        //예외처리 통과후 서버 통신
        if (cardValidate()) {
            Call<Void> cardRegister = MyApplication.getInstance()
                    .getRestAdapter()
                    .setCardRegister(mBinding.etCardNum1.getText().toString() + mBinding.etCardNum2.getText().toString() + mBinding.etCardNum3.getText().toString() + mBinding.etCardNum4.getText().toString(),
                            cardTypeCode,
                            Constants.USER_CODE);

            cardRegister.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.body() == null) {

                    } else {

                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                }
            });
        }
    }

    /**
     * 입력필드 예외처리
     */
    @Override
    public boolean cardValidate() {
        if (mBinding.tvCardSelect == null || mBinding.tvCardSelect.getText().toString().trim().isEmpty() ||
                mBinding.tvCardSelect.getText().toString().trim().isEmpty()) {
            Toast.makeText(mContext, "카드사를 선택하세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mBinding.etCardNum1 == null || mBinding.etCardNum1.getText().toString().trim().matches("") ||
                mBinding.etCardNum2 == null || mBinding.etCardNum2.getText().toString().trim().matches("") ||
                mBinding.etCardNum3 == null || mBinding.etCardNum3.getText().toString().trim().matches("") ||
                mBinding.etCardNum4 == null || mBinding.etCardNum4.getText().toString().trim().matches("")) {
            Toast.makeText(mContext, "카드번호를 입력하세요.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (mBinding.etCardYear == null || mBinding.etCardYear.getText().toString().trim().matches("")) {
            Toast.makeText(mContext, "카드년도을 입력하세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mBinding.etCardMonth == null || mBinding.etCardMonth.getText().toString().trim().matches("")) {
            Toast.makeText(mContext, "카드월을 입력하세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mBinding.etCardCVC == null || mBinding.etCardCVC.getText().toString().trim().matches("")) {
            Toast.makeText(mContext, "카드월을 입력하세요.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void getCardSelectList() {

        Call<CardCompanyList> cardList = MyApplication.getInstance()
                .getRestAdapter()
                .getCardSelectList();

        cardList.enqueue(new Callback<CardCompanyList>() {
            @Override
            public void onResponse(Call<CardCompanyList> call, Response<CardCompanyList> response) {
                if (response.body() == null) {

                } else {
                    Dialog build = new Dialog(mContext);
                    build.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    build.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    build.setContentView(new CardSelectView(mContext, response.body().getCardtypelist(), new CardSelectView.OnReceiveMessageListener() {
                        @Override
                        public void onReceive(String carName, String cardCode) {
                            mView.cardData(carName, cardCode);
                            build.dismiss();
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
                }
            }

            @Override
            public void onFailure(Call<CardCompanyList> call, Throwable t) {

            }
        });
    }
}
