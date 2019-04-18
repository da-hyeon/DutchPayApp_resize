package com.dutch.hdh.dutchpayapp.ui.event.main;

import android.widget.ListView;

import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragmentContract;

public interface Event_MainContract {
    interface View  extends BaseFragmentContract.View {

    }
    interface Presenter{
        void initListViewData(ListView listView);

        void clickOnGoingEvent();
        void clickEndProgressEvent();
    }
}
