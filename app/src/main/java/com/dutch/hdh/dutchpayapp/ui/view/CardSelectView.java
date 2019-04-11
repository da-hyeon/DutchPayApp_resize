package com.dutch.hdh.dutchpayapp.ui.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.CardSelectListAdapter;
import com.dutch.hdh.dutchpayapp.data.db.CardCompanyList;
import com.dutch.hdh.dutchpayapp.databinding.DialogCardSelectBinding;

import java.util.ArrayList;

public class CardSelectView extends LinearLayout {

    private Context mContext;
    private OnReceiveMessageListener mListener;
    private DialogCardSelectBinding mBinding;
    private String mCardName;
    private String mCardCode;
    private ArrayList<CardCompanyList.CardCompanyListResult> mCardListResultArrayList;
    private CardSelectListAdapter mCardListAdapter;


    public CardSelectView(Context context, ArrayList<CardCompanyList.CardCompanyListResult> cardListResults, OnReceiveMessageListener listener) {
        super(context);

        this.mContext = context;
        this.mListener = listener;
        this.mCardListResultArrayList = cardListResults;
        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_card_select, this, true);
        init();
    }

    private void init() {
//        Log.e("테스트", mCardListResultArrayList.toString());
        mCardListAdapter = new CardSelectListAdapter(mContext, mCardListResultArrayList);
        mBinding.lvCardSelect.setAdapter(mCardListAdapter);
        mBinding.lvCardSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCardName = mCardListResultArrayList.get(position).getCard_TypeName();
                mCardCode = mCardListResultArrayList.get(position).getCard_TypeCode();
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        mListener.onReceive(mCardName, mCardCode);
    }

    public interface OnReceiveMessageListener {
        void onReceive(String carName, String cardCode);
    }
}
