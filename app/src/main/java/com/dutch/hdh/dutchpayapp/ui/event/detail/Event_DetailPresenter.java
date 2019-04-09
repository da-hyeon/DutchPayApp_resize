package com.dutch.hdh.dutchpayapp.ui.event.detail;

import android.content.Context;
import android.support.v4.app.FragmentManager;

public class Event_DetailPresenter implements Event_DetailContract.Presenter{

    private Event_DetailContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;

    public Event_DetailPresenter(Event_DetailContract.View mView, Context mContext, FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
    }
}
