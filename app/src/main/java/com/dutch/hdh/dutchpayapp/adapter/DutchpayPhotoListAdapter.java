package com.dutch.hdh.dutchpayapp.adapter;

import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.databinding.ItemDutchpayPhotoBinding;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.photo.DutchpayPhotoContract;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.photo.TempPhotoListModel;

public class DutchpayPhotoListAdapter extends RecyclerView.Adapter<DutchpayPhotoListAdapter.dPhotoViewHolder> {

    private ObservableArrayList<TempPhotoListModel> mList;
    private DutchpayPhotoContract.Presenter mPresenter;

    public class dPhotoViewHolder extends RecyclerView.ViewHolder{

        ItemDutchpayPhotoBinding mBinding;

        public dPhotoViewHolder(ItemDutchpayPhotoBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        void bind(TempPhotoListModel item) {
            mBinding.setItem(item);

            mBinding.clPhotoItem.setOnClickListener(v -> {
                mPresenter.onItemClick();
            });

            //mBinding.getRoot().setOnClickListener(v -> mDSatrtPresenter.onItemClick(item));
        }
    }

    public DutchpayPhotoListAdapter(ObservableArrayList<TempPhotoListModel> mList, DutchpayPhotoContract.Presenter mPresenter) {
        this.mList = mList;
        this.mPresenter = mPresenter;
    }

    public void setItem(ObservableArrayList<TempPhotoListModel> list){
        if(list == null){ return; }
        this.mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public dPhotoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemDutchpayPhotoBinding binding = ItemDutchpayPhotoBinding.inflate(LayoutInflater.from(viewGroup.getContext()),viewGroup,false);

        return new dPhotoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull dPhotoViewHolder dPhotoViewHolder, int i) {
        TempPhotoListModel item = mList.get(i);
        dPhotoViewHolder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }



}
