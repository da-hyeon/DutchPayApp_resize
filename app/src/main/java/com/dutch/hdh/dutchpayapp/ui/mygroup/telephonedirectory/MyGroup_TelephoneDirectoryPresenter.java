package com.dutch.hdh.dutchpayapp.ui.mygroup.telephonedirectory;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.widget.ListView;

import com.dutch.hdh.dutchpayapp.adapter.ListView_TelephoneDirectoryAdapter;
import com.dutch.hdh.dutchpayapp.data.db.TelephoneDirectory;

import java.util.ArrayList;
import java.util.Locale;

public class MyGroup_TelephoneDirectoryPresenter implements MyGroup_TelephoneDirectoryContract.Presenter {

    private MyGroup_TelephoneDirectoryContract.View mView;
    private Context mContext;

    private ListView_TelephoneDirectoryAdapter mListview_telephoneDirectoryAdapter;
    private ArrayList<TelephoneDirectory> mTelephoneDirectoryArrayList;

    public MyGroup_TelephoneDirectoryPresenter(MyGroup_TelephoneDirectoryContract.View mView, Context mContext) {
        this.mView = mView;
        this.mContext = mContext;
        mTelephoneDirectoryArrayList = new ArrayList<>();
    }

    @SuppressLint("NewApi")
    @Override
    public void callingTelephoneDirectory(ContentResolver getContentResolver) {
        try {
            Cursor c = getContentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                    null, null, null,
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " asc");

            while (c.moveToNext()) {
                // 연락처 id 값
                String id = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
                // 연락처 대표 이름
                String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));

                // ID로 전화 정보 조회
                Cursor phoneCursor = getContentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                        null, null);

                // 데이터가 있는 경우
                if (phoneCursor.moveToFirst()) {
                    String number = phoneCursor.getString(phoneCursor.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER));
                    mTelephoneDirectoryArrayList.add(new TelephoneDirectory(name, PhoneNumberUtils.formatNumber(number, Locale.getDefault().getCountry()) , false));
                    //saveList.add(new ListViewItem_TelephoneDirectory(id, name, number.replace("-" , "")));
                }

                phoneCursor.close();
            }// end while
            c.close();
            mListview_telephoneDirectoryAdapter = new ListView_TelephoneDirectoryAdapter(mContext , mTelephoneDirectoryArrayList);
        } catch (SecurityException e){
            Log.d("error" , "저장된 연락처가 없다");
        }
    }

    @Override
    public void initListViewData(ListView listView) {
        mListview_telephoneDirectoryAdapter = new ListView_TelephoneDirectoryAdapter(mContext, mTelephoneDirectoryArrayList);
        listView.setAdapter(mListview_telephoneDirectoryAdapter);
    }
}
