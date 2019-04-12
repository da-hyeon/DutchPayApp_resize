package com.dutch.hdh.dutchpayapp.ui.mygroup.telephonedirectory;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentManager;
import android.telephony.PhoneNumberUtils;
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
    private Bundle mBundle;
    
    private MyApplication mMyApplication;

    private ListView_TelephoneDirectoryAdapter mListview_telephoneDirectoryAdapter;

    private ArrayList<TelephoneDirectory> mTelephoneDirectoryArrayList;
    private ArrayList<TelephoneDirectory> mTelephoneDirectorySaveArrayList;

    /**
     * 생성자
     */
    MyGroup_TelephoneDirectoryPresenter(MyGroup_TelephoneDirectoryContract.View mView, Context mContext, FragmentManager mFragmentManager , Bundle mBundle) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        this.mBundle = mBundle;
        
        mMyApplication = MyApplication.getInstance();
        mTelephoneDirectoryArrayList = new ArrayList<>();
        mTelephoneDirectorySaveArrayList = new ArrayList<>();
    }

    /**
     * 전화부호부 불러오기
     */
    @SuppressLint("NewApi")
    @Override
    public void callingTelephoneDirectory(ContentResolver getContentResolver) {
        ArrayList<GroupParticipants> duplicateCheckArrayList = mBundle.getParcelableArrayList("itemList");
        int count = 0;

        if (duplicateCheckArrayList != null) {
            count = duplicateCheckArrayList.size();
        }

        try {
            Cursor cursor = getContentResolver.query(
                    ContactsContract.CommonDataKinds
                            .Phone.CONTENT_URI,
                    null, // 조회할 컬럼명
                    null, // 조건 절
                    null, // 조건절의 파라미터
                    null);// 정렬 방향

            if (cursor != null) {
                cursor.moveToFirst();
            }

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String name = cursor.getString
                            (cursor.getColumnIndex(ContactsContract
                                    .CommonDataKinds.Phone.DISPLAY_NAME));
                    String phoneNumber = cursor.getString
                            (cursor.getColumnIndex(ContactsContract
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
            }

            mListview_telephoneDirectoryAdapter = new ListView_TelephoneDirectoryAdapter(mView, this , mContext, mTelephoneDirectoryArrayList);

            if (cursor != null) {
                cursor.close();
            }

        } catch (SecurityException e) {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("itemList", mBundle.getParcelableArrayList("itemList"));
            bundle.putParcelableArrayList("InputMember", null);
            bundle.putParcelableArrayList("telephoneInputMember", null);
            mMyApplication.getMyGroup_EditFragment().setArguments(bundle);
            mView.showErrorDialog("실패", "전화부호부에 연락처가 없습니다.");
        }
    }

    /**
     * Adapter 세팅
     */
    @Override
    public void initListViewData(ListView listView) {
        if (mListview_telephoneDirectoryAdapter != null) {
            listView.setAdapter(mListview_telephoneDirectoryAdapter);
        }
    }

    /**
     * 전화부호부 검색
     */
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


    /**
     * 멤버 추가하기 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickAdd() {
        if (mListview_telephoneDirectoryAdapter.getAddMemberCount() > 0) {

            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("itemList", mBundle.getParcelableArrayList("itemList"));
            bundle.putParcelableArrayList("InputMember", null);
            bundle.putParcelableArrayList("telephoneInputMember", mListview_telephoneDirectoryAdapter.getSelectList());
            mMyApplication.getMyGroup_EditFragment().setArguments(bundle);
            mFragmentManager.popBackStack();
        } else {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("itemList", mBundle.getParcelableArrayList("itemList"));
            bundle.putParcelableArrayList("InputMember", null);
            bundle.putParcelableArrayList("telephoneInputMember", null);
            mMyApplication.getMyGroup_EditFragment().setArguments(bundle);
            mView.showFailDialog("실패", "선택한 연락처가 없습니다.");
        }
    }

    /**
     * 뒤로가기 이벤트 처리
     */
    @Override
    public void clickBackPressed() {
        mView.showWarningDialog("경고", "정말로 추가를 그만하시겠습니까?\n선택한 정보는 저장되지 않습니다.");
    }

    /**
     * 경고다이얼로그 확인 버튼 이벤트 처리
     */
    @Override
    public void clickWarningDialogOK() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("itemList", mBundle.getParcelableArrayList("itemList"));
        bundle.putParcelableArrayList("InputMember", null);
        bundle.putParcelableArrayList("telephoneInputMember", null);
        mMyApplication.getMyGroup_EditFragment().setArguments(bundle);
        mFragmentManager.popBackStack();
    }

    /**
     * 체크 이벤트 처리
     */
    @Override
    public void setCheckState(int position , boolean state){
        mTelephoneDirectorySaveArrayList.get(position).setCheckState(state);
    }

    /**
     * Double , String 구별
     * true = Double , false = String
     */
    private static boolean isStringDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
