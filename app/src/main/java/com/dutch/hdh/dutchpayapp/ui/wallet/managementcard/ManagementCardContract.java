package com.dutch.hdh.dutchpayapp.ui.wallet.managementcard;

import com.dutch.hdh.dutchpayapp.adapter.CardManagementListAdapter;
import com.dutch.hdh.dutchpayapp.data.db.CardManagement;

import java.util.ArrayList;

public interface ManagementCardContract {

    interface View {
        void initData();
        void setAdapter();
        void setCardManagementListRefresh(ArrayList<CardManagement.CardManagementListResult> cardManagementListResultArrayList);

    }

    interface Presenter {


        //카드등록화면 이동
        void clickCardAdd();
        //카드삭제
        void cardDelete(String cardCode);
        //카드목록 요청
        void getCardRegisterList();


        ArrayList<CardManagement.CardManagementListResult> getCardManagementDummyData();
        void cancelClick();
    }
}
