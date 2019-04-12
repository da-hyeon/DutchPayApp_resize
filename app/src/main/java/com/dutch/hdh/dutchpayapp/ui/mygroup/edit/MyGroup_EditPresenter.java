package com.dutch.hdh.dutchpayapp.ui.mygroup.edit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import android.widget.ListView;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.Listview_GroupParticipantsAdapter;
import com.dutch.hdh.dutchpayapp.data.db.DirectInputParticipants;
import com.dutch.hdh.dutchpayapp.data.db.TelephoneDirectory;
import com.dutch.hdh.dutchpayapp.data.db.UserInfo;
import com.dutch.hdh.dutchpayapp.data.db.userRegister;
import com.dutch.hdh.dutchpayapp.ui.mygroup.directinput.MyGroup_DirectInputFragment;
import com.dutch.hdh.dutchpayapp.ui.mygroup.telephonedirectory.MyGroup_TelephoneDirectoryFragment;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyGroup_EditPresenter implements MyGroup_EditContract.Presenter {

    private MyGroup_EditContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private MyApplication mMyApplication;
    private String groupCode;
    private Bundle mBundle;

    private Listview_GroupParticipantsAdapter mListview_GroupParticipantsAdapter;

    /**
     * 생성자
     */
    public MyGroup_EditPresenter(MyGroup_EditContract.View mView, Context mContext, FragmentManager mFragmentManager, Bundle mBundle) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        this.mBundle = mBundle;
        mMyApplication = MyApplication.getInstance();
        mListview_GroupParticipantsAdapter = new Listview_GroupParticipantsAdapter(mView, mContext);
    }

    /**
     * 입장경로가 편집일때
     * 해당 그룹에 참여중인 구성원들을 리스트 데이터에 추가
     */
    @Override
    public void receiveGroupMemberWhenEditing() {
        if (mMyApplication.entranceGroupPath) {
            if (mBundle != null) {
                String jsonString = mBundle.getString("jsonString");
                String groupName = mBundle.getString("groupName");
                groupCode = mBundle.getString("groupCode");

                if (jsonString != null && !jsonString.equals("")) {
                    try {
                        JSONArray ja = new JSONArray(jsonString);
                        for (int i = 0; i < ja.length(); i++) {
                            JSONObject order = ja.getJSONObject(i);
                            mListview_GroupParticipantsAdapter.addItem(order.getString("name"), order.getString("phoneNumber"));
                        }
                        if (groupName != null && !groupName.equals("")) {
                            //그룹입력란에 해당그룹이름을 표시.
                            mView.changeGroupName(groupName);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            //입장경로가 신규추가이므로 그룹입력란을 비워줌.
            mView.changeGroupName("");
        }
    }

    /**
     * 프래그먼트 이동시 전달했던 구성원 리스트 되돌려받기
     */
    @Override
    public void returningMembers() {
        if (mBundle != null) {
            ArrayList<DirectInputParticipants> directInputParticipantsArrayList = mBundle.getParcelableArrayList("itemList");

            if (directInputParticipantsArrayList != null && directInputParticipantsArrayList.size() > 0) {
                mListview_GroupParticipantsAdapter.setList(mBundle.getParcelableArrayList("itemList"));
            }
        }
    }

    /**
     * 직접입력한 구성원 리스트 받기.
     */
    @SuppressLint("NewApi")
    @Override
    public void receiveDirectlyEnteredMembers() {
        if (mBundle != null) {
            ArrayList<DirectInputParticipants> directInputParticipantsArrayList = mBundle.getParcelableArrayList("InputMember");
            if (directInputParticipantsArrayList != null && directInputParticipantsArrayList.size() > 0) {
                for (int i = 0; i < directInputParticipantsArrayList.size(); i++) {
                    mListview_GroupParticipantsAdapter.addItem(directInputParticipantsArrayList.get(i).getName(), PhoneNumberUtils.formatNumber(directInputParticipantsArrayList.get(i).getPhoneNumber(), Locale.getDefault().getCountry()));
                }
            }
        }
    }

    /**
     * 전화부호부에서 선택한 구성원 리스트 받기.
     */
    @Override
    public void receiveTelephoneDirectoryMembers() {
        if (mBundle != null) {
            ArrayList<TelephoneDirectory> mTelephoneDirectoryArrayList = mBundle.getParcelableArrayList("telephoneInputMember");
            if (mTelephoneDirectoryArrayList != null && mTelephoneDirectoryArrayList.size() > 0) {
                for (int i = 0; i < mTelephoneDirectoryArrayList.size(); i++) {
                    mListview_GroupParticipantsAdapter.addItem(mTelephoneDirectoryArrayList.get(i).getName(), mTelephoneDirectoryArrayList.get(i).getPhoneNumber());
                }
            }
        }
    }

    /**
     * 구성원 리스트 데이터를 갱신
     */
    @Override
    public void refreshData() {
            mListview_GroupParticipantsAdapter.notifyDataSetInvalidated();
            mView.changePersonCount(mListview_GroupParticipantsAdapter.getCount());
    }

    /**
     * 프래그먼트 진입 시 전달받은 데이터 갱신 데이터를 갱신
     */
    @Override
    public void onResume(Bundle bundle) {
        mBundle = bundle;
    }

    /**
     * ListView 초기화
     */
    @Override
    public void initListViewData(ListView listView) {
        listView.setAdapter(mListview_GroupParticipantsAdapter);
    }

    /**
     * 전화부호부 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickTelephoneDirectory() {

        //현재 프래그먼트로 다시 되돌아 왔을때 리스트가 초기화되는 현상 발생.
        //현재 저장된 구성원 리스트를 전달하여 되돌아 왔을때 해당 리스트를 다시 돌려받음.
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("itemList", mListview_GroupParticipantsAdapter.getList());

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

        MyGroup_TelephoneDirectoryFragment mMyGroup_TelephoneDirectoryFragment = new MyGroup_TelephoneDirectoryFragment();

        mMyGroup_TelephoneDirectoryFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.flFragmentContainer, mMyGroup_TelephoneDirectoryFragment, MyGroup_TelephoneDirectoryFragment.class.getName());
        fragmentTransaction.addToBackStack(MyGroup_TelephoneDirectoryFragment.class.getName());
        fragmentTransaction.commit();

    }

    /**
     * 직접입력 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickDirectInput() {

        //현재 프래그먼트로 다시 되돌아 왔을때 리스트가 초기화되는 현상 발생.
        //현재 저장된 구성원 리스트를 전달하여 되돌아 왔을때 해당 리스트를 다시 돌려받음.
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("itemList", mListview_GroupParticipantsAdapter.getList());

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

        MyGroup_DirectInputFragment mMyGroup_DirectInputFragment = new MyGroup_DirectInputFragment();

        mMyGroup_DirectInputFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.flFragmentContainer, mMyGroup_DirectInputFragment, MyGroup_DirectInputFragment.class.getName());
        fragmentTransaction.addToBackStack(MyGroup_DirectInputFragment.class.getName());
        fragmentTransaction.commit();
    }

    /**
     * 뒤로가기 이벤트 처리
     */
    @Override
    public void clickBackPressed() {
        if (mMyApplication.entranceGroupPath) {
            mView.showWarningDialog("경고", "정말로 편집을 그만하시겠습니까?\n입력된 정보는 저장되지 않습니다.");
        } else {
            mView.showWarningDialog("경고", "정말로 추가를 그만하시겠습니까?\n입력된 정보는 저장되지 않습니다.");
        }
    }

    /**
     * 완료 버튼 이벤트 처리
     */
    @Override
    public void clickComplete(String groupName) {
        if(groupName.equals("")){
            mView.showFailDialog("실패" , "그룹명을 입력해주세요.");
            return;
        }

        //그룹 구성원이 2명 이상이어야 완료가 가능.
        if (mListview_GroupParticipantsAdapter.getCount() > 1) {

            //입장경로가 편집일때 데이터 업데이트.
            if (mMyApplication.entranceGroupPath) {
                Call<Void> updateGroup = MyApplication
                        .getRestAdapter()
                        .updateGroup(groupCode,
                                groupName,
                                new Gson().toJson(mListview_GroupParticipantsAdapter.getList()),
                                mListview_GroupParticipantsAdapter.getCount() + "");

                updateGroup.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        mView.showSuccessDialog("성공", groupName + "그룹이 변경되었습니다.");
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("fail", t.getLocalizedMessage());
                        Log.d("fail", t.getMessage());
                    }
                });

                //입장경로가 신규추가일때 데이터 생성.
            } else {
                Call<Void> createGroup = MyApplication
                        .getRestAdapter()
                        .createGroup(groupName,
                                mMyApplication.getUserInfo().getUserCode(),
                                new Gson().toJson(mListview_GroupParticipantsAdapter.getList()),
                                mListview_GroupParticipantsAdapter.getCount() + "");

                createGroup.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        mView.showSuccessDialog("성공", groupName + "그룹이 추가되었습니다.");
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("fail", t.getLocalizedMessage());
                        Log.d("fail", t.getMessage());
                    }
                });
            }
        } else {
            mView.showFailDialog("실패", "그룹은 최소 2인 이상입니다.");
        }
    }
}
