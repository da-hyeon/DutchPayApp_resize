package com.dutch.hdh.dutchpayapp.ui.mygroup.telephonedirectory;

import android.content.ContentResolver;
import android.widget.ListView;

public interface MyGroup_TelephoneDirectoryContract {
    interface View{

    }
    interface Presenter{
        void callingTelephoneDirectory(ContentResolver getContentResolver);
        void initListViewData(ListView listView);
    }
}
