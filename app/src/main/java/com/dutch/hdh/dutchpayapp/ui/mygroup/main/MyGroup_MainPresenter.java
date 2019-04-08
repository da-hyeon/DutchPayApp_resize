package com.dutch.hdh.dutchpayapp.ui.mygroup.main;

import android.content.Context;
import android.widget.ListView;

import com.dutch.hdh.dutchpayapp.adapter.Listview_MyGroupAdapter;
import com.dutch.hdh.dutchpayapp.data.db.MyGroup;

import java.util.ArrayList;

public class MyGroup_MainPresenter implements MyGroup_MainContract.Presenter{
    private MyGroup_MainContract.View mView;
    private Context mContext;
    private Listview_MyGroupAdapter mListview_MyGroupAdapter;
    private ArrayList<MyGroup> mMyGroupArrayList;

    public MyGroup_MainPresenter(MyGroup_MainContract.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
        mMyGroupArrayList = new ArrayList<>();

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

        mListview_MyGroupAdapter = new Listview_MyGroupAdapter(mContext, mMyGroupArrayList);
        listView.setAdapter(mListview_MyGroupAdapter);
    }
}
