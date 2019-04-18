package com.dutch.hdh.dutchpayapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.AccountRegisterList;
import com.dutch.hdh.dutchpayapp.data.db.PayHistoryList;
import com.dutch.hdh.dutchpayapp.databinding.ItemBankListBinding;
import com.dutch.hdh.dutchpayapp.databinding.ItemUsageHistoryBinding;
import com.dutch.hdh.dutchpayapp.ui.wallet.payhistory.PayUsageHistoryContract;
import com.dutch.hdh.dutchpayapp.util.FormatUtil;

import java.util.ArrayList;

public class PayHistoryListAdapter extends BaseAdapter {
    private ItemPayUsageHolder holder;
    private ArrayList<PayHistoryList.PayHistoryListResult> payHistoryListResultArrayList = new ArrayList<>();
    private Context mContext;
    private PayUsageHistoryContract.View mView;
    private PayUsageHistoryContract.Presenter mPresenter;

    public PayHistoryListAdapter(Context context, ArrayList<PayHistoryList.PayHistoryListResult> payHistoryListResultArrayList, PayUsageHistoryContract.View view, PayUsageHistoryContract.Presenter presenter) {
        super();
        this.mContext = context;
        this.payHistoryListResultArrayList = payHistoryListResultArrayList;
        this.mView = view;
        this.mPresenter = presenter;
    }

    @Override
    public int getCount() {
        return payHistoryListResultArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return payHistoryListResultArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            ItemUsageHistoryBinding itemUsageHistoryBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_usage_history, parent, false);
            holder = new ItemPayUsageHolder(itemUsageHistoryBinding);
            holder.mView = itemUsageHistoryBinding.getRoot();
            holder.mView.setTag(holder);
        } else {
            holder = (ItemPayUsageHolder) convertView.getTag();
        }
        PayHistoryList.PayHistoryListResult mPayHistoryListResult = payHistoryListResultArrayList.get(position);
        holder.mItemBankListBinding.tvUsageDate.setText(mPayHistoryListResult.getPayment_date());
        holder.mItemBankListBinding.tvUsageName.setText(mPayHistoryListResult.getPayment_date());
        holder.mItemBankListBinding.tvUsageDate.setText(mPayHistoryListResult.getPayment_date());

//        holder.mItemBankListBinding.tvBankName.setText(mPayHistoryListResult.getAccount_TypeName());
//        holder.mItemBankListBinding.tvAccountNumber.setText(FormatUtil.getHyphenCardMasking(mPayHistoryListResult.getAccount_No()));


        return holder.mView;
    }

    /**
     * 결제 타입에대한 이미지
     */
    private int getPayStatusImage(int accountTypeCode) {
        switch (accountTypeCode) {
            case 0:
                return R.drawable.wallet16_5;
            case 1:
                return R.drawable.wallet16_6;
            case 2:
                return R.drawable.wallet16_7;
            case 3:
                return R.drawable.wallet16_8;
            case 4:
                return R.drawable.wallet16_9;
        }
        return R.drawable.wallet16_5;
    }

    class ItemPayUsageHolder {
        View mView;
        ItemUsageHistoryBinding mItemBankListBinding;

        ItemPayUsageHolder(ItemUsageHistoryBinding itemUsageHistoryBinding) {
            this.mView = itemUsageHistoryBinding.getRoot();
            this.mItemBankListBinding = itemUsageHistoryBinding;
        }
    }
}
