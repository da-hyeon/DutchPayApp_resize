package com.dutch.hdh.dutchpayapp.base.activity;

public interface BaseActivityContract {
    interface View {

        void showCommonDialog(String title, String content, boolean isBack);

    }

    interface Presenter {

    }
}
