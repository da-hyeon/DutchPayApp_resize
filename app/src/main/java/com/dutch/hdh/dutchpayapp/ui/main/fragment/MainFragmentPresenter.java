package com.dutch.hdh.dutchpayapp.ui.main.fragment;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.EventImageSliderAdapter;
import com.dutch.hdh.dutchpayapp.data.db.EventList;
import com.dutch.hdh.dutchpayapp.ui.event.main.Event_MainFragment;
import com.dutch.hdh.dutchpayapp.ui.personal_payment.main.PersonalPayment_MainFragment;

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

    MainFragmentPresenter(MainFragmentContract.View mView, Context mContext, FragmentManager mFragmentManager, Activity mActivity) {
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
     * Fragment 재진입
     */
    @Override
    public void onResume() {
        mView.changeUserMoney(myApplication.getUserInfo().getUserMoney());
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
            public void onResponse(@NonNull Call<EventList> call, @NonNull Response<EventList> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        eventList = response.body();
                        mEventImageSliderAdapter.setmEventArrayList(eventList.getEventList());
                        mView.changeEventTitle(eventList.getEventList().get(0).getEventTitle());
                        viewPager.setAdapter(mEventImageSliderAdapter);
                        tabLayout.setupWithViewPager(viewPager, true);
                    }
                } else {
                    Log.d("실패", "이미지 수신 실패");
                }
            }

            @Override
            public void onFailure(@NonNull Call<EventList> call, @NonNull Throwable t) {
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
        PersonalPayment_MainFragment soloPayFragment = new PersonalPayment_MainFragment();
        fragmentTransaction.replace(R.id.flFragmentContainer, soloPayFragment, PersonalPayment_MainFragment.class.getName());
        fragmentTransaction.addToBackStack(PersonalPayment_MainFragment.class.getName());
        fragmentTransaction.commit();
    }

    /**
     * 더치페이 시작하기 클릭 이벤트 처리
     */
    @Override
    public void clickDutchpay() {

    }

    /**
     * 모든 이벤트 보기 버튼 클릭 이벤트 처리
     */
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

    /**
     * 이벤트뷰 슬라이드 이벤트 처리
     */
    @Override
    public void slideViewPagerAction(int position) {
        mView.changeEventTitle(eventList.getEventList().get(position).getEventTitle());
    }
}
