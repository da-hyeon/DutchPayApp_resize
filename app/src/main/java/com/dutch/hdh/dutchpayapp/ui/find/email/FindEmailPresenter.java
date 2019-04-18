package com.dutch.hdh.dutchpayapp.ui.find.email;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.EditText;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.data.db.SearchEmail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindEmailPresenter implements FindEmailContract.Presenter {

    private FindEmailContract.View mView;
    private Context mContext;
    private MyApplication mMyApplication;

    public FindEmailPresenter(FindEmailContract.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
        mMyApplication = MyApplication.getInstance();
    }

    /**
     * 취소 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickCancel() {
        mView.showWarningDialog("경고", "현재 입력된 정보는 저장되지 않습니다. \n 정말 나가시겠습니까?");
        //mView.finishActivity();
    }

    /**
     * 확인 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickOKButton(EditText[] mEditTextArray) {
        if (!isEmpty(mEditTextArray)) {
            mView.hideKeyboard();

            Call<SearchEmail> findEmail = MyApplication
                    .getRestAdapter()
                    .findEmail(mEditTextArray[0].getText().toString(), mEditTextArray[1].getText().toString());

            findEmail.enqueue(new Callback<SearchEmail>() {
                @Override
                public void onResponse(@NonNull Call<SearchEmail> call, @NonNull Response<SearchEmail> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            SearchEmail email = response.body();
                            if (email != null) {
                                mView.showEmailLayout();
                                mView.changeEmailText(email.getEmail());
                            } else {
                                mView.showFailDialog("실패", "이메일을 찾을 수 없습니다.");
                                mView.hideEmailLayout();
                            }
                        } else {
                            mView.showFailDialog("실패", "이메일을 찾을 수 없습니다.");
                        }
                    }
                }


                @Override
                public void onFailure(@NonNull Call<SearchEmail> call, @NonNull Throwable t) {
                    mView.showFailDialog("실패", t.getMessage());
                }
            });
        } else {
            mView.showFailDialog("실패", "빈칸을 채워주세요.");
        }
    }

    /**
     * Edittext의 빈칸이 있는지 확인
     */
    private boolean isEmpty(EditText[] mEditTextArray) {
        for (EditText editTexts : mEditTextArray) {
            if (editTexts.getText().toString().equals(""))
                return true;
        }
        return false;
    }
}
