package com.dutch.hdh.dutchpayapp.ui.mygroup.main;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.ListView;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.Listview_MyGroupAdapter;
import com.dutch.hdh.dutchpayapp.data.db.GroupList;
import com.dutch.hdh.dutchpayapp.data.db.MyGroup;
import com.dutch.hdh.dutchpayapp.ui.mygroup.edit.MyGroup_EditFragment;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        //그룹불러오기

        Call<MyGroup> getGroupList = MyApplication
                .getRestAdapter()
                .getGroupList(mMyApplication.getUserInfo().getUserCode());

        getGroupList.enqueue(new Callback<MyGroup>() {
            @Override
            public void onResponse(Call<MyGroup> call, Response<MyGroup> response) {
                if(response.body() != null) {
                    MyGroup myGroup = response.body();
                    mListview_MyGroupAdapter = new Listview_MyGroupAdapter(mView, mContext, myGroup.getGroupLists(), mFragmentManager);
                    listView.setAdapter(mListview_MyGroupAdapter);
                }
            }

            @Override
            public void onFailure(Call<MyGroup> call, Throwable t) {
                Log.d("fail" , t.getLocalizedMessage());
                Log.d("fail" , t.getMessage());
            }
        });
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
