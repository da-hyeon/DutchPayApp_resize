package com.dutch.hdh.dutchpayapp.ui.event.detail;

import android.os.Bundle;

import com.dutch.hdh.dutchpayapp.base.fragment.BaseFragmentContract;

public interface Event_DetailContract {
    interface View extends BaseFragmentContract.View {
        void changeTitle(String title);
        void changeImage(String url);
        void changeContent(String content);
    }
    interface Presenter{
        void initView(Bundle bundle);

        void clickEventJoin();
    }
}
