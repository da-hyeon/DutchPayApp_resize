package com.dutch.hdh.dutchpayapp.ui.dutchpay.startdetail;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.DutchpayDetailListAdapter;
import com.dutch.hdh.dutchpayapp.data.db.DutchDetailMember;
import com.dutch.hdh.dutchpayapp.data.db.DutchDetailRoom;
import com.dutch.hdh.dutchpayapp.data.db.DutchpayDetail;
import com.dutch.hdh.dutchpayapp.data.db.Dutchpayhistory;
import com.dutch.hdh.dutchpayapp.data.db.DutchpaytotalList;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.photo.DutchpayPhotoFragment;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.start.TempStartListModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DutchpayDetailPresenter implements DutchpayDetailContract.Presenter {

    private DutchpayDetailContract.View mView;
    private ObservableArrayList<TempDetailListModel> mDetailList;
    private DutchpayDetailListAdapter mAdapter;

    private MyApplication mMyApplication;

    public DutchpayDetailPresenter(DutchpayDetailContract.View mView) {
        this.mView = mView;
        this.mDetailList = new ObservableArrayList<>();
        this.mAdapter = new DutchpayDetailListAdapter(mDetailList,this);
        this.mMyApplication = MyApplication.getInstance();
    }

    @Override
    public void listInit(Bundle bundle) {
        int dutchpayId = bundle.getInt("dutchpayID");

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        //리스트 불러오기
        Call<DutchpayDetail> getDutchpayDetail = MyApplication
                .getRestAdapter()
                .getDutchpayDetail(mMyApplication.getUserInfo().getUserCode(),
                        dutchpayId);

        getDutchpayDetail.enqueue(new Callback<DutchpayDetail>() {
            @Override
            public void onResponse(Call<DutchpayDetail> call, Response<DutchpayDetail> response) {
                if(response.body() != null){
                    ArrayList<DutchDetailRoom> roomInfo = response.body().getRoominfo();
                    for(int i=0; i<roomInfo.size(); i++){

                    }

                    String a = gson.toJson(roomInfo);
                    Log.e("Room? ->",a);

                    ArrayList<DutchDetailMember> memberInfo = response.body().getMemberinfo();

                    String b = gson.toJson(memberInfo);
                    Log.e("Member? ->",b);




//                    for(int i=0; i<list.size(); i++){
//                        DutchpaytotalList item = list.get(i);
//                        int status = dutchpayStatusCheck(item);
//
//                        mStartList.add(new TempStartListModel(item.getShop(), String.valueOf(item.getCost()), item.getDate(),status,item.getDutchpayid()));
//                    }
                }
            }

            @Override
            public void onFailure(Call<DutchpayDetail> call, Throwable t) {
                Log.e("fail",t.getMessage());
            }
        });


        //더미 데이터 셋
        mDetailList.add(new TempDetailListModel("",false,"",1));
        mDetailList.add(new TempDetailListModel("",true,"",2));
        mDetailList.add(new TempDetailListModel("",false,"",3));
        mDetailList.add(new TempDetailListModel("",false,"",0));

        mAdapter.notifyDataSetChanged();

        //binding
        mView.adapterInit();
    }

    public ObservableArrayList<TempDetailListModel> getmDetailList() {
        return mDetailList;
    }

    public DutchpayDetailListAdapter getmAdapter() {
        return mAdapter;
    }

    @BindingAdapter("item")
    public static void bindItem(RecyclerView view, ObservableArrayList<TempDetailListModel> list){
        DutchpayDetailListAdapter adapter = (DutchpayDetailListAdapter) view.getAdapter();
        if(adapter != null) {
            adapter.setItem(list);
        }
    }

    public void onPhotoClick(){
        FragmentManager fm = mView.getFragmentManager();

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

        DutchpayPhotoFragment dutchpayPhotoFragment = new DutchpayPhotoFragment();
        fragmentTransaction.replace(R.id.flFragmentContainer,dutchpayPhotoFragment , DutchpayPhotoFragment.class.getName());
        fragmentTransaction.addToBackStack(DutchpayPhotoFragment.class.getName());
        fragmentTransaction.commit();
    }
}
