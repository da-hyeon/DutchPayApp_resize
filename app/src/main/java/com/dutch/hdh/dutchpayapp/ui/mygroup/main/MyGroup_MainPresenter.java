package com.dutch.hdh.dutchpayapp.ui.mygroup.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.ListView;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.Listview_MyGroupAdapter;
import com.dutch.hdh.dutchpayapp.data.db.MyGroup;
import com.dutch.hdh.dutchpayapp.ui.mygroup.edit.MyGroup_EditFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyGroup_MainPresenter implements MyGroup_MainContract.Presenter{

    private MyGroup_MainContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;

    private Listview_MyGroupAdapter mListview_MyGroupAdapter;

    private MyApplication mMyApplication;

    /**
     * 생성자
     */
    MyGroup_MainPresenter(MyGroup_MainContract.View mView, Context mContext , FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
        mMyApplication = MyApplication.getInstance();
    }

    /**
     * ListView 초기화 및 데이터 삽입
     */
    @Override
    public void initListViewData(ListView listView) {

        //그룹불러오기
        Call<MyGroup> getGroupList = MyApplication
                .getRestAdapter()
                .getGroupList(mMyApplication.getUserInfo().getUserCode());

        getGroupList.enqueue(new Callback<MyGroup>() {
            @Override
            public void onResponse(@NonNull Call<MyGroup> call, @NonNull Response<MyGroup> response) {
                if(response.isSuccessful()) {

                    //받아온 데이터를 모델에 삽입
                    MyGroup myGroup = response.body();
                    if (myGroup != null) {
                        mListview_MyGroupAdapter = new Listview_MyGroupAdapter(mView, mContext, myGroup.getGroupLists() , mFragmentManager);
                    }
                    listView.setAdapter(mListview_MyGroupAdapter);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MyGroup> call, @NonNull Throwable t) {
                Log.d("fail" , t.getLocalizedMessage());
                Log.d("fail" , t.getMessage());
            }
        });
    }

    /**
     * 그룹 추가하기 버튼 이벤트 처리
     */
    @Override
    public void clickAddGroup() {
        //입장경로 = 신규추가
        mMyApplication.entranceGroupPath = false;

        //프래그먼트 이동
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

        mMyApplication.getMyGroup_EditFragment().setArguments(null);
        fragmentTransaction.replace(R.id.flFragmentContainer, mMyApplication.getMyGroup_EditFragment(), MyGroup_EditFragment.class.getName());
        fragmentTransaction.addToBackStack(MyGroup_EditFragment.class.getName());
        fragmentTransaction.commit();
    }
}
