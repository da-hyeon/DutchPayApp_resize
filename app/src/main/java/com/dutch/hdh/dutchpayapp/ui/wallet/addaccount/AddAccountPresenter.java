package com.dutch.hdh.dutchpayapp.ui.wallet.addaccount;

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

import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.data.db.AccountBankList;
import com.dutch.hdh.dutchpayapp.data.db.CardCompanyList;
import com.dutch.hdh.dutchpayapp.databinding.ActivityAccountAddBinding;
import com.dutch.hdh.dutchpayapp.databinding.ActivityCardAddBinding;
import com.dutch.hdh.dutchpayapp.ui.view.BankSelectView;
import com.dutch.hdh.dutchpayapp.ui.view.CardSelectView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddAccountPresenter implements AddAccountContract.Presenter {

    private Context mContext;
    private AddAccountContract.View mView;
    private ActivityAccountAddBinding mBinding;
    private MyApplication mMyApplication;

    public AddAccountPresenter(Context context, AddAccountContract.View view, ActivityAccountAddBinding activityAccountAddBinding) {
        this.mContext = context;
        this.mView = view;
        this.mBinding = activityAccountAddBinding;
        mMyApplication = MyApplication.getInstance();
    }

    /**
     * 취소하기
     */
    @Override
    public void cancelClick() {
        if (mContext instanceof AddAccountActivity) {
            ((AddAccountActivity) mContext).onBackPressed();
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

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e("afterTextChanged", "afterTextChanged");

                Log.e("onTextChanged", "onTextChanged");
                switch (maxCount) {
                    case 1:
                        if (s.length() == maxCount) {
                            if (editText.getId() == mBinding.etAccountPassword1.getId()) {
                                mBinding.etAccountPassword2.requestFocus();
                            } else if (editText.getId() == mBinding.etAccountPassword2.getId()) {
                                mBinding.etAccountPassword3.requestFocus();
                            } else if (editText.getId() == mBinding.etAccountPassword3.getId()) {
                                mBinding.etAccountPassword4.requestFocus();
                            }
                        }
                        break;
                }
            }
        };
        return textWatcher;
    }

    @Override
    public void accountAddConfirm(String accountTypeCode) {
        //예외처리 통과후 서버 통신
        if (accountValidate()) {
            Call<Void> accountRegister = MyApplication.getInstance()
                    .getRestAdapter()
                    .setAccountRegister(mBinding.etAccountNum.getText().toString().trim(),
                            accountTypeCode,
                            mMyApplication.getUserInfo().getUserCode()
                            );
            accountRegister.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        mView.showCommonDialog("알림", "계좌등록이 완료되었습니다.", true);
                    }
                }
                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    mView.showCommonDialog("알림", "계좌등록 처리를 하지 못했습니다.", false);
                }
            });
        }
    }

    @Override
    public boolean accountValidate() {
        if (mBinding.tvBankSelect == null || mBinding.tvBankSelect.getText().toString().trim().isEmpty()) {

            mView.showCommonDialog("알림", "계좌등록할 은행을 선택하세요.", false);

            return false;
        }

        if (mBinding.etAccountNum == null || mBinding.etAccountNum.getText().toString().trim().matches("")) {

            mView.showCommonDialog("알림", "계좌번호를 입력하세요", false);

            return false;
        }

        if (mBinding.etAccountPassword1 == null || mBinding.etAccountPassword1.getText().toString().trim().matches("") ||
                mBinding.etAccountPassword2 == null || mBinding.etAccountPassword2.getText().toString().trim().matches("") ||
                mBinding.etAccountPassword3 == null || mBinding.etAccountPassword3.getText().toString().trim().matches("") ||
                mBinding.etAccountPassword4 == null || mBinding.etAccountPassword4.getText().toString().trim().matches("")) {
            mView.showCommonDialog("알림", "비밀번호를 입력하세요.", false);

            return false;
        }

        return true;
    }

    @Override
    public void getAccountSelectList() {
        Call<AccountBankList> cardList = MyApplication.getInstance()
                .getRestAdapter()
                .getBankSelectList();

        cardList.enqueue(new Callback<AccountBankList>() {
            @Override
            public void onResponse(Call<AccountBankList> call, Response<AccountBankList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getAccounttypelist().size() != 0) {
                        Dialog build = new Dialog(mContext);
                        build.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        build.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        build.setContentView(new BankSelectView(mContext, response.body().getAccounttypelist(), new BankSelectView.OnReceiveMessageListener() {
                            @Override
                            public void onReceive(String bankName, String bankCode) {
                                mView.bankData(bankName, bankCode);
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
                } else {
                    mView.showCommonDialog("알림", "에러", false);
                }

            }

            @Override
            public void onFailure(Call<AccountBankList> call, Throwable t) {

            }
        });
    }
}
