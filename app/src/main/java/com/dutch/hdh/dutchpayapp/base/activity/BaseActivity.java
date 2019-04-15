package com.dutch.hdh.dutchpayapp.base.activity;

import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.util.LogUtils;
import com.kinda.alert.KAlertDialog;

public class BaseActivity extends AppCompatActivity implements BaseActivityContract.View{

    private BaseActivityContract.Presenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mPresenter = new BaseActivityPresenter(this , this , getSupportFragmentManager());
    }

    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    /**
     * 성공 다이얼로그 보이기
     */
    @Override
    public void showSuccessDialog(String title, String content) {
        new KAlertDialog(this, KAlertDialog.SUCCESS_TYPE)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText("확인")
                .setConfirmClickListener(sDialog -> {
                    sDialog.dismissWithAnimation();
                    finish();
                })
                .show();
    }

    /**
     * 경고 다이얼로그 보이기
     */
    @Override
    public void showWarningDialog(String title, String content) {
        new KAlertDialog(this, KAlertDialog.WARNING_TYPE)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText("확인")
                .setConfirmClickListener(sDialog -> {
                    sDialog.dismissWithAnimation();
                    finish();
                })
                .setCancelText("취소")
                .setCancelClickListener(sDialog -> sDialog.dismissWithAnimation())
                .show();
    }

    /**
     * 실패 다이얼로그 보이기
     */
    @Override
    public void showFailDialog(String title, String content) {
        new KAlertDialog(this, KAlertDialog.WARNING_TYPE)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText("확인")
                .setConfirmClickListener(sDialog -> {
                    sDialog.dismissWithAnimation();
                })
                .show();
    }

    /**
     * 키패드 hide
     */
    @Override
    public void hideKeyboard() {
        try {
            View focusView = getCurrentFocus();

            if(focusView != null) {
                IBinder binder = focusView.getWindowToken();
                if(binder != null) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(binder, 0);
                    focusView.clearFocus();
                }
            }
        } catch (NullPointerException e) {
            LogUtils.e(e);
        } catch (IllegalStateException e) {
            LogUtils.e(e);
        }
    }
}
