package com.dutch.hdh.dutchpayapp.ui.mygroup.directinput;

import android.os.Bundle;
import android.widget.ListView;

public interface MyGroup_DirectInputContract {

    interface View{
        void showFailDialog(String title, String content);
        void showWarningDialog(String title, String content);

        void changeAddMemberCount(int count);
    }

    interface Presenter {
        void initListViewData(ListView listView);

        void clickAddMember();
        void clickMemberAdditionConfirmed(Bundle bundle);
        void clickBackPressed();
        void clickWarningDialogOK(Bundle bundle);
    }
}