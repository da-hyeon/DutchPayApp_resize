package com.dutch.hdh.dutchpayapp.base.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.data.db.UserInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class BaseActivityPresenter implements BaseActivityContract.Presenter {

    private BaseActivityContract.View mView;
    private Context mContext;
    private Activity mActivity;
    private FragmentManager mFragmentManager;
    private MyApplication mMyApplication;


    public BaseActivityPresenter(BaseActivityContract.View mView, Context mContext,  Activity mActivity ,FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mActivity = mActivity;
        this.mFragmentManager = mFragmentManager;
        mMyApplication = MyApplication.getInstance();
    }
}
