package com.dutch.hdh.dutchpayapp.ui.wallet.managementcard;



import com.dutch.hdh.dutchpayapp.base.activity.BaseActivityContract;
import com.dutch.hdh.dutchpayapp.data.db.CardRegisterList;

import java.util.ArrayList;

public interface ManagementCardContract extends BaseActivityContract {

    interface View extends BaseActivityContract.View {
        void initData();

        void showDeleteCardDialog(String cardCode);

        void setRegisterCardList(ArrayList<CardRegisterList.CardRegisterListResult> cardRegisterListResultArrayList);
    }

    interface Presenter {


        boolean isRepresentativeCard(String cardChoice);

        //카드등록화면 이동
        void clickCardAdd();
        //카드삭제
        void setCardDelete(String cardCode);
        //카드목록 요청
        void getRegisterCardList();

        void cancelClick();
    }
}
