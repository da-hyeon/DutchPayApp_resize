package com.dutch.hdh.dutchpayapp.ui.mygroup.telephonedirectory;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentManager;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.widget.ListView;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.adapter.ListView_TelephoneDirectoryAdapter;
import com.dutch.hdh.dutchpayapp.data.db.GroupParticipants;
import com.dutch.hdh.dutchpayapp.data.db.TelephoneDirectory;

import java.util.ArrayList;
import java.util.Locale;

public class MyGroup_TelephoneDirectoryPresenter implements MyGroup_TelephoneDirectoryContract.Presenter {

    private MyGroup_TelephoneDirectoryContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private MyApplication mMyApplication;

    private ListView_TelephoneDirectoryAdapter mListview_telephoneDirectoryAdapter;

    private ArrayList<TelephoneDirectory> mTelephoneDirectoryArrayList;
    private ArrayList<TelephoneDirectory> mTelephoneDirectorySaveArrayList;

    public MyGroup_TelephoneDirectoryPresenter(MyGroup_TelephoneDirectoryContract.View mView, Context mContext, FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        mMyApplication = MyApplication.getInstance();
        mTelephoneDirectoryArrayList = new ArrayList<>();
        mTelephoneDirectorySaveArrayList = new ArrayList<>();
    }

    @SuppressLint("NewApi")
    @Override
    public void callingTelephoneDirectory(ContentResolver getContentResolver, Bundle bundle) {
        ArrayList<GroupParticipants> duplicateCheckArrayList = bundle.getParcelableArrayList("itemList");
        int count = duplicateCheckArrayList.size();
        try {
            Cursor c = getContentResolver.query(
                    ContactsContract.CommonDataKinds
                            .Phone.CONTENT_URI,
                    null, // 조회할 컬럼명
                    null, // 조건 절
                    null, // 조건절의 파라미터
                    null);// 정렬 방향

            c.moveToFirst();

            while (c.moveToNext()) {
                String name = c.getString
                        (c.getColumnIndex(ContactsContract
                                .CommonDataKinds.Phone.DISPLAY_NAME));
                String phoneNumber = c.getString
                        (c.getColumnIndex(ContactsContract
                                .CommonDataKinds.Phone.NUMBER));


                boolean duplicateCheck = false;
                String directoryPhoneNumber = PhoneNumberUtils.formatNumber(phoneNumber, Locale.getDefault().getCountry());
                //리스트에 있는 정보는 제외하고 Add해야함.
                for (int i = 0; i < count; i++) {
                    //전화번호가 같다면
                    if (PhoneNumberUtils.formatNumber(duplicateCheckArrayList.get(i).getPhoneNumber(), Locale.getDefault().getCountry()).equals(directoryPhoneNumber)) {
                        duplicateCheck = true;
                    }
                }
                if (!duplicateCheck) {
                    mTelephoneDirectoryArrayList.add(new TelephoneDirectory(name, directoryPhoneNumber, false));
                    mTelephoneDirectorySaveArrayList.add(new TelephoneDirectory(name, directoryPhoneNumber.replace("-", ""), false));
                }
            }

            mListview_telephoneDirectoryAdapter = new ListView_TelephoneDirectoryAdapter(mView, this , mContext, mTelephoneDirectoryArrayList);

            c.close();

        } catch (SecurityException e) {
            mView.showErrorDialog("실패", "전화부호부에 연락처가 없습니다.");
        }
    }

    @Override
    public void initListViewData(ListView listView) {
        if (mListview_telephoneDirectoryAdapter != null) {
            listView.setAdapter(mListview_telephoneDirectoryAdapter);
        }
    }

    @Override
    public void searchTelephoneDirectories(String search) {
        mTelephoneDirectoryArrayList.clear();
        for (int i = 0; i < mTelephoneDirectorySaveArrayList.size(); i++) {
            if (isStringDouble(search)) {
                if (mTelephoneDirectorySaveArrayList.get(i).getPhoneNumber().contains(search)) {
                    mTelephoneDirectoryArrayList.add(mTelephoneDirectorySaveArrayList.get(i));
                }
            } else {
                if (mTelephoneDirectorySaveArrayList.get(i).getName().contains(search)) {
                    mTelephoneDirectoryArrayList.add(mTelephoneDirectorySaveArrayList.get(i));
                }
            }
        }

        //입력한값이 리스트에 없을때
        if (mTelephoneDirectoryArrayList.size() == 0) {
            mListview_telephoneDirectoryAdapter.setVisibility(false);
            mTelephoneDirectoryArrayList.add(new TelephoneDirectory("", "연락처에 " + search + " (이)가 없습니다.", false));
        } else {
            mListview_telephoneDirectoryAdapter.setVisibility(true);
        }
        mListview_telephoneDirectoryAdapter.notifyDataSetChanged();

    }

    @Override
    public void clickAdd(Bundle bundle) {
        if (mListview_telephoneDirectoryAdapter.getAddMemberCount() > 0) {

            Bundle bundle1 = new Bundle();
            bundle1.putParcelableArrayList("itemList", bundle.getParcelableArrayList("itemList"));
            bundle1.putParcelableArrayList("telephoneInputMember", mListview_telephoneDirectoryAdapter.getSelectList());
            mMyApplication.getMyGroup_EditFragment().setArguments(bundle1);
            mFragmentManager.popBackStack();
        } else {
            mView.showFailDialog("실패", "선택한 연락처가 없습니다.");
        }
    }

    @Override
    public void clickBackPressed() {
        mView.showWarningDialog("경고", "정말로 추가를 그만하시겠습니까?\n선택한 정보는 저장되지 않습니다.");
    }

    @Override
    public void clickWarningDialogOK(Bundle bundle) {
        Bundle bundle1 = new Bundle();
        bundle1.putParcelableArrayList("itemList", bundle.getParcelableArrayList("itemList"));
        bundle1.putParcelableArrayList("telephoneInputMember", null);
        mMyApplication.getMyGroup_EditFragment().setArguments(bundle1);
        mFragmentManager.popBackStack();
    }

    @Override
    public void setCheckState(int position , boolean state){
        mTelephoneDirectorySaveArrayList.get(position).setCheckState(state);
    }

    public static boolean isStringDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
