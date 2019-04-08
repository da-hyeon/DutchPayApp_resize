package com.dutch.hdh.dutchpayapp.adapter;

import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.databinding.ItemDutchpayNewMemberBinding;
import com.dutch.hdh.dutchpayapp.databinding.ItemDutchpayNewPayMemBinding;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpay.DutchpayNewContract;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpay.TempNewListModel;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpayconfirm.DutchpayNewConfirmContract;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpayconfirm.TempConfirmListModel;

public class DutchpayConfirmListAdapter extends RecyclerView.Adapter<DutchpayConfirmListAdapter.dConfirmViewHolder> {

    private ObservableArrayList<TempConfirmListModel> mList;
    private DutchpayNewConfirmContract.Presenter mDConfirmPresenter;

    public class dConfirmViewHolder extends RecyclerView.ViewHolder{
        ItemDutchpayNewPayMemBinding mBinding;

        public dConfirmViewHolder(ItemDutchpayNewPayMemBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        void bind(TempConfirmListModel item) {
            mBinding.setItem(item);

            //mBinding.getRoot().setOnClickListener(v -> mDSatrtPresenter.onItemClick(item));
        }
    }

    public DutchpayConfirmListAdapter(ObservableArrayList<TempConfirmListModel> mList, DutchpayNewConfirmContract.Presenter mDConfirmPresenter) {
        this.mList = mList;
        this.mDConfirmPresenter = mDConfirmPresenter;
    }

    public void setItem(ObservableArrayList<TempConfirmListModel> list){
        if(list == null ){return;}
        this.mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public dConfirmViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemDutchpayNewPayMemBinding binding = ItemDutchpayNewPayMemBinding.inflate(LayoutInflater.from(viewGroup.getContext()),viewGroup,false);

        return new dConfirmViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull dConfirmViewHolder dConfirmViewHolder, int i) {
        TempConfirmListModel item = mList.get(i);
        dConfirmViewHolder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
