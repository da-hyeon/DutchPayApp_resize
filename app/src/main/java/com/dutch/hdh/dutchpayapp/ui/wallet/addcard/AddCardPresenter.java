package com.dutch.hdh.dutchpayapp.ui.wallet.addcard;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.CardList;
import com.dutch.hdh.dutchpayapp.databinding.ActivityAddCardBinding;
import com.dutch.hdh.dutchpayapp.ui.view.CardSelectView;

import java.util.ArrayList;


public class AddCardPresenter implements AddCardContract.Presenter {


    private Context mContext;
    private AddCardContract.View mView;
    private ActivityAddCardBinding mBinding;

    public AddCardPresenter(Context context, AddCardContract.View view, ActivityAddCardBinding activityAddCardBinding) {
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
     * 카드 선택화면 보여주기
     */
    @Override
    public void cardSelect() {
        Dialog build = new Dialog(mContext);
        build.requestWindowFeature(Window.FEATURE_NO_TITLE);
        build.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        build.setContentView(new CardSelectView(mContext, getCardDummyData(), new CardSelectView.OnReceiveMessageListener() {
            @Override
            public void onReceive(String carName, int cardCode) {
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
     * @param cardCompany 카드회사 선택
     * @param cardNumber  카드번호
     * @param cardYear    카드년도
     * @param cardMonth   카드월
     * @param cardCVC     카드 cvc 번호
     */
    @Override
    public void cardAddConfirm(String cardCompany, String cardNumber, String cardYear, String cardMonth, String cardCVC) {
        //예외처리 통과후 서버 통신
        if (cardValidate()) {

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
    public ArrayList<CardList.CardListResult> getCardDummyData() {

        ArrayList<CardList.CardListResult> mCardListResults = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            mCardListResults.add(new CardList.CardListResult("하나카드", i));
        }
        return mCardListResults;
    }
}
