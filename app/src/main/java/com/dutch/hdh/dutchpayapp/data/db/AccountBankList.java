package com.dutch.hdh.dutchpayapp.data.db;

import java.util.ArrayList;

public class AccountBankList {

    private ArrayList<AccountBankListResult> accounttypelist;

    public ArrayList<AccountBankListResult> getAccounttypelist() {
        return accounttypelist;
    }

    public void setAccounttypelist(ArrayList<AccountBankListResult> accounttypelist) {
        this.accounttypelist = accounttypelist;
    }

    public class AccountBankListResult {

        private String account_TypeName;
        private String account_TypeCode;

        public String getAccount_TypeName() {
            return account_TypeName;
        }

        public void setAccount_TypeName(String account_TypeName) {
            this.account_TypeName = account_TypeName;
        }

        public String getAccount_TypeCode() {
            return account_TypeCode;
        }

        public void setAccount_TypeCode(String account_TypeCode) {
            this.account_TypeCode = account_TypeCode;
        }

    }
}
