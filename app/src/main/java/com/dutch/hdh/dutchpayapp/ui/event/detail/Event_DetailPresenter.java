package com.dutch.hdh.dutchpayapp.ui.event.detail;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

public class Event_DetailPresenter implements Event_DetailContract.Presenter {

    private Event_DetailContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;
    private final String BASE_IMAGE_URL = "http://dutchkor02.cafe24.com/image/";

    public Event_DetailPresenter(Event_DetailContract.View mView, Context mContext, FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
    }

    @Override
    public void initView(Bundle bundle) {
        String eventTitle = bundle.getString("eventTitle");
        String eventUploadName = bundle.getString("eventUploadName");
        String eventContent = bundle.getString("eventContent");

        mView.changeTitle(eventTitle);
        mView.changeImage(BASE_IMAGE_URL + eventUploadName);
        mView.changeContent(eventContent);
    }

    @Override
    public void clickEventJoin() {
        mView.showSuccessDialog("성공" , "이벤트에 참여했습니다.");
    }
}
