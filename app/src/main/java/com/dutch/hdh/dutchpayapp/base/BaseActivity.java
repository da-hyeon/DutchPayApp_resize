package com.dutch.hdh.dutchpayapp.base;



import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.dutch.hdh.dutchpayapp.R;


public class BaseActivity extends AppCompatActivity {
    private Dialog dialog;
    private ImageView imageView;

    private View mContainer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        dialog = new Dialog(BaseActivity.this, android.R.style.Theme_Dialog);
//        dialog.setCancelable(false);
//        mContainer = View.inflate(this, R.layout.dialog_loding, null);
//        mAvLoadingIndicatorView = mContainer.findViewById(R.id.progress);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(mContainer);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }



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

    public void showDialog() {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();

        }
    }

    public void hideDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }


}
