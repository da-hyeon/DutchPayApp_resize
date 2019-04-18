package com.dutch.hdh.dutchpayapp.data.db;

import java.util.ArrayList;

public class PayHistoryList {

    private ArrayList<PayHistoryListResult> payhistory;

    public ArrayList<PayHistoryListResult> getPayhistory() {
        return payhistory;
    }

    public void setPayhistory(ArrayList<PayHistoryListResult> payhistory) {
        this.payhistory = payhistory;
    }

    public class PayHistoryListResult {
        private String payment_no;
        private String payment_price;
        private String payment_card_code;
        private String payment_account_code;
        private String payment_type;
        private String user_email;
        private String user_no;
        private String payment_date;
        private String payment_status;
        private String refound_status;
        private String payment_room_status;

        public String getPayment_no() {
            return payment_no;
        }

        public void setPayment_no(String payment_no) {
            this.payment_no = payment_no;
        }

        public String getPayment_price() {
            return payment_price;
        }

        public void setPayment_price(String payment_price) {
            this.payment_price = payment_price;
        }

        public String getPayment_card_code() {
            return payment_card_code;
        }

        public void setPayment_card_code(String payment_card_code) {
            this.payment_card_code = payment_card_code;
        }

        public String getPayment_account_code() {
            return payment_account_code;
        }

        public void setPayment_account_code(String payment_account_code) {
            this.payment_account_code = payment_account_code;
        }

        public String getPayment_type() {
            return payment_type;
        }

        public void setPayment_type(String payment_type) {
            this.payment_type = payment_type;
        }

        public String getUser_email() {
            return user_email;
        }

        public void setUser_email(String user_email) {
            this.user_email = user_email;
        }

        public String getUser_no() {
            return user_no;
        }

        public void setUser_no(String user_no) {
            this.user_no = user_no;
        }

        public String getPayment_date() {
            return payment_date;
        }

        public void setPayment_date(String payment_date) {
            this.payment_date = payment_date;
        }

        public String getPayment_status() {
            return payment_status;
        }

        public void setPayment_status(String payment_status) {
            this.payment_status = payment_status;
        }

        public String getRefound_status() {
            return refound_status;
        }

        public void setRefound_status(String refound_status) {
            this.refound_status = refound_status;
        }

        public String getPayment_room_status() {
            return payment_room_status;
        }

        public void setPayment_room_status(String payment_room_status) {
            this.payment_room_status = payment_room_status;
        }
    }
}
