package com.dutch.hdh.dutchpayapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.TelephoneDirectory;
import com.dutch.hdh.dutchpayapp.databinding.ItemGroupTelephoneDirectoryBinding;

import java.util.ArrayList;

public class ListView_TelephoneDirectoryAdapter extends BaseAdapter {

    private ItemGroupTelephoneDirectoryBinding mBinding;

    private Context mContext;
    private ArrayList<TelephoneDirectory> mTelephoneDirectoryArrayList;

    public ListView_TelephoneDirectoryAdapter(Context mContext, ArrayList<TelephoneDirectory> mTelephoneDirectoryArrayList) {
        this.mContext = mContext;
        this.mTelephoneDirectoryArrayList = mTelephoneDirectoryArrayList;
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
        mBinding.tvPhoneNumber.setText(mTelephoneDirectoryArrayList.get(position).getPhoneNumber());
        mBinding.cbCheckBox.setChecked(mTelephoneDirectoryArrayList.get(position).isCheckState());



        convertView.setOnClickListener(v1->{
            Toast.makeText(mContext, position+"", Toast.LENGTH_SHORT).show();
        });



        v.setTag(mBinding);
        return v;
    }

    public ArrayList<TelephoneDirectory> getList() {
        return mTelephoneDirectoryArrayList;
    }
}
