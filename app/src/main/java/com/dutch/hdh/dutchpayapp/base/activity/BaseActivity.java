package com.dutch.hdh.dutchpayapp.base.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.dutch.hdh.dutchpayapp.R;

public class BaseActivity extends AppCompatActivity implements BaseActivityContract.View{

    private BaseActivityContract.Presenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mPresenter = new BaseActivityPresenter(this , this , getSupportFragmentManager());
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

}
