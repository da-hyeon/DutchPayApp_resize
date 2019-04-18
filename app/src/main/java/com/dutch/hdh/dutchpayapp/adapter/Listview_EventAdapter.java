package com.dutch.hdh.dutchpayapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;
import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.Event;
import com.dutch.hdh.dutchpayapp.databinding.ItemEventBinding;
import com.dutch.hdh.dutchpayapp.ui.event.detail.Event_DetailFragment;

import java.util.ArrayList;

public class Listview_EventAdapter extends BaseAdapter {

    private ItemEventBinding mBinding;
    private Context mContext;
    private FragmentManager mFragmentManager;

    private ArrayList<Event> mEventArrayList;

    public Listview_EventAdapter(Context mContext, ArrayList<Event> mEventArrayList, FragmentManager mFragmentManager) {
        this.mContext = mContext;
        this.mEventArrayList = mEventArrayList;
        this.mFragmentManager = mFragmentManager;
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

        mBinding.tvEventTitle.setText(mEventArrayList.get(position).getEventTitle());

        Glide.with(mContext)
                .load(MyApplication.getBaseUrl() + Constants.IMAGE_URL + mEventArrayList.get(position).getEventUploadName())
                .error(R.drawable.intro_dutchpay_korea)
                .into(mBinding.ivImage);


        if (!mEventArrayList.get(position).getEventStatus().equals("Y")) {
            mBinding.ivImage.setColorFilter(Color.parseColor("#BDBDBD"), PorterDuff.Mode.MULTIPLY);
        } else {
            mBinding.ivImage.setColorFilter(Color.parseColor("#00ff0000"), PorterDuff.Mode.DST);
        }

        mBinding.clView.setOnClickListener(v1 -> {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

            Bundle bundle = new Bundle();
            bundle.putString("eventTitle", mEventArrayList.get(position).getEventTitle());
            bundle.putString("eventUploadName", mEventArrayList.get(position).getEventUploadName());
            bundle.putString("eventContent", mEventArrayList.get(position).getEventContent());
            bundle.putBoolean("onGoing", mEventArrayList.get(position).getEventStatus().equals("Y"));
            Event_DetailFragment event_detailFragment = new Event_DetailFragment();
            event_detailFragment.setArguments(bundle);

            fragmentTransaction.replace(R.id.flFragmentContainer, event_detailFragment, Event_DetailFragment.class.getName());
            fragmentTransaction.addToBackStack(Event_DetailFragment.class.getName());
            fragmentTransaction.commit();
        });

        v.setTag(mBinding);
        return v;
    }

    public void setmEventArrayList(ArrayList<Event> mEventArrayList) {
        this.mEventArrayList = mEventArrayList;
    }
}
