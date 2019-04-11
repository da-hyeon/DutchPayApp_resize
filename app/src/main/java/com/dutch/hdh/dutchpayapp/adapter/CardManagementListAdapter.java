package com.dutch.hdh.dutchpayapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.CardManagement;
import com.dutch.hdh.dutchpayapp.databinding.ItemCardListBinding;

import java.util.ArrayList;

public class CardManagementListAdapter extends BaseAdapter {
    private ItemCardHolder holder;
    private ArrayList<CardManagement.CardManagementListResult> mCardManagementListResultArrayList = new ArrayList<>();
    private Context mContext;

    public CardManagementListAdapter(Context context, ArrayList<CardManagement.CardManagementListResult> managementListResultArrayList) {
        super();
        this.mContext = context;
        this.mCardManagementListResultArrayList = managementListResultArrayList;
    }

    @Override
    public int getCount() {
        return mCardManagementListResultArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCardManagementListResultArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            ItemCardListBinding itemCardListBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_card_list, parent, false);
            holder = new ItemCardHolder(itemCardListBinding);
            holder.mView = itemCardListBinding.getRoot();
            holder.mView.setTag(holder);
        } else {
            holder = (ItemCardHolder) convertView.getTag();
        }
        CardManagement.CardManagementListResult mCardManagementListResult = mCardManagementListResultArrayList.get(position);
        holder.mItemCardListBinding.tvCardName.setText(mCardManagementListResult.getCardName());
        holder.mItemCardListBinding.tvAccountNumber.setText(mCardManagementListResult.getCardNumber());
        holder.mItemCardListBinding.llCardBackground.setBackgroundColor(getCardCompanyColor(mCardManagementListResult.getCardCode()));

        return holder.mView;
    }

    /**
     * 카드사에 따른 백그라운드 컬러
     */
    private int getCardCompanyColor(int cardCode) {
        switch (cardCode) {
            case 0:
                return R.color.hanaCardColor;
            case 1:
                return R.color.kbCardColor;
            case 2:
                return R.color.shinhanCardColor;
            case 3:
                return R.color.bcCardColor;
            case 4:
                return R.color.hyundaiCardColor;
            case 5:
                return R.color.samsungCardColor;
        }
        return 0;
    }

    class ItemCardHolder {
        View mView;
        ItemCardListBinding mItemCardListBinding;

        ItemCardHolder(ItemCardListBinding itemCardListBinding) {
            this.mView = itemCardListBinding.getRoot();
            this.mItemCardListBinding = itemCardListBinding;
        }
    }
}
