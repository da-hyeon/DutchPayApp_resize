package com.dutch.hdh.dutchpayapp.ui.main.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.EventImageSliderAdapter;
import com.dutch.hdh.dutchpayapp.data.db.EventList;
import com.dutch.hdh.dutchpayapp.ui.event.main.Event_MainFragment;
import com.dutch.hdh.dutchpayapp.ui.login.LoginFragment;
import com.dutch.hdh.dutchpayapp.ui.main.activity.MainActivity;
import com.dutch.hdh.dutchpayapp.ui.solopay.SoloPayFragment;
import com.kinda.alert.KAlertDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragmentPresenter implements MainFragmentContract.Presenter {

    private MainFragmentContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private Activity mActivity;
    private MyApplication myApplication;
    private EventImageSliderAdapter mEventImageSliderAdapter;
    private EventList eventList;

    public MainFragmentPresenter(MainFragmentContract.View mView, Context mContext, FragmentManager mFragmentManager, Activity mActivity) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        this.mActivity = mActivity;
        mEventImageSliderAdapter = new EventImageSliderAdapter(mView, mContext, mFragmentManager);
        myApplication = MyApplication.getInstance();
    }

    /**
     * 로그인상태 불러오기
     */
    @Override
    public void initLoginState() {
        mView.showUserInfo(myApplication.getUserInfo().getUserName(), myApplication.getUserInfo().getUserMoney(), myApplication.getUserInfo().isUserState());
    }

    /**
     * 이미지슬라이드 어댑터 연결
     */
    @Override
    public void setAdapter(ViewPager viewPager, TabLayout tabLayout) {


        Call<EventList> eventOnGoingList = MyApplication
                .getRestAdapter()
                .selectOnGoingEvent();

        eventOnGoingList.enqueue(new Callback<EventList>() {
            @Override
            public void onResponse(Call<EventList> call, Response<EventList> response) {
                if (response.isSuccessful()) {
                    eventList = response.body();
                    mEventImageSliderAdapter.setmEventArrayList(eventList.getEventList());
                    mView.changeEventTitle(eventList.getEventList().get(0).getEventTitle());
                    viewPager.setAdapter(mEventImageSliderAdapter);
                    tabLayout.setupWithViewPager(viewPager, true);

                } else {
                    Log.d("실패", "이미지 수신 실패");
                }
            }

            @Override
            public void onFailure(Call<EventList> call, Throwable t) {
                Log.d("error", t.getMessage());
                Log.d("error", t.getLocalizedMessage());
            }
        });


    }

    /**
     * 개인결제 시작하기 클릭 이벤트 처리
     */
    @Override
    public void clickSolopay() {

        //프래그먼트 이동
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);
        SoloPayFragment soloPayFragment = new SoloPayFragment();
        fragmentTransaction.replace(R.id.flFragmentContainer, soloPayFragment, SoloPayFragment.class.getName());
        fragmentTransaction.addToBackStack(SoloPayFragment.class.getName());
        fragmentTransaction.commit();
    }

    /**
     * 더치페이 시작하기 클릭 이벤트 처리
     */
    @Override
    public void clickDutchpay() {

    }

    @Override
    public void clickAllEvent() {
        //프래그먼트 이동
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

        Event_MainFragment event_MainFragment = new Event_MainFragment();
        fragmentTransaction.replace(R.id.flFragmentContainer, event_MainFragment, Event_MainFragment.class.getName());
        fragmentTransaction.addToBackStack(Event_MainFragment.class.getName());
        fragmentTransaction.commit();
    }

    @Override
    public void slideViewPagerAction(int position) {
        mView.changeEventTitle(eventList.getEventList().get(position).getEventTitle());
    }
}
