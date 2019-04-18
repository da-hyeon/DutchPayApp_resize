package com.dutch.hdh.dutchpayapp.ui.intro;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.activity.BaseActivity;
import com.dutch.hdh.dutchpayapp.ui.main.activity.MainActivity;

public class IntroActivity extends BaseActivity {
    private PopupWindow popupWindow;
    private View notiPopup;

    Handler handler = new Handler();
    Runnable r = new Runnable() {
        @Override
        public void run() {
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
