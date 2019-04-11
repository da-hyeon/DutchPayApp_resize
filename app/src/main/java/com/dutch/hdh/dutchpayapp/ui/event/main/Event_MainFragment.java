package com.dutch.hdh.dutchpayapp.ui.event.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentEventMainBinding;


public class Event_MainFragment extends BaseFragment implements Event_MainContract.View {

    private Event_MainContract.Presenter mPresenter;
    private FragmentEventMainBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater , R.layout.fragment_event_main, container, false);
        mPresenter = new Event_MainPresenter(this ,getContext(), getFragmentManager());

        initData();

        mBinding.btOnGoing.setOnClickListener(v->
            mPresenter.clickOnGoingEvent()
        );

        mBinding.btEndProgress.setOnClickListener(v ->
            mPresenter.clickEndProgressEvent()
        );

        return mBinding.getRoot();
    }

    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {
        mPresenter.initListViewData(mBinding.lvEvent);
    }


}
