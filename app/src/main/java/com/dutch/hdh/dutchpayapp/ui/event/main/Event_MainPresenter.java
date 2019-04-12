package com.dutch.hdh.dutchpayapp.ui.event.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.ListView;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.adapter.Listview_EventAdapter;
import com.dutch.hdh.dutchpayapp.data.db.Event;
import com.dutch.hdh.dutchpayapp.data.db.EventList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Event_MainPresenter implements Event_MainContract.Presenter {

    private Event_MainContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;

    private Listview_EventAdapter mListview_EventAdapter;
    private ArrayList<Event> mEventArrayList;

    /**
     * 생성자
     */
    Event_MainPresenter(Event_MainContract.View mView, Context mContext, FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        mEventArrayList = new ArrayList<>();
        mListview_EventAdapter = new Listview_EventAdapter(mContext, mEventArrayList , mFragmentManager);
    }

    /**
     * Adapter 세팅
     */
    @Override
    public void initListViewData(ListView listView) {
        listView.setAdapter(mListview_EventAdapter);
        clickOnGoingEvent();
    }

    /**
     * 진행중인 이벤트 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickOnGoingEvent() {
        mEventArrayList.clear();

        Call<EventList> eventOnGoingList = MyApplication
                .getRestAdapter()
                .selectOnGoingEvent();

        eventOnGoingList.enqueue(new Callback<EventList>() {
            @Override
            public void onResponse(@NonNull Call<EventList> call, @NonNull Response<EventList> response) {
                if (response.isSuccessful()) {
                    EventList eventList = response.body();
                    if (eventList != null) {
                        mListview_EventAdapter.setmEventArrayList(eventList.getEventList());
                    }
                    mListview_EventAdapter.notifyDataSetChanged();

                } else {
                    mView.showFailDialog("실패" , "데이터 로딩 실패");
                }
            }

            @Override
            public void onFailure(@NonNull Call<EventList> call, @NonNull Throwable t) {
                Log.d("error" , t.getMessage());
                Log.d("error" , t.getLocalizedMessage());
            }
        });

    }

    /**
     * 종료된 이벤트 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickEndProgressEvent() {
        mEventArrayList.clear();

        Call<EventList> eventEndProgressList = MyApplication
                .getRestAdapter()
                .selectEndProgressEvent();

        eventEndProgressList.enqueue(new Callback<EventList>() {
            @Override
            public void onResponse(@NonNull Call<EventList> call, @NonNull Response<EventList> response) {
                if (response.body() != null) {
                    EventList eventList = response.body();
                    mListview_EventAdapter.setmEventArrayList(eventList.getEventList());
                    mListview_EventAdapter.notifyDataSetChanged();

                } else {
                    mView.showFailDialog("실패" , "데이터 로딩 실패");
                }
            }

            @Override
            public void onFailure(@NonNull Call<EventList> call, @NonNull Throwable t) {
                Log.d("error" , t.getMessage());
                Log.d("error" , t.getLocalizedMessage());
            }
        });
    }
}
