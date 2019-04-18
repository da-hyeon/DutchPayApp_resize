package com.dutch.hdh.dutchpayapp.ui.mypage.change_password;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.EditText;

import com.dutch.hdh.dutchpayapp.MyApplication;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPage_ChangePasswordPresenter implements MyPage_ChangePasswordContract.Presenter {

    private MyPage_ChangePasswordContract.View mView;
    private Context mContext;
    private MyApplication mMyApplication;

    /**
     * 생성자
     */
    MyPage_ChangePasswordPresenter(MyPage_ChangePasswordContract.View mView, Context mContext) {
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
    }

    /**
     * 변경하기 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickChange(EditText[] mEditTextArray) {
        //빈칸이 존재하는지 확인
        if(!isEmpty(mEditTextArray)) {
            //비밀번호가 맞는지 비교
            if (isSameMyPassword(mEditTextArray[0].getText().toString())) {
                //신규 비밀번호가 서로 같은지 비교
                if (isSamePassword(mEditTextArray[1].getText().toString(), mEditTextArray[2].getText().toString())) {
                    //변경하기
                    Call<Void> changePassword = MyApplication
                            .getRestAdapter()
                            .changePassword(mEditTextArray[1].getText().toString(), mMyApplication.getUserInfo().getUserCode());

                    changePassword.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                            if (response.isSuccessful()) {
                                mMyApplication.getUserInfo().setUserPassword(mEditTextArray[1].getText().toString());
                                mView.showSuccessDialog("비밀번호 변경 성공", "");
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
                    mView.showFailDialog("실패", "신규 비밀번호가 서로 같지 않습니다.");
                }
            } else {
                mView.showFailDialog("실패", "비밀번호가 틀립니다.");
            }
        } else {
            mView.showFailDialog("실패", "빈칸을 채워주세요.");
        }
    }

    /**
     * 신규 비밀번호 , 신규 비밀번호 확인 필드가 서로 같은지 확인.
     * true = 같음 , false = 틀림
     */
    private boolean isSamePassword(String password, String passwordCheck) {
        return password.equals(passwordCheck);
    }

    /**
     * 입력한 비밀번호가 맞는지 확인.
     * true = 맞음 , false = 틀림
     */
    private boolean isSameMyPassword(String password) {
        return mMyApplication.getUserInfo().getUserPassword().equals(password);
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
