package com.dutch.hdh.dutchpayapp.ui.event.main;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.widget.ListView;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.Listview_EventAdapter;
import com.dutch.hdh.dutchpayapp.data.db.Event;

import java.util.ArrayList;

public class Event_MainPresenter implements Event_MainContract.Presenter {

    private Event_MainContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;

    private Listview_EventAdapter mListview_EventAdapter;
    private ArrayList<Event> mEventArrayList;

    public Event_MainPresenter(Event_MainContract.View mView, Context mContext, FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        mEventArrayList = new ArrayList<>();
        mListview_EventAdapter = new Listview_EventAdapter(mContext, mEventArrayList);
    }

    @Override
    public void initListViewData(ListView listView) {
        listView.setAdapter(mListview_EventAdapter);
        clickOnGoingEvent();
    }

    @Override
    public void clickOnGoingEvent() {
        mEventArrayList.clear();
        mEventArrayList.add(new Event("아이유와 함께하는 더치페이 할인 이벤트" , ContextCompat.getDrawable(mContext, R.drawable.dutchpay_event)));
        mEventArrayList.add(new Event("아이유와 함께하는 더치페이 할인 이벤트" , ContextCompat.getDrawable(mContext, R.drawable.dutchpay_event)));
        mEventArrayList.add(new Event("아이유와 함께하는 더치페이 할인 이벤트" , ContextCompat.getDrawable(mContext, R.drawable.dutchpay_event)));
        mEventArrayList.add(new Event("아이유와 함께하는 더치페이 할인 이벤트" , ContextCompat.getDrawable(mContext, R.drawable.dutchpay_event)));
        mEventArrayList.add(new Event("아이유와 함께하는 더치페이 할인 이벤트" , ContextCompat.getDrawable(mContext, R.drawable.dutchpay_event)));
        mEventArrayList.add(new Event("아이유와 함께하는 더치페이 할인 이벤트" , ContextCompat.getDrawable(mContext, R.drawable.dutchpay_event)));
        mListview_EventAdapter.setOnGoingCheck(true);
        mListview_EventAdapter.setmEventArrayList(mEventArrayList);
        mListview_EventAdapter.notifyDataSetChanged();
    }

    @Override
    public void clickEndProgressEvent() {
        mEventArrayList.clear();
        mEventArrayList.add(new Event("종료된 이벤트 입니다." , ContextCompat.getDrawable(mContext, R.drawable.dutchpay_event)));
        mEventArrayList.add(new Event("종료된 이벤트 입니다." , ContextCompat.getDrawable(mContext, R.drawable.dutchpay_event)));
        mEventArrayList.add(new Event("종료된 이벤트 입니다." , ContextCompat.getDrawable(mContext, R.drawable.dutchpay_event)));
        mEventArrayList.add(new Event("종료된 이벤트 입니다." , ContextCompat.getDrawable(mContext, R.drawable.dutchpay_event)));
        mEventArrayList.add(new Event("종료된 이벤트 입니다." , ContextCompat.getDrawable(mContext, R.drawable.dutchpay_event)));
        mEventArrayList.add(new Event("종료된 이벤트 입니다." , ContextCompat.getDrawable(mContext, R.drawable.dutchpay_event)));
        mListview_EventAdapter.setOnGoingCheck(false);
        mListview_EventAdapter.setmEventArrayList(mEventArrayList);
        mListview_EventAdapter.notifyDataSetChanged();
    }

}
