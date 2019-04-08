package com.dutch.hdh.dutchpayapp.adapter;

import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.R;
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

            if(item.isImageFlag()) { //이미지_이름 뷰 셋팅
                mBinding.ivUserIcon.setVisibility(View.VISIBLE);
                mBinding.tvUserName.setVisibility(View.INVISIBLE);
            } else {
                mBinding.tvUserName.setVisibility(View.VISIBLE);
                mBinding.ivUserIcon.setVisibility(View.INVISIBLE);
            }

            switch (item.getState()){
                case Constants.DUTCHPAY_STATE_WAIT :
                    mBinding.ivState.setImageResource(R.drawable.dutchpay_4);
                    mBinding.btReRequest.setOnClickListener(v -> {
                        //재요청 기능
                        Log.e("check ->","재요청 눌림");
                    });
                    mBinding.btCancel.setOnClickListener(v -> {
                        //취소 기능
                        Log.e("check ->","취소 눌림");
                    });
                    break;
                case Constants.DUTCHPAY_STATE_REQUEST :
                    mBinding.ivState.setImageResource(R.drawable.dutchpay_1);
                    mBinding.btReRequest.setVisibility(View.GONE);
                    mBinding.btCancel.setVisibility(View.GONE);
                    break;
                case Constants.DUTCHPAY_STATE_COMPLETE :
                    mBinding.ivState.setImageResource(R.drawable.dutchpay_2);
                    mBinding.btReRequest.setVisibility(View.GONE);
                    mBinding.btCancel.setVisibility(View.GONE);
                    break;
                case Constants.DUTCHPAY_STATE_CANCEL :
                    mBinding.ivState.setImageResource(R.drawable.dutchpay_3);
                    mBinding.btReRequest.setText("취소승인");
                    mBinding.btReRequest.setOnClickListener(v -> {
                        //취소승인 기능
                        Log.e("check ->","취소승인 눌림");
                    });
                    mBinding.btCancel.setText("거절");
                    mBinding.btCancel.setOnClickListener(v -> {
                        //거절 기능
                        Log.e("check ->","거절 눌림");
                    });
                    break;
            }
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
