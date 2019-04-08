package com.dutch.hdh.dutchpayapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.CardList;
import com.dutch.hdh.dutchpayapp.databinding.ViewListCardselectBinding;

import java.util.ArrayList;

public class CardSelectListAdapter extends BaseAdapter {
    private CardHolder holder;
    private ArrayList<CardList.CardListResult> mCardListResultArrayList = new ArrayList<>();
    private Context mContext;

    public CardSelectListAdapter(Context context, ArrayList<CardList.CardListResult> cardListResultArrayList) {
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
            ViewListCardselectBinding mViewListCardSelectBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.view_list_cardselect, parent, false);
            holder = new CardHolder(mViewListCardSelectBinding);
            holder.mView = mViewListCardSelectBinding.getRoot();
            holder.mView.setTag(holder);
        } else {
            holder = (CardHolder) convertView.getTag();
        }
        CardList.CardListResult mCardListResult = mCardListResultArrayList.get(position);
        holder.mViewListCardSelectBinding.tvCardName.setText(mCardListResult.getCardName());

        return holder.mView;
    }

    class CardHolder {
        View mView;
        ViewListCardselectBinding mViewListCardSelectBinding;

        CardHolder(ViewListCardselectBinding viewListCardselectBinding) {
            this.mView = viewListCardselectBinding.getRoot();
            this.mViewListCardSelectBinding = viewListCardselectBinding;
        }
    }
}
