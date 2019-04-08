package com.dutch.hdh.dutchpayapp.ui.wallet.mycard;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Trace;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.dutch.hdh.dutchpayapp.MyApplication;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.BaseActivity;
import com.dutch.hdh.dutchpayapp.databinding.ActivityMyCardBinding;

public class MyCardActivity extends BaseActivity implements MyCardContract.View {

    private ActivityMyCardBinding mBinding;
    private MyCardContract.Presenter mPresenter;
    private MyApplication mMyApplication;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_my_card);
        mPresenter = new MyCardPresenter(this, this);
        mMyApplication = MyApplication.getInstance();
        initData();

        //취소
        mBinding.tvCancel.setOnClickListener(v -> {
                    mPresenter.clickCancel();
                }
        );
        //카드등록
        mBinding.llCardAdd.setOnClickListener(v -> {
                    mPresenter.clickCardManagement();
                }
        );
        mBinding.ivCardAdd.setOnClickListener(v -> {
                    mPresenter.clickCardManagement();
                }
        );
        //카드 목록 버튼
        mBinding.ivLeftArrow.setOnClickListener(v ->
                mPresenter.clickLeft(mBinding.vpCardList)
        );
        mBinding.ivRightArrow.setOnClickListener(v ->
                mPresenter.clickRight(mBinding.vpCardList)
        );


        mBinding.vpCardList.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {


            }

            @Override
            public void onPageScrollStateChanged(int i) {
                
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void initData() {
        mPresenter.setAdapter(mBinding.vpCardList, mBinding.tlIndicator);
    }
}
