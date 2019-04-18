package com.dutch.hdh.dutchpayapp.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.dutch.hdh.dutchpayapp.ui.mypage.refund.MyPage_SelectRefundAccountContract;
import com.dutch.hdh.dutchpayapp.ui.mypage.withdrawal.MyPage_WithdrawalActivity;
import com.kinda.alert.KAlertDialog;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Listview_AccountAdapter extends BaseAdapter {


    private ItemMyAccountBinding mBinding;
    private MyPage_SelectRefundAccountContract.View mView;
    private Context mContext;
    private ArrayList<Account> mAccountArrayList;
    private MyApplication mMyApplication;

    public Listview_AccountAdapter(MyPage_SelectRefundAccountContract.View mView, Context mContext, ArrayList<Account> mAccountArrayList) {
        this.mView = mView;
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

        //배경색 변경
        mBinding.clView.setBackgroundResource(Constants.backColorID(Integer.parseInt(mAccountArrayList.get(position).getAccountTypeCode())));
        //은행명 변경
        mBinding.tvBank.setText(mAccountArrayList.get(position).getAccountTypeName());
        //계좌번호 번경
        mBinding.tvAccountNumber.setText(mAccountArrayList.get(position).getAccountNumber());

        mBinding.clView.setOnClickListener(v1 ->
                new KAlertDialog(Objects.requireNonNull(mContext), KAlertDialog.WARNING_TYPE)
                        .setTitleText("주의")
                        .setContentText(mAccountArrayList.get(position).getAccountTypeName() + " " + mAccountArrayList.get(position).getAccountNumber() + "\n환불계좌를 위의 계좌로 변경하시겠습니까?")
                        .setConfirmText("확인")
                        .setConfirmClickListener(sDialog -> {
                            sDialog.dismissWithAnimation();
                            Intent intent = new Intent(mContext, MyPage_WithdrawalActivity.class);
                            intent.putExtra(Constants.ACCOUNT_TYPE_CODE, Integer.parseInt(mAccountArrayList.get(position).getAccountTypeCode()));
                            intent.putExtra(Constants.ACCOUNT_NUMBER, mAccountArrayList.get(position).getAccountNumber());
                            mContext.startActivity(intent);
                            mView.finish();
                        })
                        .setCancelText("취소")
                        .setCancelClickListener(KAlertDialog::dismissWithAnimation)
                        .show()
        );

        v.setTag(mBinding);
        return v;
    }

}
