package com.dutch.hdh.dutchpayapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.CardCompanyList;
import com.dutch.hdh.dutchpayapp.databinding.ItemCardSelectBinding;

import java.util.ArrayList;

public class CardSelectListAdapter extends BaseAdapter {
    private CardHolder holder;
    private ArrayList<CardCompanyList.CardCompanyListResult> mCardListResultArrayList = new ArrayList<>();
    private Context mContext;

    public CardSelectListAdapter(Context context, ArrayList<CardCompanyList.CardCompanyListResult> cardListResultArrayList) {
        super();
        this.mContext = context;
        this.mCardListResultArrayList = cardListResultArrayList;
    }

    @Override
    public int getCount() {
        return mCardListResultArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCardListResultArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            ItemCardSelectBinding itemCardSelectBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_card_select, parent, false);
            holder = new CardHolder(itemCardSelectBinding);
            holder.mView = itemCardSelectBinding.getRoot();
            holder.mView.setTag(holder);
        } else {
            holder = (CardHolder) convertView.getTag();
        }
        CardCompanyList.CardCompanyListResult mCardListResult = mCardListResultArrayList.get(position);
        holder.mViewListCardSelectBinding.tvCardName.setText(mCardListResult.getCard_TypeName());

        return holder.mView;
    }

    class CardHolder {
        View mView;
        ItemCardSelectBinding mViewListCardSelectBinding;

        CardHolder(ItemCardSelectBinding itemCardSelectBinding) {
            this.mView = itemCardSelectBinding.getRoot();
            this.mViewListCardSelectBinding = itemCardSelectBinding;
        }
    }
}
