package com.dutch.hdh.dutchpayapp.adapter;

import android.databinding.BindingConversion;
import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.dutch.hdh.dutchpayapp.R;
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

            //직접입력 반영
            if(item.isEditableFlag()){
                Log.e("check //->",item.getCost());
                mBinding.editText.setFocusableInTouchMode(true);
                setTextChangedListener(mBinding.editText,item);
            } else {
                mBinding.editText.setFocusableInTouchMode(false);
            }

            //뷰 셋팅
            if(!item.isCompleteFlag()) {
                mBinding.btPayComplete.setBackgroundResource(R.drawable.dutchpay3_1);
            } else {
                mBinding.btPayComplete.setBackgroundResource(R.drawable.dutchpay3_3);
            }

            if(lastItemCheck(item)){ //다음 버튼 생성
                mBinding.clMember.setVisibility(View.INVISIBLE);
                mBinding.btNext.setVisibility(View.VISIBLE);

                mBinding.btNext.setOnClickListener(v -> mDNewPresenter.onNextClick());
            } else {
                mBinding.clMember.setVisibility(View.VISIBLE);
                mBinding.btNext.setVisibility(View.GONE);
            }

            //클릭 처리
            mBinding.btPayComplete.setOnClickListener(v -> { //사전납부 버튼
                if(item.isCompleteFlag()) {
                    mBinding.btPayComplete.setBackgroundResource(R.drawable.dutchpay3_1);
                    item.setCompleteFlag(false);
                } else {
                    mBinding.btPayComplete.setBackgroundResource(R.drawable.dutchpay3_3);
                    item.setCompleteFlag(true);
                }
            });

            mBinding.btDelete.setOnClickListener(v -> { //삭제 버튼

                mList.remove(item);
                notifyDataSetChanged();

                if(mList.size() == 1){ //다음 버튼 제거
                    mList.clear();
                }
            });
        }

        private void setTextChangedListener(EditText et, TempNewListModel item){
            et.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    Log.e("check before //->",item.getCost());
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    item.setCost(s.toString());

                    Log.e("check after//->",item.getCost());

                    mDNewPresenter.reDutchpayLogic(item);
                }
            });
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
        if(mList.get(mList.size()-1).equals(item)) {
            return true;
        } else {
            return false;
        }
    }
}
