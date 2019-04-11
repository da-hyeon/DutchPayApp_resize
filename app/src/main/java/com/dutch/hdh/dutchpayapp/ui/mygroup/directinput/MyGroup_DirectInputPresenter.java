package com.dutch.hdh.dutchpayapp.ui.mygroup.directinput;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentManager;
import android.widget.ListView;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.adapter.Listview_DirectInputParticipantsAdapter;

import java.util.ArrayList;

public class MyGroup_DirectInputPresenter implements MyGroup_DirectInputContract.Presenter {

    private MyGroup_DirectInputContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;

    private MyApplication mMyApplication;
    private Listview_DirectInputParticipantsAdapter mListview_DirectInputParticipantsAdapter;

    public MyGroup_DirectInputPresenter(MyGroup_DirectInputContract.View mView, Context mContext , FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        mMyApplication = MyApplication.getInstance();
    }

    @Override
    public void initListViewData(ListView listView) {
        mListview_DirectInputParticipantsAdapter = new Listview_DirectInputParticipantsAdapter(mView, mContext);
        mListview_DirectInputParticipantsAdapter.addMember();
        mView.changeAddMemberCount(mListview_DirectInputParticipantsAdapter.getCount());
        listView.setAdapter(mListview_DirectInputParticipantsAdapter);
    }

    @Override
    public void clickAddMember() {
        mListview_DirectInputParticipantsAdapter.addMember();
        mView.changeAddMemberCount(mListview_DirectInputParticipantsAdapter.getCount());
        mListview_DirectInputParticipantsAdapter.notifyDataSetInvalidated();
    }

    @Override
    public void clickMemberAdditionConfirmed(Bundle bundle) {
        if (mListview_DirectInputParticipantsAdapter.isEmptyText()) {
            mView.showFailDialog("실패", "빈칸을 채워주세요.");
        } else {
            Bundle bundle1 = new Bundle();
            bundle1.putParcelableArrayList("itemList" , bundle.getParcelableArrayList("itemList"));
            bundle1.putParcelableArrayList("InputMember", mListview_DirectInputParticipantsAdapter.getList());
            mMyApplication.getMyGroup_EditFragment().setArguments(bundle1);
            mFragmentManager.popBackStack();
        }
    }

    @Override
    public void clickBackPressed() {
        mView.showWarningDialog("경고", "정말로 추가를 그만하시겠습니까?\n입력된 정보는 저장되지 않습니다.");
    }

    @Override
    public void clickWarningDialogOK(Bundle bundle) {
        Bundle bundle1 = new Bundle();
        bundle1.putParcelableArrayList("itemList" , bundle.getParcelableArrayList("itemList"));
        bundle1.putParcelableArrayList("InputMember", null);
        mMyApplication.getMyGroup_EditFragment().setArguments(bundle1);
        mFragmentManager.popBackStack();
    }
}
