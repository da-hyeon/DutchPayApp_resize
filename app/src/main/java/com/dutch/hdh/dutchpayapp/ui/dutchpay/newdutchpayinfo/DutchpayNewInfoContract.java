package com.dutch.hdh.dutchpayapp.ui.dutchpay.newdutchpayinfo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.EditText;

import java.util.ArrayList;

public interface DutchpayNewInfoContract {
    interface View{

        FragmentManager getFragmentManager();

        void ivShopChange(boolean flag);
        void ivDateChagne(boolean flag);
        void ivCommentChange(boolean flag);
        void setCount(int count);

        ArrayList<String> getText();
    }

    interface Presenter{


    }

}
