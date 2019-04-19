package com.dutch.hdh.dutchpayapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.PayHistoryList;
import com.dutch.hdh.dutchpayapp.databinding.ItemUsageHistoryBinding;
import com.dutch.hdh.dutchpayapp.ui.wallet.payhistory.PayUsageHistoryContract;

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
        holder.itemUsageHistoryBinding.tvUsageDate.setText(mPayHistoryListResult.getProgress_Date());
        if ("단독".equals(mPayHistoryListResult.getPay_Types())) {
            holder.itemUsageHistoryBinding.tvUsageName.setText(mPayHistoryListResult.getSingleTitle());
        } else {
            holder.itemUsageHistoryBinding.tvUsageName.setText(mPayHistoryListResult.getDutchPay_Title());
        }
        holder.itemUsageHistoryBinding.tvUsagePrice.setText(String.format("%,d", Integer.parseInt(mPayHistoryListResult.getPayMent_Price()))+"원");
        holder.itemUsageHistoryBinding.ivUsagePayStatus.setImageResource(getPayStatusImage(mPayHistoryListResult.getPay_Types()));

        return holder.mView;
    }

    /**
     * 결제 타입에대한 이미지
     */
    private int getPayStatusImage(String payType) {
        switch (payType) {
            case "단독":
                return R.drawable.wallet16_5;
            case "더치페이":
                return R.drawable.wallet16_6;
        }
        return R.drawable.wallet16_5;
    }

    class ItemPayUsageHolder {
        View mView;
        ItemUsageHistoryBinding itemUsageHistoryBinding;

        ItemPayUsageHolder(ItemUsageHistoryBinding itemUsageHistoryBinding) {
            this.mView = itemUsageHistoryBinding.getRoot();
            this.itemUsageHistoryBinding = itemUsageHistoryBinding;
        }
    }
}
