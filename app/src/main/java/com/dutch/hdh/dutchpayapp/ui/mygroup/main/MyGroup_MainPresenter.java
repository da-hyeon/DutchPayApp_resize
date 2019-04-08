package com.dutch.hdh.dutchpayapp.ui.mygroup.main;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.ListView;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.Listview_MyGroupAdapter;
import com.dutch.hdh.dutchpayapp.data.db.MyGroup;
import com.dutch.hdh.dutchpayapp.ui.mygroup.edit.MyGroup_EditFragment;

import java.util.ArrayList;

public class MyGroup_MainPresenter implements MyGroup_MainContract.Presenter{
    private MyGroup_MainContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private Listview_MyGroupAdapter mListview_MyGroupAdapter;
    private ArrayList<MyGroup> mMyGroupArrayList;

    private MyApplication mMyApplication;

    public MyGroup_MainPresenter(MyGroup_MainContract.View mView, Context mContext , FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        mMyGroupArrayList = new ArrayList<>();
        mMyApplication = MyApplication.getInstance();
    }

    @Override
    public void initListViewData(ListView listView) {
        mMyGroupArrayList.add(new MyGroup("0" , "5" , "더치페이코리아"));
        mMyGroupArrayList.add(new MyGroup("1" , "3" , "더더"));
        mMyGroupArrayList.add(new MyGroup("2" , "2" , "치치"));
        mMyGroupArrayList.add(new MyGroup("3" , "11" , "페페"));
        mMyGroupArrayList.add(new MyGroup("4" , "15" , "이이"));
        mMyGroupArrayList.add(new MyGroup("4" , "15" , "이이"));
        mMyGroupArrayList.add(new MyGroup("4" , "15" , "이이"));
        mMyGroupArrayList.add(new MyGroup("4" , "15" , "이이"));
        mMyGroupArrayList.add(new MyGroup("4" , "15" , "이이"));
        mMyGroupArrayList.add(new MyGroup("4" , "15" , "이이"));
        mMyGroupArrayList.add(new MyGroup("4" , "15" , "이이"));
        mMyGroupArrayList.add(new MyGroup("4" , "15" , "이이"));
        mMyGroupArrayList.add(new MyGroup("4" , "15" , "이이"));
        mMyGroupArrayList.add(new MyGroup("4" , "15" , "이이"));
        mMyGroupArrayList.add(new MyGroup("4" , "15" , "이이"));
        mMyGroupArrayList.add(new MyGroup("4" , "15" , "이이"));

        mListview_MyGroupAdapter = new Listview_MyGroupAdapter(mContext, mMyGroupArrayList , mFragmentManager);
        listView.setAdapter(mListview_MyGroupAdapter);
    }

    @Override
    public void clickAddGroup() {
        mMyApplication.entranceGroupPath = false;
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

        mMyApplication.getMyGroup_EditFragment().setArguments(null);
        fragmentTransaction.replace(R.id.flFragmentContainer, mMyApplication.getMyGroup_EditFragment(), MyGroup_EditFragment.class.getName());
        fragmentTransaction.addToBackStack(MyGroup_EditFragment.class.getName());
        fragmentTransaction.commit();
    }
}
