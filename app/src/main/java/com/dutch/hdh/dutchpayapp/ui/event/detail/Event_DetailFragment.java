package com.dutch.hdh.dutchpayapp.ui.event.detail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentEventDetailBinding;
import com.dutch.hdh.dutchpayapp.databinding.FragmentEventMainBinding;

public class Event_DetailFragment extends BaseFragment implements Event_DetailContract.View {

    private FragmentEventDetailBinding mBinding;
    private Event_DetailContract.Presenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater , R.layout.fragment_event_detail, container, false);
        mPresenter = new Event_DetailPresenter(this , getContext() ,getFragmentManager());

        initData();

        mBinding.btEventJoin.setOnClickListener(v->
            mPresenter.clickEventJoin()
        );

        return mBinding.getRoot();
    }
    /**
     * 객체생성 및 데이터초기화
     */
    private void initData(){
        mPresenter.initView(getArguments());
    }

    @Override
    public void changeTitle(String title) {
        mBinding.tvEventTitle.setText(title);
    }

    @Override
    public void changeImage(String url) {
        Glide.with(getContext())
                .load(url)
                .error(R.drawable.intro_dutchpay_korea)
                .into(mBinding.ivEventImage);
    }

    @Override
    public void changeContent(String content) {
        mBinding.tvEventContent.setText(content);
    }
}
