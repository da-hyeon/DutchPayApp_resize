package com.dutch.hdh.dutchpayapp.ui.main.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.EventImageSliderAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainFragmentPresenter implements MainFragmentContract.Presenter{

    private MainFragmentContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private Activity mActivity;
    private MyApplication myApplication;


    public MainFragmentPresenter(MainFragmentContract.View mView, Context mContext, FragmentManager mFragmentManager, Activity mActivity) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        this.mActivity = mActivity;

        myApplication = MyApplication.getInstance();
    }

    /**
     * 로그인상태 불러오기
     */
    @Override
    public void initLoginState() {
        mView.showUserInfo(myApplication.getUserInfo().getUserName(), myApplication.getUserInfo().getUserMoney() , myApplication.getUserInfo().isUserState());
    }

    /**
     * 이미지슬라이드 어댑터 연결
     */
    @Override
    public void setAdapter(ViewPager viewPager, TabLayout tabLayout) {
        //슬라이드 이미지 저장
        List<Drawable> imageArray = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            imageArray.add(ContextCompat.getDrawable(mContext, R.drawable.dutchpay_event));
        }
        EventImageSliderAdapter mEventImageSliderAdapter = new EventImageSliderAdapter(mContext, imageArray);
        viewPager.setAdapter(mEventImageSliderAdapter);

        tabLayout.setupWithViewPager(viewPager, true);
    }

    /**
     * 개인결제 시작하기 클릭 이벤트 처리
     */
    @Override
    public void clickSolopay() {

    }

    /**
     * 더치페이 시작하기 클릭 이벤트 처리
     */
    @Override
    public void clickDutchpay() {

    }
}
