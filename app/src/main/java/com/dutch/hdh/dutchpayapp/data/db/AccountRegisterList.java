package com.dutch.hdh.dutchpayapp.data.db;

import java.util.ArrayList;

public class AccountRegisterList {

    private ArrayList<AccountRegisterListResult> accountlist;

    public ArrayList<AccountRegisterListResult> getAccountlist() {
        return accountlist;
    }

    public void setAccountlist(ArrayList<AccountRegisterListResult> accountlist) {
        this.accountlist = accountlist;
    }

    public class AccountRegisterListResult {

        private String account_Code;
        private String account_No;
        private String account_TypeCode;
        private String account_Choice;
        private String user_Code;
        private String account_Tno;
        private String account_Price;
        private String status;
        private String account_TypeName;
        private String upload_Name;


        public String getAccount_Code() {
            return account_Code;
        }

        public void setAccount_Code(String account_Code) {
            this.account_Code = account_Code;
        }

        public String getAccount_No() {
            return account_No;
        }

        public void setAccount_No(String account_No) {
            this.account_No = account_No;
        }

        public String getAccount_TypeCode() {
            return account_TypeCode;
        }

        public void setAccount_TypeCode(String account_TypeCode) {
            this.account_TypeCode = account_TypeCode;
        }

        public String getAccount_Choice() {
            return account_Choice;
        }

        public void setAccount_Choice(String account_Choice) {
            this.account_Choice = account_Choice;
        }

        public String getUser_Code() {
            return user_Code;
        }

        public void setUser_Code(String user_Code) {
            this.user_Code = user_Code;
        }

        public String getAccount_Tno() {
            return account_Tno;
        }

        public void setAccount_Tno(String account_Tno) {
            this.account_Tno = account_Tno;
        }

        public String getAccount_Price() {
            return account_Price;
        }

        public void setAccount_Price(String account_Price) {
            this.account_Price = account_Price;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getAccount_TypeName() {
            return account_TypeName;
        }

        public void setAccount_TypeName(String account_TypeName) {
            this.account_TypeName = account_TypeName;
        }

        public String getUpload_Name() {
            return upload_Name;
        }

        public void setUpload_Name(String upload_Name) {
            this.upload_Name = upload_Name;
        }

    }
}


