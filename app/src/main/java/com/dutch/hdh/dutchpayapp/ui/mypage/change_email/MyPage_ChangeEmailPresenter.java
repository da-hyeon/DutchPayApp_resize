package com.dutch.hdh.dutchpayapp.ui.mypage.change_email;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.EditText;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.data.db.UserInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPage_ChangeEmailPresenter implements MyPage_ChangeEmailContract.Presenter{
    private MyPage_ChangeEmailContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private MyApplication mMyApplication;

    public MyPage_ChangeEmailPresenter(MyPage_ChangeEmailContract.View mView, Context mContext, FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        mMyApplication = MyApplication.getInstance();
    }

    @Override
    public void setVIew() {
        mView.changeEmailText(mMyApplication.getUserInfo().getUserEmail());
    }

    @Override
    public void clickCancel() {
        mView.showWarningDialog("경고" , "현재 입력된 정보는 저장되지 않습니다. \n 정말 나가시겠습니까?");
        //mView.finishActivity();
    }

    @Override
    public void clickChange(EditText[] mEditTextArray) {
        if(isEmailSame(mEditTextArray[0].getText().toString() , mEditTextArray[1].getText().toString())){
            if(isSameMyPassword(mEditTextArray[2].getText().toString())){
                //변경하기
                Call<Void> userInfo = MyApplication
                        .getRestAdapter()
                        .changeEmail(mEditTextArray[0].getText().toString(), mMyApplication.getUserInfo().getUserCode());

                userInfo.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            mMyApplication.getUserInfo().setUserEmail(mEditTextArray[0].getText().toString());
                            mView.showSuccessDialog("이메일 변경 성공" , mEditTextArray[0].getText().toString() + "\n 위의 이메일로 변경 완료.");
                        } else {
                            mView.showFailDialog("실패" , "데이터 전송에 실패했습니다.");
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        mView.showFailDialog("실패" , t.getMessage());
                    }
                });
            } else{
                mView.showFailDialog("실패" , "비밀번호가 틀립니다.");
            }
        } else {
            mView.showFailDialog("실패" , "신규 이메일이 서로 같지 않습니다.");
        }
    }

    private boolean isEmailSame(String email , String emailCheck){
        if(email.equals(emailCheck))
            return true;

        return false;
    }

    private boolean isSameMyPassword(String password){
        if(mMyApplication.getUserInfo().getUserPassword().equals(password))
            return true;

        return false;
    }
}
