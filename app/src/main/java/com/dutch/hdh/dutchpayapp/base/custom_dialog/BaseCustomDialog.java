package com.dutch.hdh.dutchpayapp.base.custom_dialog;

import android.app.Dialog;
import android.content.Context;

import com.kinda.alert.KAlertDialog;

public class BaseCustomDialog extends Dialog implements BaseCustomDialogContract.View{

    private BaseCustomDialogContract.Presenter mPresenter;

    public BaseCustomDialog(Context context) {
        super(context);
    }

    /**
     * 성공 다이얼로그 보이기
     */
    @Override
    public void showSuccessDialog(String title, String content) {
        new KAlertDialog(getContext(), KAlertDialog.SUCCESS_TYPE)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText("확인")
                .setConfirmClickListener(sDialog -> {
                    sDialog.dismissWithAnimation();
                    dismiss();
                })
                .show();
    }

    /**
     * 경고 다이얼로그 보이기
     */
    @Override
    public void showWarningDialog(String title, String content) {
        new KAlertDialog(getContext(), KAlertDialog.WARNING_TYPE)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText("확인")
                .setConfirmClickListener(sDialog -> {
                    sDialog.dismissWithAnimation();
                    dismiss();
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
        new KAlertDialog(getContext(), KAlertDialog.WARNING_TYPE)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText("확인")
                .setConfirmClickListener(sDialog -> {
                    sDialog.dismissWithAnimation();
                    dismiss();
                })
                .show();
    }
}
