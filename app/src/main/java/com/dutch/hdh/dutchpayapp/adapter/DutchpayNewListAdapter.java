package com.dutch.hdh.dutchpayapp.adapter;

import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.databinding.ItemDutchpayNewMemberBinding;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpay.DutchpayNewContract;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpay.TempNewListModel;

public class DutchpayNewListAdapter extends RecyclerView.Adapter<DutchpayNewListAdapter.dNewViewHolder> {

    private ObservableArrayList<TempNewListModel> mList;
    private DutchpayNewContract.Presenter mDNewPresenter;

    public class dNewViewHolder extends RecyclerView.ViewHolder{

        ItemDutchpayNewMemberBinding mBinding;

        public dNewViewHolder(ItemDutchpayNewMemberBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        void bind(TempNewListModel item) {
            mBinding.setItem(item);

            if(lastItemCheck(item)){
                mBinding.clMember.setVisibility(View.INVISIBLE);
                mBinding.btNext.setVisibility(View.VISIBLE);

                mBinding.btNext.setOnClickListener(v -> mDNewPresenter.onNextClick());
            }

            //mBinding.getRoot().setOnClickListener(v -> mDSatrtPresenter.onItemClick(item));
        }
    }

    public DutchpayNewListAdapter(ObservableArrayList<TempNewListModel> mList, DutchpayNewContract.Presenter mDNewPresenter) {
        this.mList = mList;
        this.mDNewPresenter = mDNewPresenter;
    }

    public void setItem(ObservableArrayList<TempNewListModel> list){
        if(list == null ){return;}
        this.mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public dNewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemDutchpayNewMemberBinding binding = ItemDutchpayNewMemberBinding.inflate(LayoutInflater.from(viewGroup.getContext()),viewGroup,false);

        return new dNewViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull dNewViewHolder dNewViewHolder, int i) {
        TempNewListModel item = mList.get(i);
        dNewViewHolder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private boolean lastItemCheck(TempNewListModel item){
        if(mList.get(getItemCount()-1).equals(item)) {
            return true;
        } else {
            return false;
        }
    }
}
