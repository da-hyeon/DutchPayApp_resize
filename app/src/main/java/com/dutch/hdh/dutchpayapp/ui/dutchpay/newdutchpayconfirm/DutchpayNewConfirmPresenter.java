package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpayconfirm;

import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.DutchpayConfirmListAdapter;
import com.dutch.hdh.dutchpayapp.data.db.UserList;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpay.TempNewListModel;
import com.dutch.hdh.dutchpayapp.ui.mygroup.directinput.MyGroup_DirectInputFragment;
import com.dutch.hdh.dutchpayapp.ui.payment_password.PaymentPasswordFragment;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DutchpayNewConfirmPresenter implements DutchpayNewConfirmContract.Presenter{

    DutchpayNewConfirmContract.View mView;
    ObservableArrayList<TempConfirmListModel> mConfirmList;
    DutchpayConfirmListAdapter mAdapter;
    Bundle mData;

    MyApplication mMyApplication;
    ArrayList<UserList> user_list;

    private String json;

    public DutchpayNewConfirmPresenter(DutchpayNewConfirmContract.View mView,Bundle data) {
        this.mView = mView;
        this.mConfirmList = new ObservableArrayList<>();
        this.mAdapter = new DutchpayConfirmListAdapter(mConfirmList,this);
        this.mData = data;
        this.mMyApplication = MyApplication.getInstance();
        this.user_list = new ArrayList<>();
    }

    @Override
    public void listInit() {
        Gson gson = new Gson();
        Log.e("check-->", mData.getString("JList"));

        List<TempConfirmListModel> list = gson.fromJson(mData.getString("JList"),new TypeToken<List<TempConfirmListModel>>(){}.getType());
        for(TempConfirmListModel model : list){
            //페이지 리스트 생성
            mConfirmList.add(model);

            //flag 보완
           String tmp = "";
           tmp = model.completeFlag ? "Y" : "N";

           //서버 전송용 리스트 생성
           user_list.add(new UserList(model.getName(),model.getPhone(),model.getCost(),tmp));
        }

        json = gson.toJson(user_list);
        Log.e("make list ->",json);

        //binding
        mView.adapterInit();
    }

    @Override
    public void onPayClick() {
        Bundle bundle = new Bundle();

        bundle.putStringArrayList(Constants.PAYMENT_INFO,mData.getStringArrayList("Info"));
        bundle.putString(Constants.PAYMENT_LIST_JSON,json);

        //결제 비밀번호 페이지로 이동
        moveToPaymentPassword(bundle);
    }

    public ObservableArrayList<TempConfirmListModel> getmConfirmList() {
        return mConfirmList;
    }

    public DutchpayConfirmListAdapter getmAdapter() {
        return mAdapter;
    }

    private void moveToPaymentPassword(Bundle bundle){
        FragmentManager fm = mView.getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

        PaymentPasswordFragment paymentPasswordFragment = new PaymentPasswordFragment();
        paymentPasswordFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.flFragmentContainer,paymentPasswordFragment, PaymentPasswordFragment.class.getName());
        fragmentTransaction.addToBackStack(PaymentPasswordFragment.class.getName());
        fragmentTransaction.commit();
    }
}
