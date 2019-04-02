package com.dutch.hdh.dutchpayapp.ui.register.allview;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

public interface Register_ViewAllTermsConditionsContract {

    interface View {
        void changeTitle(String title);
        void changeContent(String content);
        void changeTOS(boolean state);
    }

    interface Presenter{
        void getData(Bundle bundle);
        void clickTOS(boolean state);
        void clickBackPressed();
    }
}
