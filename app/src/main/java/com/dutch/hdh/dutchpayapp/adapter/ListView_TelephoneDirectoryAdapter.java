package com.dutch.hdh.dutchpayapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.telephony.PhoneNumberUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.TelephoneDirectory;
import com.dutch.hdh.dutchpayapp.databinding.ItemGroupTelephoneDirectoryBinding;
import com.dutch.hdh.dutchpayapp.ui.mygroup.telephonedirectory.MyGroup_TelephoneDirectoryContract;

import java.util.ArrayList;
import java.util.Locale;

public class ListView_TelephoneDirectoryAdapter extends BaseAdapter {

    private ItemGroupTelephoneDirectoryBinding mBinding;
    private MyGroup_TelephoneDirectoryContract.View mView;
    private MyGroup_TelephoneDirectoryContract.Presenter mPresenter;
    private Context mContext;
    private ArrayList<TelephoneDirectory> mTelephoneDirectoryArrayList;
    private ArrayList<TelephoneDirectory> mTelephoneDirectorySelectArrayList;

    private int addMemberCount;

    public ListView_TelephoneDirectoryAdapter(MyGroup_TelephoneDirectoryContract.View mView , MyGroup_TelephoneDirectoryContract.Presenter mPresenter ,Context mContext, ArrayList<TelephoneDirectory> mTelephoneDirectoryArrayList) {
        this.mView = mView;
        this.mContext = mContext;
        this.mPresenter = mPresenter;
        this.mTelephoneDirectoryArrayList = mTelephoneDirectoryArrayList;
        mTelephoneDirectorySelectArrayList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mTelephoneDirectoryArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mTelephoneDirectoryArrayList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("NewApi")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_group_telephone_directory, parent, false);
            v = mBinding.getRoot();
        } else {
            mBinding = (ItemGroupTelephoneDirectoryBinding) v.getTag();
        }

        mBinding.tvName.setSelected(true);
        mBinding.tvName.setText(mTelephoneDirectoryArrayList.get(position).getName());
        mBinding.tvPhoneNumber.setText(PhoneNumberUtils.formatNumber(mTelephoneDirectoryArrayList.get(position).getPhoneNumber(), Locale.getDefault().getCountry()));
        mBinding.cbCheckBox.setChecked(mTelephoneDirectoryArrayList.get(position).isCheckState());

        mBinding.clView.setOnClickListener(v1->{
            if(mTelephoneDirectoryArrayList.get(position).isCheckState()){
                mTelephoneDirectoryArrayList.get(position).setCheckState(false);
                mPresenter.setCheckState(position, false);
                addMemberCount--;
            } else {
                mTelephoneDirectoryArrayList.get(position).setCheckState(true);
                mPresenter.setCheckState(position, true);
                addMemberCount++;
            }
            notifyDataSetChanged();
            mView.changeAddMember(addMemberCount);
        });

        v.setTag(mBinding);
        return v;
    }

    public ArrayList<TelephoneDirectory> getList() {
        return mTelephoneDirectoryArrayList;
    }

    public int getAddMemberCount() {
        return addMemberCount;
    }

    public ArrayList<TelephoneDirectory> getSelectList(){
        int count = mTelephoneDirectoryArrayList.size();
        for(int i = 0; i < count; i++){
            if(mTelephoneDirectoryArrayList.get(i).isCheckState()){
                mTelephoneDirectorySelectArrayList.add(mTelephoneDirectoryArrayList.get(i));
            }
        }
        return mTelephoneDirectorySelectArrayList;
    }

    public void setVisibility(boolean state){
        if(state){
            mBinding.cbCheckBox.setVisibility(View.VISIBLE);
        } else {
            mBinding.cbCheckBox.setVisibility(View.GONE);
        }

    }
}
