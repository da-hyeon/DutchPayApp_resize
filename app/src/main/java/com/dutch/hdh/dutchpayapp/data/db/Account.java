package com.dutch.hdh.dutchpayapp.data.db;

import com.google.gson.annotations.SerializedName;

public class Account {
      @SerializedName("account_Code")
      String accountCode;

      @SerializedName("account_No")
      String accountNumber;

      @SerializedName("account_TypeCode")
      String accountTypeCode;

      @SerializedName("account_Choice")
      String accountChoice;

      @SerializedName("account_TypeName")
      String accountTypeName;

      public String getAccountCode() {
            return accountCode;
      }

      public void setAccountCode(String accountCode) {
            this.accountCode = accountCode;
      }

      public String getAccountNumber() {
            return accountNumber;
      }

      public void setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
      }

      public String getAccountTypeCode() {
            return accountTypeCode;
      }

      public void setAccountTypeCode(String accountTypeCode) {
            this.accountTypeCode = accountTypeCode;
      }

      public String getAccountChoice() {
            return accountChoice;
      }

      public void setAccountChoice(String accountChoice) {
            this.accountChoice = accountChoice;
      }

      public String getAccountTypeName() {
            return accountTypeName;
      }

      public void setAccountTypeName(String accountTypeName) {
            this.accountTypeName = accountTypeName;
      }
}
