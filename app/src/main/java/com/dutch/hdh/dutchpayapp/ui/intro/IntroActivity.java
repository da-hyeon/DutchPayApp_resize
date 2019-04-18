package com.dutch.hdh.dutchpayapp.ui.intro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.activity.BaseActivity;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragmentContract;
import com.dutch.hdh.dutchpayapp.data.db.UserInfo;
import com.dutch.hdh.dutchpayapp.ui.main.activity.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IntroActivity extends BaseActivity {
    private PopupWindow popupWindow;
    private View notiPopup;
    Handler handler = new Handler();
    Runnable r = new Runnable() {
        @Override
        public void run() {
            isPermissionCheck();

            //팝업창 뷰 생성
            notiPopup = getLayoutInflater().inflate(R.layout.dialog_notice, null);

            //팝업 윈도우 생성
            popupWindow = new PopupWindow(notiPopup, ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT);
            //팝업 윈도우 셋팅
            popupWindow.setFocusable(false);
            popupWindow.showAtLocation(notiPopup, Gravity.CENTER, 0, 0);

            Button cancel = notiPopup.findViewById(R.id.btn_noti_cancel);
            cancel.setOnClickListener(v ->
                    //종료버튼
                    popupWindow.dismiss());


            Button ok = notiPopup.findViewById(R.id.btn_noti_next);
            ok.setOnClickListener(v ->{
                    //확인버튼
                    startActivity(new Intent(IntroActivity.this, MainActivity.class));
                    finish();
            });
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        MyApplication mMyApplication = MyApplication.getInstance();

        //저장된 아이디 ,비밀번호 불러오기
        SharedPreferences sharedPreferences = getSharedPreferences(Constants.USER_INFOMATION, MODE_PRIVATE);

        String userID = sharedPreferences.getString(Constants.USER_ID, "");
        String userPassword = sharedPreferences.getString(Constants.USER_PASSWORD, "");

        //아이디와 비밀번호가 저장되어 있지 않다면.
        if (!userID.equals("") && !userPassword.equals("")) {
            Call<UserInfo> userInfo = MyApplication
                    .getRestAdapter()
                    .getFineUser(userID, userPassword);

            userInfo.enqueue(new Callback<UserInfo>() {
                @Override
                public void onResponse(@NonNull Call<UserInfo> call, @NonNull Response<UserInfo> response) {
                    if (response.body() != null) {
                        mMyApplication.setUserInfo(response.body());
                        if (mMyApplication.getUserInfo().getMessage() == null) {
                            mMyApplication.getUserInfo().setUserState(true);
                            //initMainFragment();
                            // initLoginState();
                        } else {
                            mMyApplication.getUserInfo().setUserState(false);
                        }
                    } else {
                        mMyApplication.getUserInfo().setUserState(false);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<UserInfo> call, @NonNull Throwable t) {
                    mMyApplication.getUserInfo().setUserState(false);
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        handler.postDelayed(r,3000);
    }

    @Override
    protected void onPause() {
        super.onPause();

        handler.removeCallbacks(r);
    }

}
