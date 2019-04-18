package com.dutch.hdh.dutchpayapp.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.data.db.Event;
import com.dutch.hdh.dutchpayapp.databinding.EventSliderBinding;
import com.dutch.hdh.dutchpayapp.ui.event.detail.Event_DetailFragment;
import com.dutch.hdh.dutchpayapp.ui.main.fragment.MainFragmentContract;

import java.util.ArrayList;
import java.util.List;

public class EventImageSliderAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<Event> mEventArrayList;
    private MainFragmentContract.View mView;
    private FragmentManager mFragmentManager;

    public EventImageSliderAdapter(MainFragmentContract.View mView, Context mContext, FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
    }

    @Override
    public int getCount() {
        return mEventArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        EventSliderBinding mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.event_slider, container, false);
        mBinding.getRoot().setTag(position);

        if (mEventArrayList.size() > 0) {
            Glide.with(mContext)
                    .load(MyApplication.getBaseUrl() + Constants.IMAGE_URL + mEventArrayList.get(position).getEventUploadName())
                    .error(R.drawable.intro_dutchpay_korea)
                    .into(mBinding.ivEventImage);

            // 이벤트타이틀 변경
            // mView.changeEventTitle(mEventArrayList.get(mEventArrayList.get(position).getCurrentCount()).getEventTitle());

            //Toast.makeText(mContext, mEventArrayList.get(position).getEventTitle(), Toast.LENGTH_SHORT).show();
            mBinding.ivEventImage.setOnClickListener(v1 -> {
                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.fade_in, 0, 0, R.anim.fade_out);

                Bundle bundle = new Bundle();
                bundle.putString("eventTitle", mEventArrayList.get(position).getEventTitle());
                bundle.putString("eventUploadName", mEventArrayList.get(position).getEventUploadName());
                bundle.putString("eventContent", mEventArrayList.get(position).getEventContent());

                Event_DetailFragment event_detailFragment = new Event_DetailFragment();
                event_detailFragment.setArguments(bundle);

                fragmentTransaction.replace(R.id.flFragmentContainer, event_detailFragment, Event_DetailFragment.class.getName());
                fragmentTransaction.addToBackStack(Event_DetailFragment.class.getName());
                fragmentTransaction.commit();
            });
        }

        container.addView(mBinding.getRoot());
        return mBinding.getRoot();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.invalidate();
    }

    public void setmEventArrayList(ArrayList<Event> mEventArrayList) {
        this.mEventArrayList = mEventArrayList;
    }
}
