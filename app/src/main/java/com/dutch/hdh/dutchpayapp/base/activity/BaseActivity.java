package com.dutch.hdh.dutchpayapp.base.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.databinding.DialogCommonBinding;
import com.dutch.hdh.dutchpayapp.ui.view.CommonDialogView;

public class BaseActivity extends AppCompatActivity implements BaseActivityContract.View {

    private BaseActivityContract.Presenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mPresenter = new BaseActivityPresenter(this, this, getSupportFragmentManager());
    }


    @Override
    public void showCommonDialog(String title, String content, boolean isBack) {
        Dialog build = new Dialog(this);
        build.requestWindowFeature(Window.FEATURE_NO_TITLE);
        build.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        build.setContentView(new CommonDialogView(this, title, content, true, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.ivConfirm) {
                    build.dismiss();
                    if (isBack) {
                        onBackPressed();
                    }
                }
            }
        }));
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(build.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.TOP;
        build.show();
        Window window = build.getWindow();
        window.setAttributes(lp);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }

    /**
     * 임시로 공용 다이얼로
     */



    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(0, R.anim.slide_down_out);
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
