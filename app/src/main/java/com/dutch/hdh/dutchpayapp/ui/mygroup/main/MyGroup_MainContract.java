package com.dutch.hdh.dutchpayapp.ui.mygroup.main;

import android.widget.ListView;

public interface MyGroup_MainContract {
    interface View{

    }
    interface Presenter{
        void initListViewData(ListView listView);

        void clickAddGroup();
    }
}
