package com.dutch.hdh.dutchpayapp.ui.event.main;

import android.widget.ListView;

public interface Event_MainContract {
    interface View{

    }
    interface Presenter{
        void initListViewData(ListView listView);

        void clickOnGoingEvent();
        void clickEndProgressEvent();
    }
}
