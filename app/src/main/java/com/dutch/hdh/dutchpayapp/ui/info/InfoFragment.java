package com.dutch.hdh.dutchpayapp.ui.info;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dutch.hdh.dutchpayapp.R;
import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragment;
import com.dutch.hdh.dutchpayapp.databinding.FragmentWebviewBinding;

public class InfoFragment extends BaseFragment {
    FragmentWebviewBinding mBinding;

    private WebSettings mWebSettings;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), R.layout.fragment_webview,container,false);

        mBinding.wvContainer.setWebViewClient(new WebViewClient() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                view.loadUrl(request.getUrl().toString());
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                mBinding.wvContainer.invalidate(); //화면에 꽉 채우기
                //Toast.makeText(getContext(),"로딩 완료",Toast.LENGTH_SHORT).show();
            }
        });
        mBinding.wvContainer.setWebChromeClient(new WebChromeClient());
        mBinding.wvContainer.setInitialScale(1);
        mBinding.wvContainer.setVerticalScrollBarEnabled(false); //스크롤바 설정
        mBinding.wvContainer.setHorizontalScrollBarEnabled(false); //..
        mBinding.wvContainer.setOverScrollMode(View.OVER_SCROLL_NEVER);

        mBinding.wvContainer.setOnKeyListener((v, keyCode, event) -> { //백버튼 수정

            if(event.getAction() != KeyEvent.ACTION_DOWN)
                return true;

            if(keyCode == KeyEvent.KEYCODE_BACK) {
                if(mBinding.wvContainer.canGoBack()) {
                    mBinding.wvContainer.goBack();
                } else {
                    getActivity().onBackPressed();
                }

                return true;
            }

            return false;
        });

        mWebSettings = mBinding.wvContainer.getSettings();
        mWebSettings.setJavaScriptEnabled(true); //자바스크립트 허용
        mWebSettings.setUseWideViewPort(true); //뷰포트의 메타 태그 지원
        mWebSettings.setLoadWithOverviewMode(true); //스크린 크기 맞춤
        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL); //html 스크린 맞춤
        mWebSettings.setLoadsImagesAutomatically(true); //이미지 로드 허용
        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); //캐시 설정
        mWebSettings.setDomStorageEnabled(true); //웹뷰내 local storage 사용 허용

        if(android.os.Build.VERSION.SDK_INT >= 21){
            // HTTPS HTTP의 연동, 서로 호출 가능하도록
            mWebSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        mBinding.wvContainer.loadUrl("http://kokomar3.dothome.co.kr/appview/operation guide.html");

        return mBinding.getRoot();
    }
}
