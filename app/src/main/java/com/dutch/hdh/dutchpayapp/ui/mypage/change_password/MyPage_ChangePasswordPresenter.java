package com.dutch.hdh.dutchpayapp.ui.mypage.change_password;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.widget.EditText;

import com.dutch.hdh.dutchpayapp.MyApplication;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPage_ChangePasswordPresenter implements MyPage_ChangePasswordContract.Presenter {

    private MyPage_ChangePasswordContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private MyApplication mMyApplication;

    public MyPage_ChangePasswordPresenter(MyPage_ChangePasswordContract.View mView, Context mContext, FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        mMyApplication = MyApplication.getInstance();
    }

    @Override
    public void clickCancel() {

    }

    @Override
    public void clickChange(EditText[] mEditTextArray) {
        if(isSameMyPassword(mEditTextArray[0].getText().toString())){
            if(isSamePassword(mEditTextArray[1].getText().toString() , mEditTextArray[2].getText().toString())){
                //변경하기
                Call<Void> userInfo = MyApplication
                        .getRestAdapter()
                        .changePassword(mEditTextArray[1].getText().toString(), mMyApplication.getUserInfo().getUserCode());

                userInfo.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            mMyApplication.getUserInfo().setUserPassword(mEditTextArray[1].getText().toString());
                            mView.showSuccessDialog("비밀번호 변경 성공" , "");
                        } else {
                            mView.showFailDialog("실패" , "데이터 전송에 실패했습니다.");
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        mView.showFailDialog("실패" , t.getMessage());
                    }
                });
            } else {
                mView.showFailDialog("실패","신규 비밀번호가 서로 같지 않습니다.");
            }
        } else {
            mView.showFailDialog("실패","비밀번호가 틀립니다.");
        }
    }
    private boolean isSamePassword(String password , String passwordCheck){
        if(password.equals(passwordCheck))
            return true;

        return false;
    }

    private boolean isSameMyPassword(String password){
        if(mMyApplication.getUserInfo().getUserPassword().equals(password))
            return true;

        return false;
    }
}
