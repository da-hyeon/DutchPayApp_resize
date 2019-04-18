package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpay;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.data.db.DirectInputParticipants;
import com.dutch.hdh.dutchpayapp.databinding.FragmentDutchpayNewStartBinding;
import com.dutch.hdh.dutchpayapp.ui.dutchpay.start.ItemDecoration;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class DutchpayNewFragment extends BaseFragment implements DutchpayNewContract.View {

    FragmentDutchpayNewStartBinding mBinding;
    DutchpayNewPresenter mPresenter;

    TextWatcher tw = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            //리스트 마지막_더치 대표자 데이터 셋팅

            mPresenter.getmNewList().get(mPresenter.getmNewList().size()-1).setEditedCheck(true);

            if(!s.toString().equals("") && Integer.parseInt(s.toString()) == 0){
                mPresenter.getmNewList().get(mPresenter.getmNewList().size()-1).setCost("0");
            } else {
                mPresenter.getmNewList().get(mPresenter.getmNewList().size()-1).setCost(s.toString());
            }
        }
    };

    private boolean viewFlag = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_dutchpay_new_start,container,false);
        mPresenter = new DutchpayNewPresenter(this);
        mBinding.setFragment(this);
        mBinding.setPresenter(mPresenter);

        //입력 완료_버그 수정
        mBinding.etCost.setImeOptions(EditorInfo.IME_ACTION_DONE);
        mBinding.etMycost.setImeOptions(EditorInfo.IME_ACTION_DONE);

        setMyCostEditable(false);

        //키보드 변동 체크
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mBinding.getRoot().getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            Rect r = new Rect();
            mBinding.getRoot().getWindowVisibleDisplayFrame(r);

            int heightDiff = mBinding.getRoot().getRootView().getHeight() - (r.bottom - r.top);
            if(heightDiff > 600){

                if(!viewFlag){
                    viewFlag = true; //키보드 열림
                }
            } else if(heightDiff > 150) {
                //Log.e("heightDiff",heightDiff+"");
                //금액 값 변동 확인
                mPresenter.checkCost(mBinding.etCost.getText().toString());

                if(viewFlag) {
                    if (mPresenter.getmNewList().size() != 0) {

                        mPresenter.reDutchpayLogic();
                        mPresenter.getmAdapter().notifyDataSetChanged();
                        mBinding.etMycost.removeTextChangedListener(tw);
                    }
                    viewFlag = false; //키보드 닫음
                }
            }
        });

        return mBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        //키보드 설정 변경
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        if(!mBinding.etCost.getText().toString().equals("")){
            mPresenter.setOldCost(mBinding.etCost.getText().toString());
        }

        if(mPresenter.getmMyApplication().isDutchpayGroup()){
            if(getArguments() != null){
                mPresenter.listInit(getArguments());
            }
        } else {
            if(getArguments() != null){
                if( !(getArguments().getString("dutchpayListData").equals("")) ) { //이전 리스트 유무 체크
                    String oldList = getArguments().getString("dutchpayListData");

                    Log.e("oldlist ->" ,oldList);
                }
            }
        }
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
    public void setMyCost(String mycost) {
        mBinding.etMycost.setText(mycost);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void setMyCostEditable(boolean flag) {
        mBinding.etMycost.setFocusableInTouchMode(flag);
        if(flag){
            mBinding.etMycost.setOnTouchListener((v, event) -> {
                mBinding.etMycost.addTextChangedListener(tw);
                return false;
            });
        }
    }

    @Override
    public void setMemCount(int count) {
        mBinding.tvMemNum.setText("총 "+count+"명");
    }

    @Override
    public void setDutchCost(String oldCost) {
        mBinding.etCost.setText(oldCost);
    }

    @Override
    public void adapterInit(){
        if( !(mBinding.etCost.getText().toString().equals("")) ) {
            //금액 입력

            mPresenter.reDutchpayLogic();
        }

        mBinding.tvMemNum.setText("총 "+mPresenter.getmNewList().size()+"명");
        if(mPresenter.getmNewList().size() != 0) {
            setMyCost(mPresenter.getmNewList().get(mPresenter.getmNewList().size() - 1).getCost() + "");
        } else {
            setMyCost(0+"");
        }

        //어댑터 셋팅
        mBinding.setMemberList(mPresenter.getmNewList());
        mBinding.rvMemberlist.setAdapter(mPresenter.getmAdapter());
        mBinding.rvMemberlist.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        mBinding.rvMemberlist.addItemDecoration(new ItemDecoration(38));

        mPresenter.getmAdapter().notifyDataSetChanged();
    }

    @Override
    public void makeToast(String message) {
        Toast.makeText(getActivity().getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    /**
     * 뒤로가기 처리
     */
    public void onBackPressed(){
        mPresenter.clickBackPressed();
    }



}
