package com.dutch.hdh.dutchpayapp.ui.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.BankSelectListAdapter;
import com.dutch.hdh.dutchpayapp.data.db.AccountBankList;
import com.dutch.hdh.dutchpayapp.databinding.DialogBankSelectBinding;

import java.util.ArrayList;

public class BankSelectView extends LinearLayout {

    private Context mContext;
    private OnReceiveMessageListener mListener;
    private DialogBankSelectBinding mBinding;
    private String mBankName;
    private String mBankCode;
    private ArrayList<AccountBankList.AccountBankListResult> mAccountListResultArrayList;
    private BankSelectListAdapter mBankListAdapter;


    public BankSelectView(Context context, ArrayList<AccountBankList.AccountBankListResult> accountBankListResultArrayList, OnReceiveMessageListener listener) {
        super(context);

        this.mContext = context;
        this.mListener = listener;
        this.mAccountListResultArrayList = accountBankListResultArrayList;
        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_bank_select, this, true);
        init();
    }

    private void init() {
        mBankListAdapter = new BankSelectListAdapter(mContext, mAccountListResultArrayList);
        mBinding.lvBankSelect.setAdapter(mBankListAdapter);
        mBinding.lvBankSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mBankName = mAccountListResultArrayList.get(position).getAccount_TypeName();
                mBankCode = mAccountListResultArrayList.get(position).getAccount_TypeCode();
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        mListener.onReceive(mBankName, mBankCode);
    }

    public interface OnReceiveMessageListener {
        void onReceive(String bankName, String bankCode);
    }
}
