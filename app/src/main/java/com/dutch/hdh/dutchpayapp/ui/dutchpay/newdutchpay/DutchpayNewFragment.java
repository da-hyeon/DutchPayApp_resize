package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpay;

import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentDutchpayNewStartBinding;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.start.ItemDecoration;

public class DutchpayNewFragment extends BaseFragment implements DutchpayNewContract.View {

    FragmentDutchpayNewStartBinding mBinding;
    DutchpayNewPresenter mPresenter;

    String oldCost = "";
    String newCost = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dutchpay_new_start,container,false);
        mPresenter = new DutchpayNewPresenter(this);
        mBinding.setFragment(this);
        mBinding.setPresenter(mPresenter);

        //입력 완료_버그 수정
        mBinding.etCost.setImeOptions(EditorInfo.IME_ACTION_DONE);

        //키보드 변동 체크
        mBinding.getRoot().getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            Rect r = new Rect();
            mBinding.getRoot().getWindowVisibleDisplayFrame(r);

            int heightDiff = mBinding.getRoot().getRootView().getHeight() - (r.bottom - r.top);
            if(heightDiff > 150) { //키보드 닫음
                //프레젠터로... ->

                newCost = mBinding.etCost.getText().toString();

                if( !(oldCost.equals(newCost)) ){
                    oldCost = newCost;
                    Log.e("change --> ",newCost+"");
                    //1/n로직 재실행

                    //-----//
                }
            }
        });


        //리스트 셋업
        //mPresenter.listInit();

        return mBinding.getRoot();
    }

    @Override
    public void setDutchBtColor(boolean flag) {
        if(flag) {//활성화
            mBinding.btDutch.setBackgroundResource(R.drawable.dutchpay3_1_n);
        } else {//비활성화
            mBinding.btDutch.setBackgroundResource(R.drawable.dutchpay3_n_1_1);
        }
    }

    @Override
    public void setTypeBtColor(boolean flag) {
        if(flag){//활성화
            mBinding.btType.setBackgroundResource(R.drawable.dutchpay3_btn_1);
        } else {//비활성화
            mBinding.btType.setBackgroundResource(R.drawable.dutchpay3_btn);
        }
    }

    @Override
    public void adapterInit(){
        //어댑터 셋팅
        mBinding.setMemberList(mPresenter.getmNewList());
        mBinding.rvMemberlist.setAdapter(mPresenter.getmAdapter());
        mBinding.rvMemberlist.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mBinding.rvMemberlist.addItemDecoration(new ItemDecoration(38));
    }

}
