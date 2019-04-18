package com.dutch.hdh.dutchpayapp.base.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.util.LogUtils;
import com.kinda.alert.KAlertDialog;

public class BaseActivity extends AppCompatActivity implements BaseActivityContract.View {

    private BaseActivityContract.Presenter mPresenter;

    // Permission
    public static final int PERMISSION = 0x00;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mPresenter = new BaseActivityPresenter(this, this, this, getSupportFragmentManager());


    }

    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    /**
     * 권한 획득 결과
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSION:
                // 권한은 입력받은 순서대로 돌아옴
                // 0 : WRITE_EXTERNAL_STORAGE
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        // 권한 허가
                    } else {
                        // 권한 거부
                        finish();
                    }
                }
        }
    }

    /**
     * 권한 획득
     */
    @Override
    public void isPermissionCheck() {
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            int rContactPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
            int cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
            int sSmsPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
            int wExtPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int rExtPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            if (rContactPermission != PackageManager.PERMISSION_GRANTED ||
                    cameraPermission != PackageManager.PERMISSION_GRANTED ||
                    sSmsPermission != PackageManager.PERMISSION_GRANTED ||
                    wExtPermission != PackageManager.PERMISSION_GRANTED ||
                    rExtPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS,
                                Manifest.permission.CAMERA,
                                Manifest.permission.SEND_SMS,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE},
                        PERMISSION);
            }
        }
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

            if (focusView != null) {
                IBinder binder = focusView.getWindowToken();
                if (binder != null) {
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
