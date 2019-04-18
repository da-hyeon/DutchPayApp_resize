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
import com.dutch.hdh.dutchpayapp.data.db.CardRegisterList;
import com.dutch.hdh.dutchpayapp.databinding.ItemBankListBinding;
import com.dutch.hdh.dutchpayapp.ui.wallet.myaccount.MyAccountContract;
import com.dutch.hdh.dutchpayapp.util.FormatUtil;

import java.util.ArrayList;

public class AccountRegisterListAdapter extends BaseAdapter {
    private ItemBankHolder holder;
    private ArrayList<AccountRegisterList.AccountRegisterListResult> mAccountRegisterListResultArrayList = new ArrayList<>();
    private Context mContext;
    private MyAccountContract.View mView;
    private MyAccountContract.Presenter mPresenter;

    public AccountRegisterListAdapter(Context context, ArrayList<AccountRegisterList.AccountRegisterListResult> accountRegisterListResultArrayList, MyAccountContract.View view, MyAccountContract.Presenter presenter) {
        super();
        this.mContext = context;
        this.mAccountRegisterListResultArrayList = accountRegisterListResultArrayList;
        this.mView = view;
        this.mPresenter = presenter;
    }

    @Override
    public int getCount() {
        return mAccountRegisterListResultArrayList.size();
    }

    @Override
    public AccountRegisterList.AccountRegisterListResult getItem(int position) {
        return mAccountRegisterListResultArrayList.get(position);
    }

    /**
     * 대표계좌 정보 가져오기
     */

    public AccountRegisterList.AccountRegisterListResult getRepresentativeAccount() {
        if (mAccountRegisterListResultArrayList.size() != 0) {
            for (int i = 0; i < mAccountRegisterListResultArrayList.size(); i++) {
                if ("0".equals(mAccountRegisterListResultArrayList.get(i).getAccount_Choice())) {
                    return mAccountRegisterListResultArrayList.get(i);
                }
            }
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            ItemBankListBinding itemCardListBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_bank_list, parent, false);
            holder = new ItemBankHolder(itemCardListBinding);
            holder.mView = itemCardListBinding.getRoot();
            holder.mView.setTag(holder);
        } else {
            holder = (ItemBankHolder) convertView.getTag();
        }

        AccountRegisterList.AccountRegisterListResult mAccountRegisterListResult = mAccountRegisterListResultArrayList.get(position);
        holder.mItemBankListBinding.tvBankName.setText(mAccountRegisterListResult.getAccount_TypeName());
        holder.mItemBankListBinding.tvAccountNumber.setText(FormatUtil.getHyphenCardMasking(mAccountRegisterListResult.getAccount_No()));

        holder.mItemBankListBinding.llBankDelete.setOnClickListener(v -> {
            if (mPresenter.isRepresentativeAccountNumber(mAccountRegisterListResult.getAccount_Choice())) {
                mView.showCommonDialog("알림", "대표계좌는 삭제 할수 없습니다.", false);
            } else {
                mView.showDeleteAccountDialog(mAccountRegisterListResult.getAccount_Code());
            }
        });

        holder.mItemBankListBinding.llBankBackground.setBackgroundColor(Color.parseColor(getBankCompanyColor(Integer.parseInt(mAccountRegisterListResult.getAccount_TypeCode()))));


        return holder.mView;
    }

    /**
     * 카드사에 따른 백그라운드 컬러
     */
    private String getBankCompanyColor(int accountTypeCode) {
        switch (accountTypeCode) {
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

    class ItemBankHolder {
        View mView;
        ItemBankListBinding mItemBankListBinding;

        ItemBankHolder(ItemBankListBinding itemBankListBinding) {
            this.mView = itemBankListBinding.getRoot();
            this.mItemBankListBinding = itemBankListBinding;
        }
    }
}
