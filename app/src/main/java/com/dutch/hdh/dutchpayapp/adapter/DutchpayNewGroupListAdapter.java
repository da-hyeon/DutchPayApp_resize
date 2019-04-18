package com.dutch.hdh.dutchpayapp.adapter;

import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.databinding.ItemDutchpayGroupListBinding;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchaddgroup.DutchpayNewAddGroupContract;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchaddgroup.DutchpayNewAddGroupModel;

public class DutchpayNewGroupListAdapter extends  RecyclerView.Adapter<DutchpayNewGroupListAdapter.dGroupViewHolder>{

    private ObservableArrayList<DutchpayNewAddGroupModel> mGroupList;
    private DutchpayNewAddGroupContract.Presenter mAGPresenter;

    public class dGroupViewHolder extends RecyclerView.ViewHolder {

        ItemDutchpayGroupListBinding mBinding;

        public dGroupViewHolder(ItemDutchpayGroupListBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        void bind(DutchpayNewAddGroupModel item) {
            mBinding.setItem(item);

            //아이콘 변동
            mBinding.ivGroupIcon.setImageResource(Constants.groupIconID(item.getGicon()));

            mBinding.clListItem.setOnClickListener(v->{
                if(item.isGcheck()){
                    item.setGcheck( !(item.isGcheck()) );
                    //카운트 차감
                    mAGPresenter.addMemCount( -(item.getMemNum()) );
                    notifyDataSetChanged();
                } else {
                    item.setGcheck( !(item.isGcheck()) );
                    //카운트 적립
                    mAGPresenter.addMemCount( item.getMemNum() );
                    notifyDataSetChanged();
                    mAGPresenter.onPlusClick();
                }
            });
        }
    }

    public DutchpayNewGroupListAdapter(ObservableArrayList<DutchpayNewAddGroupModel> mGroupList, DutchpayNewAddGroupContract.Presenter mAGPresenter) {
        this.mGroupList = mGroupList;
        this.mAGPresenter = mAGPresenter;
    }

    public void setItem(ObservableArrayList<DutchpayNewAddGroupModel> list){
        if(list == null ){return;}
        this.mGroupList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public dGroupViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemDutchpayGroupListBinding binding = ItemDutchpayGroupListBinding.inflate(LayoutInflater.from(viewGroup.getContext()),viewGroup,false);

        return new dGroupViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull dGroupViewHolder dGroupViewHolder, int i) {

        DutchpayNewAddGroupModel item = mGroupList.get(i);
        dGroupViewHolder.bind(item);
    }

    @Override
    public int getItemCount() {
        return mGroupList.size();
    }
}
