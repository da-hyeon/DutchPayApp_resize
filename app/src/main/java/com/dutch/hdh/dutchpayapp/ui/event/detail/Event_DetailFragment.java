package com.dutch.hdh.dutchpayapp.ui.event.detail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentEventDetailBinding;
import com.kinda.alert.KAlertDialog;

import java.util.Objects;

public class Event_DetailFragment extends BaseFragment implements Event_DetailContract.View {

    private FragmentEventDetailBinding mBinding;
    private Event_DetailContract.Presenter mPresenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_detail, container, false);
        mPresenter = new Event_DetailPresenter(this, getContext(), getFragmentManager());

        initData();

        mBinding.btEventJoin.setOnClickListener(v ->
                mPresenter.clickEventJoin()
        );

        return mBinding.getRoot();
    }

    /**
     * 객체생성 및 데이터초기화
     */
    private void initData() {
        mPresenter.initView(getArguments());
    }

    /**
     * 타이틀 변경하기
     */
    @Override
    public void changeTitle(String title) {
        mBinding.tvEventTitle.setText(title);
    }

    /**
     * 이미지 변경하기
     */
    @Override
    public void changeImage(String url) {
        Glide.with(Objects.requireNonNull(getContext()))
                .load(url)
                .error(R.drawable.intro_dutchpay_korea)
                .into(mBinding.ivEventImage);
    }

    /**
     * 내용 변경하기
     */
    @Override
    public void changeContent(String content) {
        mBinding.tvEventContent.setText(content);
    }

    /**
     * 이벤트 참여하기 버튼 숨기기
     */
    @Override
    public void hideButton(boolean state) {
        if (state) {
            mBinding.btEventJoin.setVisibility(View.VISIBLE);
        } else {
            mBinding.btEventJoin.setVisibility(View.GONE);
        }
    }

    /**
     * 실패 다이얼로그 보이기
     * OK = 프래그먼트 되돌아가기
     */
    @Override
    public void showFailDialog(String title, String content) {
        new KAlertDialog(Objects.requireNonNull(getContext()), KAlertDialog.WARNING_TYPE)
                .setTitleText(title)
                .setContentText(content)
                .setConfirmText("확인")
                .setConfirmClickListener(sDialog -> {
                    sDialog.dismissWithAnimation();
                    if (getFragmentManager() != null) {
                        getFragmentManager().popBackStack();
                    }
                })
                .show();
    }
}
