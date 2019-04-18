package com.dutch.hdh.dutchpayapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.CardManagement;
import com.dutch.hdh.dutchpayapp.data.db.CardRegisterList;
import com.dutch.hdh.dutchpayapp.databinding.ItemCardListBinding;
import com.dutch.hdh.dutchpayapp.generated.callback.OnClickListener;
import com.dutch.hdh.dutchpayapp.ui.wallet.managementcard.ManagementCardContract;
import com.dutch.hdh.dutchpayapp.util.FormatUtil;
import com.dutch.hdh.dutchpayapp.util.Trace;

import java.util.ArrayList;

public class CardRegisterListAdapter extends BaseAdapter {
    private ItemCardHolder holder;
    private ArrayList<CardRegisterList.CardRegisterListResult> mCardRegisterListResultArrayList = new ArrayList<>();
    private Context mContext;
    private ManagementCardContract.View mView;
    private ManagementCardContract.Presenter mPresenter;

    public CardRegisterListAdapter(Context context, ArrayList<CardRegisterList.CardRegisterListResult> managementListResultArrayList, ManagementCardContract.View view, ManagementCardContract.Presenter presenter) {
        super();
        this.mContext = context;
        this.mCardRegisterListResultArrayList = managementListResultArrayList;
        this.mView = view;
        this.mPresenter = presenter;
    }

    @Override
    public int getCount() {
        return mCardRegisterListResultArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mCardRegisterListResultArrayList.get(position);
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
        CardRegisterList.CardRegisterListResult mCardRegisterListResult = mCardRegisterListResultArrayList.get(position);
        holder.mItemCardListBinding.tvCardName.setText(mCardRegisterListResult.getCard_TypeName());
        holder.mItemCardListBinding.tvCardNumber.setText(FormatUtil.getHyphenCardMasking(mCardRegisterListResult.getCard_No()));

        holder.mItemCardListBinding.ivCardDelete.setOnClickListener(v -> {
            if (mPresenter.isRepresentativeCard(mCardRegisterListResult.getCard_Choice())) {
                mView.showCommonDialog("알림", "대표카드는 삭제 할수 없습니다.", false);
            } else {
                mView.showDeleteCardDialog(mCardRegisterListResult.getCard_Code());
            }
        });

        holder.mItemCardListBinding.llCardBackground.setBackgroundColor(Color.parseColor(getCardCompanyColor(Integer.parseInt(mCardRegisterListResult.getCard_TypeCode()))));

        return holder.mView;
    }

    /**
     * 카드사에 따른 백그라운드 컬러
     */
    private String getCardCompanyColor(int cardCode) {
        switch (cardCode) {
            case 0:
                return "#288479";
            case 1:
                return "#f7b500";
            case 2:
                return "#0f479e";
            case 3:
                return "#ea1d24";
            case 4:
                return "#212021";
            case 5:
                return "#0165b3";
            case 6:
                return "#ea1d24";
            case 7:
                return "#1899d3";
            case 8:
                return "#f7b500";
            case 9:
                return "#288479";
            case 10:
                return "#212021";

        }
        return "#ffffff";
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
