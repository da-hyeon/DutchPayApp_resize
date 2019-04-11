package com.dutch.hdh.dutchpayapp.ui.mygroup.main;

import android.widget.ListView;

import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragmentContract;
import com.dutch.hdh.dutchpayapp.databinding.FragmentMyGroupBinding;

public interface MyGroup_MainContract extends BaseFragmentContract.View {
    interface View{
        MyGroup_MainContract.Presenter getmPresenter();
        FragmentMyGroupBinding getmBinding();
    }
    interface Presenter{
        //void requestGroupList();
        void initListViewData(ListView listView);

        void clickAddGroup();
    }
}
