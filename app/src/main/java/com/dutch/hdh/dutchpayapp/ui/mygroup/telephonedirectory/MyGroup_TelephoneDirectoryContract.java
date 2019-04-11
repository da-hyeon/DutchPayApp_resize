package com.dutch.hdh.dutchpayapp.ui.mygroup.telephonedirectory;

import android.content.ContentResolver;
import android.os.Bundle;
import android.widget.ListView;

public interface MyGroup_TelephoneDirectoryContract {
    interface View{
        void showFailDialog(String title, String content);
        void showWarningDialog(String title, String content);
        void showErrorDialog(String title, String content);

        void changeAddMember(int count);
    }
    interface Presenter{
        void callingTelephoneDirectory(ContentResolver getContentResolver , Bundle bundle);
        void initListViewData(ListView listView);
        void setCheckState(int position , boolean state);
        void searchTelephoneDirectories(String search);

        void clickAdd(Bundle bundle);
        void clickBackPressed();
        void clickWarningDialogOK(Bundle bundle);
    }
}
