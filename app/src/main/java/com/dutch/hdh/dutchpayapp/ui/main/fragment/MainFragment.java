package com.dutch.hdh.dutchpayapp.ui.main.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.activity.BaseActivity;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentMainBinding;
import com.dutch.hdh.dutchpayapp.ui.main.activity.MainActivity;

public class MainFragment extends BaseFragment implements MainFragmentContract.View{

    private FragmentMainBinding mBinding;
    private MainFragmentContract.Presenter mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Trace.e("onAttach");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        mPresenter = new MainFragmentPresenter(this, getContext() , getFragmentManager() , getActivity());

        initData();


        //흐르게 설정
        mBinding.tvTitle.setSelected(true);

        //개인결제 시작하기 버튼 클릭
        mBinding.ivSoloPay.setOnClickListener(v->{
            mPresenter.clickSolopay();
        });

        //더치페이 시작하기 버튼 클릭
        mBinding.ivDutchPay.setOnClickListener(v->{

        });

        //모든 이벤트 보기 버튼 클릭
        mBinding.llAllEvent.setOnClickListener(v->
            mPresenter.clickAllEvent()
        );

        //ViewPager 슬라이드
        mBinding.vpMainVP.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mPresenter.slideViewPagerAction(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        return mBinding.getRoot();
    }

    /**
     * 객체생성 및 데이터초기화
     */
    @Override
    public void initData() {
        //뷰페이저에 어댑터 연결요청 + TabLayer 붙이기
        mPresenter.setAdapter(mBinding.vpMainVP, mBinding.tabMainTL);
        mPresenter.initLoginState();
    }

    @Override
    public void showUserInfo(String userName, int userDutchMoney, boolean state) {
        if (state) {
            mBinding.layoutSuccessLogin.setVisibility(View.VISIBLE);
            mBinding.layoutNoneLogin.setVisibility(View.GONE);

            mBinding.txtUserName.setText(userName + "님, 안녕하세요!");
            mBinding.txtUserDutchMoney.setText(String.format("%,d", userDutchMoney) + " ");
        } else {
            mBinding.layoutSuccessLogin.setVisibility(View.GONE);
             mBinding.layoutNoneLogin.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void changeEventTitle(String title) {
        mBinding.tvEventTitle.setText(title);
    }
}
