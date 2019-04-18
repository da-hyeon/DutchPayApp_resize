package com.dutch.hdh.dutchpayapp.ui.mypage.change_phone;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.EditText;

import com.dutch.hdh.dutchpayapp.MyApplication;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPage_ChangePhonePresenter implements MyPage_ChangePhoneContract.Presenter{

    private MyPage_ChangePhoneContract.View mView;
    private Context mContext;
    private MyApplication mMyApplication;

    MyPage_ChangePhonePresenter(MyPage_ChangePhoneContract.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
        mMyApplication = MyApplication.getInstance();
    }

    @Override
    public void clickCancel() {
        mView.showWarningDialog("경고", "현재 입력된 정보는 저장되지 않습니다. \n 정말 나가시겠습니까?");
    }

    @Override
    public void clickChange(EditText[] mEditTextArray) {
        if( !isEmpty(mEditTextArray) ) {
            //변경하기
            Call<Void> changePhoneNumber = MyApplication
                    .getRestAdapter()
                    .changePhoneNumber(mEditTextArray[0].getText().toString(), mMyApplication.getUserInfo().getUserCode());

            changePhoneNumber.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                    if (response.isSuccessful()) {
                        mMyApplication.getUserInfo().setUserEmail(mEditTextArray[0].getText().toString());
                        mView.showSuccessDialog("핸드폰 번호 변경 성공", mEditTextArray[0].getText().toString() + "\n 위의 핸드폰 번호로 변경 완료.");
                        mMyApplication.getUserInfo().setUserPhone(mEditTextArray[0].getText().toString());
                    } else {
                        mView.showFailDialog("실패", "데이터 전송에 실패했습니다.");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                    mView.showFailDialog("실패", t.getMessage());
                }
            });
        } else {
            mView.showFailDialog("실패" , "빈칸이 존재합니다.");
        }
    }

    /**
     * 빈칸이 존재하는지 확인
     * true = 빈칸존재 , false = 빈칸없음
     */
    private boolean isEmpty(EditText[] mEditTextArray) {
        for (EditText aMEditTextArray : mEditTextArray) {
            if (aMEditTextArray.getText().toString().equals("")) {
                return true;
            }
        }
        return false;
    }
}
