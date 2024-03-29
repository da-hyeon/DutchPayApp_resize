package com.dutch.hdh.dutchpayapp.ui.mygroup.edit;

import android.os.Bundle;
import android.widget.ListView;

import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragmentContract;

public class MyGroup_EditContract {
    public interface View extends BaseFragmentContract.View {

        void changePersonCount(int num);
        void changeGroupName(String groupName);
    }
    interface Presenter {
        void receiveGroupMemberWhenEditing();
        void receiveDirectlyEnteredMembers();
        void receiveTelephoneDirectoryMembers();
        void returningMembers();

        void refreshData();
        void onResume(Bundle bundle);

        void initListViewData(ListView listView);

        void clickTelephoneDirectory();
        void clickDirectInput();
        void clickBackPressed();
        void clickComplete(String groupName);

        void DutchFlagInit();
    }
}
