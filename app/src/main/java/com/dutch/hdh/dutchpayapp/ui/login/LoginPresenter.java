package com.dutch.hdh.dutchpayapp.ui.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.UserInfo;
import com.dutch.hdh.dutchpayapp.ui.find.email.FindEmailActivity;
import com.dutch.hdh.dutchpayapp.ui.find.password.FindPasswordActivity;
import com.dutch.hdh.dutchpayapp.ui.mypage.change_email.MyPage_ChangeEmailActivity;
import com.dutch.hdh.dutchpayapp.ui.register.term.Register_TermsConditionsAgreementFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;

    private Context mContext;
    private FragmentManager mFragmentManager;
    private Activity mActivity;

    private MyApplication mMyApplication;

    /**
     * 생성자
     */
    LoginPresenter(LoginContract.View mView, Context mContext, FragmentManager mFragmentManager, Activity mActivity) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        this.mActivity = mActivity;

        mMyApplication = MyApplication.getInstance();
    }

    /**
     * 로그인 클릭 이벤트 처리
     */
    @Override
    public void clickLogin(String userID, String userPassword) {
        if (userID.equals("") || userPassword.equals("")) {
            mView.showFailDialog("빈칸을 채워주세요.");
        } else {
            Call<UserInfo> userInfo = MyApplication
                    .getRestAdapter()
                    .getFineUser(userID, userPassword);

            userInfo.enqueue(new Callback<UserInfo>() {
                @Override
                public void onResponse(@NonNull Call<UserInfo> call, @NonNull Response<UserInfo> response) {
                    if (response.body() != null) {
                        mMyApplication.setUserInfo(response.body());
                        mMyApplication.getUserInfo().setUserState(true);
                        mView.showSuccessDialog(mMyApplication.getUserInfo().getUserName()+"님 환영합니다.");
                    } else {
                        mView.showFailDialog("아이디와 비밀번호를 확인해주세요.");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<UserInfo> call, @NonNull Throwable t) {
                    mView.showFailDialog(t.getMessage());
                }
            });
        }
    }

    /**
     * 회원가입 클릭 이벤트 처리
     */
    @Override
    public void clickRegister() {
        //프래그먼트 이동
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0,0, R.anim.fade_out);

        fragmentTransaction.replace(R.id.flFragmentContainer, mMyApplication.getRegister_TermsConditionsAgreementFragment() , Register_TermsConditionsAgreementFragment.class.getName());
        fragmentTransaction.addToBackStack(Register_TermsConditionsAgreementFragment.class.getName());
        fragmentTransaction.commit();
    }

    /**
     * 아이디 찾기 클릭 이벤트 처리
     */
    @Override
    public void clickFindEmail() {
        Intent intent = new Intent(mContext, FindEmailActivity.class);
        mContext.startActivity(intent);
        mActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    /**
     * 비밀번호 찾기 클릭 이벤트 처리
     */
    @Override
    public void clickFindPassword() {
        Intent intent = new Intent(mContext, FindPasswordActivity.class);
        mContext.startActivity(intent);
        mActivity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    /**
     * 로그인성공 다이얼로그 확인버튼 클릭 이벤트 처리
     */
    @Override
    public void clickSuccessDialog() {
        //메인 프래그먼트 제외한 나머지 모두 스택에서 제거
        mView.removeAllExceptMains();
    }
}
