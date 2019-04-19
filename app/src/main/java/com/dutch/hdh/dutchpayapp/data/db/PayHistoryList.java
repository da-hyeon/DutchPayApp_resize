package com.dutch.hdh.dutchpayapp.data.db;

import java.util.ArrayList;

public class PayHistoryList  {

    private ArrayList<PayHistoryListResult> totallist;

    public ArrayList<PayHistoryListResult> getTotallist() {
        return totallist;
    }

    public void setTotallist(ArrayList<PayHistoryListResult> totallist) {
        this.totallist = totallist;
    }

    public class PayHistoryListResult {

        private String payment_Code;
        private String Pay_Types;
        private String singleTitle;
        private String dutchPay_Title;
        private String payMent_Price;
        private String user_Code;
        private String progress_Date;

        public String getPayment_Code() {
            return payment_Code;
        }

        public void setPayment_Code(String payment_Code) {
            this.payment_Code = payment_Code;
        }

        public String getPay_Types() {
            return Pay_Types;
        }

        public void setPay_Types(String pay_Types) {
            Pay_Types = pay_Types;
        }

        public String getSingleTitle() {
            return singleTitle;
        }

        public void setSingleTitle(String singleTitle) {
            this.singleTitle = singleTitle;
        }

        public String getDutchPay_Title() {
            return dutchPay_Title;
        }

        public void setDutchPay_Title(String dutchPay_Title) {
            this.dutchPay_Title = dutchPay_Title;
        }

        public String getPayMent_Price() {
            return payMent_Price;
        }

        public void setPayMent_Price(String payMent_Price) {
            this.payMent_Price = payMent_Price;
        }

        public String getUser_Code() {
            return user_Code;
        }

        public void setUser_Code(String user_Code) {
            this.user_Code = user_Code;
        }

        public String getProgress_Date() {
            return progress_Date;
        }

        public void setProgress_Date(String progress_Date) {
            this.progress_Date = progress_Date;
        }
    }
}
