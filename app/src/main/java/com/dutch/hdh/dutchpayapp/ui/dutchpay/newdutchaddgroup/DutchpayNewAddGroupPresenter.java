package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchaddgroup;

import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import android.util.Log;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.adapter.DutchpayNewGroupListAdapter;
import com.dutch.hdh.dutchpayapp.data.db.GroupList;
import com.dutch.hdh.dutchpayapp.data.db.MyGroup;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DutchpayNewAddGroupPresenter implements DutchpayNewAddGroupContract.Presenter {

    DutchpayNewAddGroupContract.View mView;

    private MyApplication mMyApplication;

    private ObservableArrayList<DutchpayNewAddGroupModel> mGList;
    private DutchpayNewGroupListAdapter mAdapter;


    public DutchpayNewAddGroupPresenter(DutchpayNewAddGroupContract.View mView) {
        this.mView = mView;
        this.mGList = new ObservableArrayList<>();
        this.mAdapter = new DutchpayNewGroupListAdapter(mGList,this);
        this.mMyApplication = MyApplication.getInstance();
    }

    public ObservableArrayList<DutchpayNewAddGroupModel> getGList() {
        return mGList;
    }

    public DutchpayNewGroupListAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void listInit() {
        //그룹불러오기
        Call<MyGroup> getGroupList2 = MyApplication
                .getRestAdapter()
                .getGroupList(mMyApplication.getUserInfo().getUserCode());

        getGroupList2.enqueue(new Callback<MyGroup>() {
            @Override
            public void onResponse(Call<MyGroup> call, Response<MyGroup> response) {
                if(response.isSuccessful()) {
                    MyGroup myGroup = response.body();

                    if (myGroup != null) {
                        ArrayList<GroupList> list =  myGroup.getGroupLists();

                        for(int i=0; i<list.size(); i++){
                            GroupList item = list.get(i);
                            int icon = i%6;
                            mGList.add(new DutchpayNewAddGroupModel(item.getGroupName(),icon,item.getParticipantsCount()-1));
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<MyGroup> call, @NonNull Throwable t) {
                Log.e("fail",t.getMessage());
            }
        });

        mAdapter.notifyDataSetChanged();

        //binding
        mView.adapterInit();
    }
}
