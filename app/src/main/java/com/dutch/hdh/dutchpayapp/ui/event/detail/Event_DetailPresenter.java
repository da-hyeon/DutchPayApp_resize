package com.dutch.hdh.dutchpayapp.ui.event.detail;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.dutch.hdh.dutchpayapp.Constants;
import com.dutch.hdh.dutchpayapp.MyApplication;

public class Event_DetailPresenter implements Event_DetailContract.Presenter {

    private Event_DetailContract.View mView;
    private Context mContext;
    private FragmentManager mFragmentManager;

    /**
     * 생성자
     */
    Event_DetailPresenter(Event_DetailContract.View mView, Context mContext, FragmentManager mFragmentManager) {
        this.mView = mView;
        this.mContext = mContext;
        this.mFragmentManager = mFragmentManager;
    }

    /**
     * 뷰 세팅
     */
    @Override
    public void initView(Bundle bundle) {
        if(bundle != null) {
            mView.changeTitle(bundle.getString("eventTitle"));
            mView.changeImage(MyApplication.getBaseUrl() + Constants.IMAGE_URL + bundle.getString("eventUploadName"));
            mView.hideButton(bundle.getBoolean("onGoing"));
            mView.changeContent(bundle.getString("eventContent"));
        } else {
            mView.showFailDialog("실패" , "데이터 수신에 실패했습니다.");
        }
    }

    /**
     * 이벤트 참여하기 버튼 클릭 이벤트 처리
     */
    @Override
    public void clickEventJoin() {
        mView.showSuccessDialog("성공" , "이벤트에 참여했습니다.");
    }
}
