package com.dutch.hdh.dutchpayapp.ui.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.CardSelectListAdapter;
import com.dutch.hdh.dutchpayapp.data.db.CardList;
import com.dutch.hdh.dutchpayapp.databinding.ViewCardSelectBinding;

import java.util.ArrayList;

public class CardSelectView extends LinearLayout {

    private Context mContext;
    private OnReceiveMessageListener mListener;
    private ViewCardSelectBinding mBinding;
    private String mCardName;
    private int mCardCode;
    private ArrayList<CardList.CardListResult> mCardListResultArrayList;
    private CardSelectListAdapter mCardListAdapter;


    public CardSelectView(Context context, ArrayList<CardList.CardListResult> cardListResults, OnReceiveMessageListener listener) {
        super(context);

        this.mContext = context;
        this.mListener = listener;
        this.mCardListResultArrayList = cardListResults;
        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.view_card_select, this, true);
        init();
    }

    private void init() {

        mCardListAdapter = new CardSelectListAdapter(mContext, mCardListResultArrayList);
        mBinding.lvCardSelect.setAdapter(mCardListAdapter);
        mBinding.lvCardSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCardName = mCardListResultArrayList.get(position).getCardName();
                mCardCode = mCardListResultArrayList.get(position).getCarCode();
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        mListener.onReceive(mCardName, mCardCode);
    }

    public interface OnReceiveMessageListener {
        void onReceive(String carName, int cardCode);
    }
}
