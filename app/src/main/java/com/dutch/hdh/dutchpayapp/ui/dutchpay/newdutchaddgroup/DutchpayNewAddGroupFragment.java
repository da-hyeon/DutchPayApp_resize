package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchaddgroup;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.adapter.DutchpayNewGroupListAdapter;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentDutchpayAddGroupBinding;
import com.kinda.alert.KAlertDialog;

import java.util.Objects;

public class DutchpayNewAddGroupFragment extends BaseFragment implements DutchpayNewAddGroupContract.View{

    FragmentDutchpayAddGroupBinding mBinding;
    DutchpayNewAddGroupPresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), R.layout.fragment_dutchpay_add_group,container,false);
        mBinding.setFragment(this);
        mPresenter = new DutchpayNewAddGroupPresenter(this,getArguments());
        mBinding.setPresenter(mPresenter);

        //리스트 초기 생성
        mPresenter.listInit();

        return mBinding.getRoot();
    }

    @BindingAdapter("item")
    public static void bindItem(RecyclerView view, ObservableArrayList<DutchpayNewAddGroupModel> list){
        DutchpayNewGroupListAdapter adapter = (DutchpayNewGroupListAdapter) view.getAdapter();
        if(adapter != null) {
            adapter.setItem(list);
        }
    }

    @Override
    public void adapterInit() {
        //어댑터 셋팅
        mBinding.setGroupList(mPresenter.getGList());
        mBinding.rvGroupList.setAdapter(mPresenter.getAdapter());
        mBinding.rvGroupList.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
    }

    /**
     * 경고 다이얼로그 보여주기
     */
    @Override
    public void showWarningDialog(String title, String content) {
        new KAlertDialog(Objects.requireNonNull(getContext()), KAlertDialog.WARNING_TYPE)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText("확인")
                .setConfirmClickListener(sDialog -> {
                    sDialog.dismissWithAnimation();
                    mPresenter.onPlusClick();
                })
                .setCancelText("취소")
                .setCancelClickListener(KAlertDialog::dismissWithAnimation)
                .show();
    }


    /**
     * 뒤로가기 처리
     */
    public void onBackPressed(){
        showWarningDialog("경고", "취소하시겠습니까?\n확인을 누르시면 추가되지 않습니다.");
    }
}
