package com.dutch.hdh.dutchpayapp.ui.mygroup.telephonedirectory;

import android.content.ContentResolver;
import android.widget.ListView;

import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragmentContract;

public interface MyGroup_TelephoneDirectoryContract {
    interface View extends BaseFragmentContract.View {
        void showWarningDialog(String title, String content);
        void showErrorDialog(String title, String content);

        void changeAddMember(int count);
    }
    interface Presenter{
        void callingTelephoneDirectory(ContentResolver getContentResolver);
        void initListViewData(ListView listView);
        void setCheckState(int position , boolean state);
        void searchTelephoneDirectories(String search);

        void clickAdd();
        void clickBackPressed();
        void clickWarningDialogOK();
    }
}
