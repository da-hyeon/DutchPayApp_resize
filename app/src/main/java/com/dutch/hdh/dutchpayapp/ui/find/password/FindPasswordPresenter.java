package com.dutch.hdh.dutchpayapp.ui.find.password;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.EditText;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.data.db.SearchPassword;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindPasswordPresenter implements FindPasswordContract.Presenter{

    private FindPasswordContract.View mView;
    private Context mContext;

    public FindPasswordPresenter(FindPasswordContract.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
    }

    /**
     * 취소 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickCancel() {
        mView.showWarningDialog("경고", "현재 입력된 정보는 저장되지 않습니다. \n 정말 나가시겠습니까?");
        //mView.finishActivity();
    }

    @Override
    public void clickOKButton(EditText[] mEditTextArray) {
        if(!isEmpty(mEditTextArray)){
            mView.hideKeyboard();

            Call<SearchPassword> findPhoneNumber = MyApplication
                    .getRestAdapter()
                    .findPhoneNumber(mEditTextArray[0].getText().toString(), mEditTextArray[1].getText().toString() , mEditTextArray[2].getText().toString());

            findPhoneNumber.enqueue(new Callback<SearchPassword>() {
                @Override
                public void onResponse(@NonNull Call<SearchPassword> call, @NonNull Response<SearchPassword> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            SearchPassword password = response.body();
                            if (password != null) {
                                mView.showPasswordLayout();
                            } else {
                                mView.showFailDialog("실패", "비밀번호를 찾을 수 없습니다.");
                                mView.hidePasswordLayout();
                            }
                        } else {
                            mView.showFailDialog("실패", "비밀번호를 찾을 수 없습니다.");
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<SearchPassword> call, @NonNull Throwable t) {
                    mView.showFailDialog("실패", t.getMessage());
                }
            });
        } else {
            mView.showFailDialog("실패" , "빈칸을 채워주세요.");
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
