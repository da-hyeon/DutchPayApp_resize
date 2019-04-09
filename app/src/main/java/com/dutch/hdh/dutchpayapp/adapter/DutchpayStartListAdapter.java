package com.dutch.hdh.dutchpayapp.adapter;

import android.databinding.BindingConversion;
import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.databinding.ItemDutchpayListBinding;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.start.DutchpayStartContract;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.start.TempStartListModel;


public class DutchpayStartListAdapter extends RecyclerView.Adapter<DutchpayStartListAdapter.dStartViewHolder> {

    private ObservableArrayList<TempStartListModel> mList;
    private DutchpayStartContract.Presenter mDSatrtPresenter;

    public class dStartViewHolder extends RecyclerView.ViewHolder{

        ItemDutchpayListBinding mBinding;

        public dStartViewHolder(ItemDutchpayListBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        void bind(TempStartListModel item) {
            mBinding.setItem(item);

            //더치페이 상태 반영
            stateToImage(item.getState());

            mBinding.getRoot().setOnClickListener(v -> mDSatrtPresenter.onItemClick(item));
        }

        private void stateToImage(int state){
            switch (state) {
                case Constants.DUTCHPAY_STATE_WAIT:
                    mBinding.ivDutchpayState.setImageResource(R.drawable.dutchpay_4);
                    break;
                case Constants.DUTCHPAY_STATE_REQUEST:
                    mBinding.ivDutchpayState.setImageResource(R.drawable.dutchpay_1);
                    break;
                case Constants.DUTCHPAY_STATE_COMPLETE:
                    mBinding.ivDutchpayState.setImageResource(R.drawable.dutchpay_2);
                    break;
                case Constants.DUTCHPAY_STATE_CANCEL:
                    mBinding.ivDutchpayState.setImageResource(R.drawable.dutchpay_3);
                    break;
            }
        }
    }

    public DutchpayStartListAdapter(ObservableArrayList<TempStartListModel> mList,DutchpayStartContract.Presenter presenter) {
        this.mList = mList;
        this.mDSatrtPresenter = presenter;
    }

    public void setItem(ObservableArrayList<TempStartListModel> list){
        if(list == null ){return;}
        this.mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public dStartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemDutchpayListBinding binding = ItemDutchpayListBinding.inflate(LayoutInflater.from(viewGroup.getContext()),viewGroup,false);

        return new dStartViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull dStartViewHolder dStartViewHolder, int i) {
        TempStartListModel item = mList.get(i);
        dStartViewHolder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

}
