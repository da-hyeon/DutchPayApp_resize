package com.dutch.hdh.dutchpayapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.Event;
import com.dutch.hdh.dutchpayapp.data.db.MyGroup;
import com.dutch.hdh.dutchpayapp.databinding.ItemEventBinding;
import com.dutch.hdh.dutchpayapp.databinding.ItemMyGroupBinding;
import com.dutch.hdh.dutchpayapp.ui.mygroup.edit.MyGroup_EditFragment;

import java.util.ArrayList;

public class Listview_EventAdapter extends BaseAdapter {

    private ItemEventBinding mBinding;
    private Context mContext;
    private boolean onGoingCheck;

    private ArrayList<Event> mEventArrayList;

    public Listview_EventAdapter(Context mContext, ArrayList<Event> mEventArrayList) {
        this.mContext = mContext;
        this.mEventArrayList = mEventArrayList;
    }

    @Override
    public int getCount() {
        return mEventArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mEventArrayList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_event, parent, false);
            v = mBinding.getRoot();
        } else {
            mBinding = (ItemEventBinding) v.getTag();
        }

        mBinding.tvEventTitle.setText(mEventArrayList.get(position).getTitle());
        mBinding.ivImage.setImageDrawable(mEventArrayList.get(position).getImage());

        if(!onGoingCheck) {
            mBinding.ivImage.setColorFilter(Color.parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY);
        } else {
            mBinding.ivImage.setColorFilter(Color.parseColor("#00ff0000"), PorterDuff.Mode.DST);
        }

        v.setTag(mBinding);
        return v;
    }

    public void setmEventArrayList(ArrayList<Event> mEventArrayList) {
        this.mEventArrayList = mEventArrayList;
    }

    public void setOnGoingCheck(boolean onGoingCheck) {
        this.onGoingCheck = onGoingCheck;
    }
}
