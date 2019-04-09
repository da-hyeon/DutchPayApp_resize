package com.dutch.hdh.dutchpayapp.ui.mygroup.edit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.widget.ListView;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.Listview_GroupParticipantsAdapter;
import com.dutch.hdh.dutchpayapp.data.db.DirectInputParticipants;
import com.dutch.hdh.dutchpayapp.data.db.TelephoneDirectory;
import com.dutch.hdh.dutchpayapp.ui.mygroup.directinput.MyGroup_DirectInputFragment;
import com.dutch.hdh.dutchpayapp.ui.mygroup.telephonedirectory.MyGroup_TelephoneDirectoryFragment;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Locale;

public class MyGroup_EditPresenter implements MyGroup_EditContract.Presenter {

    private MyGroup_EditContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;

    private Listview_GroupParticipantsAdapter mListview_GroupParticipantsAdapter;

    public MyGroup_EditPresenter(MyGroup_EditContract.View mView, Context mContext , FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        mListview_GroupParticipantsAdapter = new Listview_GroupParticipantsAdapter(mView , mContext);
    }

    @SuppressLint("NewApi")
    @Override
    public void refreshData(Bundle bundle) {
        if (bundle != null) {

            ArrayList<DirectInputParticipants> mDirectInputParticipantsArrayList = bundle.getParcelableArrayList("itemList" );
            if(mDirectInputParticipantsArrayList.size() > 0) {
                    mListview_GroupParticipantsAdapter.setList(bundle.getParcelableArrayList("itemList" ));
            }

            mDirectInputParticipantsArrayList = bundle.getParcelableArrayList("InputMember" );
            if(mDirectInputParticipantsArrayList != null) {
                for (int i = 0; i < mDirectInputParticipantsArrayList.size(); i++) {
                    mListview_GroupParticipantsAdapter.addItem(mDirectInputParticipantsArrayList.get(i).getName(), PhoneNumberUtils.formatNumber(mDirectInputParticipantsArrayList.get(i).getPhoneNumber(), Locale.getDefault().getCountry()));
                }
            }


            ArrayList<TelephoneDirectory> mTelephoneDirectoryArrayList = bundle.getParcelableArrayList("telephoneInputMember" );
            if(mTelephoneDirectoryArrayList != null) {
                for (int i = 0; i < mTelephoneDirectoryArrayList.size(); i++) {
                    mListview_GroupParticipantsAdapter.addItem(mTelephoneDirectoryArrayList.get(i).getName(), mTelephoneDirectoryArrayList.get(i).getPhoneNumber());
                }
            }

            mListview_GroupParticipantsAdapter.notifyDataSetInvalidated();
            mView.changePersonCount(mListview_GroupParticipantsAdapter.getCount());
        }
    }

    @Override
    public void onResume() {

    }

    @Override
    public void initListViewData(ListView listView) {
        listView.setAdapter(mListview_GroupParticipantsAdapter);
    }

    @Override
    public void clickTelephoneDirectory() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("itemList" , mListview_GroupParticipantsAdapter.getList());

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

        MyGroup_TelephoneDirectoryFragment mMyGroup_TelephoneDirectoryFragment = new MyGroup_TelephoneDirectoryFragment();

        mMyGroup_TelephoneDirectoryFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.flFragmentContainer, mMyGroup_TelephoneDirectoryFragment, MyGroup_TelephoneDirectoryFragment.class.getName());
        fragmentTransaction.addToBackStack(MyGroup_TelephoneDirectoryFragment.class.getName());
        fragmentTransaction.commit();

    }

    @Override
    public void clickDirectInput() {

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("itemList" , mListview_GroupParticipantsAdapter.getList());

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

        MyGroup_DirectInputFragment mMyGroup_DirectInputFragment = new MyGroup_DirectInputFragment();

        mMyGroup_DirectInputFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.flFragmentContainer, mMyGroup_DirectInputFragment, MyGroup_DirectInputFragment.class.getName());
        fragmentTransaction.addToBackStack(MyGroup_DirectInputFragment.class.getName());
        fragmentTransaction.commit();
    }

    @Override
    public void clickBackPressed() {
        mView.showWarningDialog("경고", "정말로 추가를 그만하시겠습니까?\n입력된 정보는 저장되지 않습니다.");
    }

    @Override
    public void clickComplete() {
        Log.d("data", new Gson().toJson(mListview_GroupParticipantsAdapter.getList()).toString());
    }
}
