package com.dutch.hdh.dutchpayapp.ui.notice;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentNoticeBinding;

public class NoticeFragment extends BaseFragment {

    FragmentNoticeBinding mBinding;

    private WebSettings mWebSettings;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), R.layout.fragment_notice,container,false);
        mBinding.setFragment(this);

        mBinding.wvNotice.setWebViewClient(new WebViewClient());
        mBinding.wvNotice.setVerticalScrollBarEnabled(false); //스크롤바 설정
        mBinding.wvNotice.setHorizontalScrollBarEnabled(false); //..

        mBinding.wvNotice.setOnKeyListener((v, keyCode, event) -> { //백버튼 수정

            if(event.getAction() != KeyEvent.ACTION_DOWN)
                return true;

            if(keyCode == KeyEvent.KEYCODE_BACK) {
                if(mBinding.wvNotice.canGoBack()) {
                    mBinding.wvNotice.goBack();
                } else {
                    getActivity().onBackPressed();
                }

                return true;
            }

            return false;
        });

        mWebSettings = mBinding.wvNotice.getSettings();
        mWebSettings.setJavaScriptEnabled(true); //자바스크립트 허용
        mWebSettings.setLoadWithOverviewMode(true); //스크린 크기 맞춤

        mBinding.wvNotice.loadUrl("http://dutchkor02.cafe24.com/dutchpay_notice.php");

        return mBinding.getRoot();
    }
}
