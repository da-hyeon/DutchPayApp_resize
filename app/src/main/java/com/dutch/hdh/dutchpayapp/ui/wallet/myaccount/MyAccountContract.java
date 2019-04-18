package com.dutch.hdh.dutchpayapp.ui.wallet.myaccount;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.dutch.hdh.dutchpayapp.base.activity.BaseActivityContract;
import com.dutch.hdh.dutchpayapp.data.db.AccountRegisterList;
import com.dutch.hdh.dutchpayapp.data.db.CardRegisterList;

import java.util.ArrayList;

public interface MyAccountContract extends BaseActivityContract {

    interface View extends BaseActivityContract.View {

        void initData();

        void showDeleteAccountDialog(String accountCode);

        void showRepresentativeAccountDialog(String mainAccountCode, String subAccountCode);

        void setRegisterAccountList(ArrayList<AccountRegisterList.AccountRegisterListResult> accountRegisterListResultArrayList);

    }

    interface Presenter {

        void getRegisterAccountList();

        //click
        void clickCancel();

        //계좌등록
        void clickAccountAdd();

        //대표계좌 선택
        void setRepresentativeAccount(String mainAccountCode, String subAccountCode);

        //계좌삭제
        void setAccountDelete(String accountCode);

        boolean isRepresentativeAccountNumber(String accountChoice);

        //코드에 맞게 이미지 가져오기
        int getBankImage(String accountTypeCode);

        String getBankBackgroundColor(String accountTypeCode);
    }
}
