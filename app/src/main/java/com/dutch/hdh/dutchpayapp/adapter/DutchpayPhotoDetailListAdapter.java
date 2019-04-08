package com.dutch.hdh.dutchpayapp.adapter;

import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.databinding.ItemDutchpayListDetailBinding;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.photodetail.DutchpayPhotoDetailContract;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.startdetail.TempDetailListModel;

public class DutchpayPhotoDetailListAdapter extends RecyclerView.Adapter<DutchpayPhotoDetailListAdapter.dDetailPhotoViewHolder> {

    private ObservableArrayList<TempDetailListModel> mList;
    private DutchpayPhotoDetailContract.Presenter mDPhotoPresenter;

    public class dDetailPhotoViewHolder extends RecyclerView.ViewHolder{

        ItemDutchpayListDetailBinding mBinding;

        public dDetailPhotoViewHolder(ItemDutchpayListDetailBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        void bind(TempDetailListModel item) {
            mBinding.setItem(item);

            if(item.isImageFlag()) {
                mBinding.ivUserIcon.setVisibility(View.VISIBLE);
                mBinding.tvUserName.setVisibility(View.INVISIBLE);
            } else {
                mBinding.ivUserIcon.setVisibility(View.INVISIBLE);
                mBinding.tvUserName.setVisibility(View.VISIBLE);
            }

            //더미 디폴트
            mBinding.btReRequest.setVisibility(View.GONE);
            mBinding.btCancel.setVisibility(View.GONE);
            mBinding.ivState.setImageResource(R.drawable.dutchpay_2);

            //mBinding.getRoot().setOnClickListener(v -> mDSatrtPresenter.onItemClick(item));
        }
    }

    public DutchpayPhotoDetailListAdapter(ObservableArrayList<TempDetailListModel> mList, DutchpayPhotoDetailContract.Presenter mDPhotoPresenter) {
        this.mList = mList;
        this.mDPhotoPresenter = mDPhotoPresenter;
    }

    public void setItem(ObservableArrayList<TempDetailListModel> list){
        if(list == null ){return;}
        this.mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public dDetailPhotoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemDutchpayListDetailBinding binding = ItemDutchpayListDetailBinding.inflate(LayoutInflater.from(viewGroup.getContext()),viewGroup,false);

        return new dDetailPhotoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull dDetailPhotoViewHolder dDetailViewHolder, int i) {
        TempDetailListModel item = mList.get(i);
        dDetailViewHolder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
