package com.dutch.hdh.dutchpayapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.AccountBankList;
import com.dutch.hdh.dutchpayapp.databinding.ItemBankSelectBinding;

import java.util.ArrayList;

public class BankSelectListAdapter extends BaseAdapter {
    private CardHolder holder;
    private ArrayList<AccountBankList.AccountBankListResult> mBankListResultArrayList = new ArrayList<>();
    private Context mContext;

    public BankSelectListAdapter(Context context, ArrayList<AccountBankList.AccountBankListResult> cardListResultArrayList) {
        super();
        this.mContext = context;
        this.mBankListResultArrayList = cardListResultArrayList;
    }

    @Override
    public int getCount() {
        return mBankListResultArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mBankListResultArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            ItemBankSelectBinding itemCardSelectBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_bank_select, parent, false);
            holder = new CardHolder(itemCardSelectBinding);
            holder.mView = itemCardSelectBinding.getRoot();
            holder.mView.setTag(holder);
        } else {
            holder = (CardHolder) convertView.getTag();
        }
        AccountBankList.AccountBankListResult mAccountBankListResult = mBankListResultArrayList.get(position);
        holder.mViewListBankSelectBinding.tvBankName.setText(mAccountBankListResult.getAccount_TypeName());

        return holder.mView;
    }

    class CardHolder {
        View mView;
        ItemBankSelectBinding mViewListBankSelectBinding;

        CardHolder(ItemBankSelectBinding itemBankSelectBinding) {
            this.mView = itemBankSelectBinding.getRoot();
            this.mViewListBankSelectBinding = itemBankSelectBinding;
        }
    }
}
