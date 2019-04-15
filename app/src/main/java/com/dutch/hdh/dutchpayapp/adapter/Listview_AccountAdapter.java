package com.dutch.hdh.dutchpayapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.Account;
import com.dutch.hdh.dutchpayapp.data.db.GroupList;
import com.dutch.hdh.dutchpayapp.databinding.ItemMyAccountBinding;
import com.dutch.hdh.dutchpayapp.databinding.ItemMyGroupBinding;
import com.dutch.hdh.dutchpayapp.ui.mygroup.edit.MyGroup_EditFragment;
import com.dutch.hdh.dutchpayapp.ui.mygroup.main.MyGroup_MainContract;
import com.kinda.alert.KAlertDialog;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Listview_AccountAdapter extends BaseAdapter {


    private ItemMyAccountBinding mBinding;
    private Context mContext;

    private ArrayList<Account> mAccountArrayList;
    private MyApplication mMyApplication;

    public Listview_AccountAdapter( Context mContext, ArrayList<Account> mAccountArrayList) {
        this.mContext = mContext;
        this.mAccountArrayList = mAccountArrayList;
        mMyApplication = MyApplication.getInstance();
    }

    @Override
    public int getCount() {
        return mAccountArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAccountArrayList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_my_account, parent, false);
            v = mBinding.getRoot();
        } else {
            mBinding = (ItemMyAccountBinding) v.getTag();
        }

        //대표계좌가 아닌것만 리스트에 보이게 하기.
        if(!mAccountArrayList.get(position).getAccountChoice().equals("0")) {
            mBinding.clView.setVisibility(View.VISIBLE);
            mBinding.clView.setBackgroundResource(Constants.backColorID(Integer.parseInt(mAccountArrayList.get(position).getAccountTypeCode())));
            mBinding.tvBank.setText(mAccountArrayList.get(position).getAccountTypeName());
            mBinding.tvAccountNumber.setText(mAccountArrayList.get(position).getAccountNumber());
        } else {
            mBinding.clView.setVisibility(View.GONE);
        }

        v.setTag(mBinding);
        return v;
    }

}
