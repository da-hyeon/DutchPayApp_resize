package com.dutch.hdh.dutchpayapp.ui.mygroup.edit;

import android.os.Bundle;
import android.widget.ListView;

public class MyGroup_EditContract {
    public interface View{
        void showWarningDialog(String title, String content);

        void changePersonCount(int num);
    }
    interface Presenter {
        void refreshData(Bundle bundle);
        void onResume();

        void initListViewData(ListView listView);

        void clickTelephoneDirectory();
        void clickDirectInput();
        void clickBackPressed();
    }
}
