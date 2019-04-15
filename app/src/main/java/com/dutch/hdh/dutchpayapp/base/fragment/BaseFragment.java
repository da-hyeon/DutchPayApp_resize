package com.dutch.hdh.dutchpayapp.base.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.dutch.hdh.dutchpayapp.data.util.LogUtils;
import com.dutch.hdh.dutchpayapp.ui.main.activity.MainActivity;
import com.kinda.alert.KAlertDialog;

import java.util.Objects;

import static android.content.Context.INPUT_METHOD_SERVICE;


public class BaseFragment extends Fragment implements BaseFragmentContract.View {

    private MainActivity mMainActivity;
    private BaseFragmentContract.Presenter mPresenter;
    private KAlertDialog dialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mMainActivity = (MainActivity) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new BaseFragmentPresenter(this, getContext(), getFragmentManager());
    }

    @Override
    public void onResume() {
        super.onResume();
        mMainActivity.checkUiAndTitle();
    }

    /**
     * 메인 프래그먼트 빼고 모두 스택에서 제거
     */
    public void setDefaultMainStack() {
        FragmentManager fragmentManager = getFragmentManager();
        int count = 0;
        if (fragmentManager != null) {
            count = fragmentManager.getBackStackEntryCount() - 1;
        }
        for (int i = 0; i < count; ++i) {
            fragmentManager.popBackStack();
        }
    }

    /**
     * 성공 다이얼로그 보이기
     * OK = 되돌아가기
     */
    @Override
    public void showSuccessDialog(String title, String content) {

        dialog = new KAlertDialog(Objects.requireNonNull(getContext()), KAlertDialog.SUCCESS_TYPE);

        dialog.setTitleText(title);
        dialog.setContentText(content);
        dialog.setConfirmText("확인");
        dialog.setConfirmClickListener(sDialog -> {
            sDialog.dismissWithAnimation();
            if (getFragmentManager() != null) {
                getFragmentManager().popBackStack();
            }
        });
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    /**
     * 경고 다이얼로그 보이기
     * OK = 되돌아가기
     * Cancel = 다이얼로그 감추기
     */
    @Override
    public void showWarningDialog(String title, String content) {

        dialog = new KAlertDialog(Objects.requireNonNull(getContext()), KAlertDialog.WARNING_TYPE);
        dialog.setTitleText(title);
        dialog.setContentText(content);
        dialog.setConfirmText("확인");
        dialog.setConfirmClickListener(sDialog -> {
            sDialog.dismissWithAnimation();
            if (getFragmentManager() != null) {
                getFragmentManager().popBackStack();
            }
        });
        dialog.setCancelText("취소");
        dialog.setCancelClickListener(KAlertDialog::dismissWithAnimation);
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    /**
     * 실패 다이얼로그 보이기
     * OK = 다이얼로그 감추기
     */
    @Override
    public void showFailDialog(String title, String content) {

        dialog = new KAlertDialog(Objects.requireNonNull(getContext()), KAlertDialog.WARNING_TYPE);
        dialog.setTitleText(title);
        dialog.setContentText(content);
        dialog.setConfirmText("확인");
        dialog.setConfirmClickListener(KAlertDialog::dismissWithAnimation);
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    /**
     * 키패드 hide
     */
    @Override
    public void hideKeyboard() {
        try {
            View focusView = Objects.requireNonNull(getActivity()).getCurrentFocus();

            if (focusView != null) {
                IBinder binder = focusView.getWindowToken();
                if (binder != null) {
                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
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
