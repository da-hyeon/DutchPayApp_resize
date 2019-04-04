package com.dutch.hdh.dutchpayapp.adapter;

import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.databinding.ItemDutchpayListDetailBinding;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.startdetail.DutchpayDetailContract;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.startdetail.TempDetailListModel;

public class DutchpayDetailListAdapter extends RecyclerView.Adapter<DutchpayDetailListAdapter.dDetailViewHolder> {

    private ObservableArrayList<TempDetailListModel> mList;
    private DutchpayDetailContract.Presenter mDDetailPresenter;

    public class dDetailViewHolder extends RecyclerView.ViewHolder{

        ItemDutchpayListDetailBinding mBinding;

        public dDetailViewHolder(ItemDutchpayListDetailBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        void bind(TempDetailListModel item) {
            mBinding.setItem(item);

            if(item.isImageFlag()) {
                mBinding.ivUserIcon.setVisibility(View.VISIBLE);
                mBinding.tvUserName.setVisibility(View.INVISIBLE);
            } else {
                mBinding.tvUserName.setVisibility(View.VISIBLE);
                mBinding.ivUserIcon.setVisibility(View.INVISIBLE);
            }

            //mBinding.getRoot().setOnClickListener(v -> mDSatrtPresenter.onItemClick(item));
        }
    }

    public DutchpayDetailListAdapter(ObservableArrayList<TempDetailListModel> mList, DutchpayDetailContract.Presenter mDDetailPresenter) {
        this.mList = mList;
        this.mDDetailPresenter = mDDetailPresenter;
    }

    public void setItem(ObservableArrayList<TempDetailListModel> list){
        if(list == null ){return;}
        this.mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public dDetailViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemDutchpayListDetailBinding binding = ItemDutchpayListDetailBinding.inflate(LayoutInflater.from(viewGroup.getContext()),viewGroup,false);

        return new dDetailViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull dDetailViewHolder dDetailViewHolder, int i) {
        TempDetailListModel item = mList.get(i);
        dDetailViewHolder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
