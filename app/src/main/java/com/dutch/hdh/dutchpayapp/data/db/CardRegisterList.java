package com.dutch.hdh.dutchpayapp.data.db;

import java.util.ArrayList;

public class CardRegisterList {

    private ArrayList<CardRegisterListResult> cardlist;

    public ArrayList<CardRegisterListResult> getCardlist() {
        return cardlist;
    }

    public void setCardlist(ArrayList<CardRegisterListResult> cardlist) {
        this.cardlist = cardlist;
    }

    public class CardRegisterListResult {

        private String card_Code;
        private String card_No;
        private String card_TypeCode;
        private String card_Choice;
        private String user_Code;
        private String card_Tno;
        private String card_Price;
        private String status;
        private String card_TypeName;
        private String upload_Name;


        public String getCard_Code() {
            return card_Code;
        }

        public void setCard_Code(String card_Code) {
            this.card_Code = card_Code;
        }

        public String getCard_No() {
            return card_No;
        }

        public void setCard_No(String card_No) {
            this.card_No = card_No;
        }

        public String getCard_TypeCode() {
            return card_TypeCode;
        }

        public void setCard_TypeCode(String card_TypeCode) {
            this.card_TypeCode = card_TypeCode;
        }

        public String getCard_Choice() {
            return card_Choice;
        }

        public void setCard_Choice(String card_Choice) {
            this.card_Choice = card_Choice;
        }

        public String getUser_Code() {
            return user_Code;
        }

        public void setUser_Code(String user_Code) {
            this.user_Code = user_Code;
        }

        public String getCard_Tno() {
            return card_Tno;
        }

        public void setCard_Tno(String card_Tno) {
            this.card_Tno = card_Tno;
        }

        public String getCard_Price() {
            return card_Price;
        }

        public void setCard_Price(String card_Price) {
            this.card_Price = card_Price;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCard_TypeName() {
            return card_TypeName;
        }

        public void setCard_TypeName(String card_TypeName) {
            this.card_TypeName = card_TypeName;
        }

        public String getUpload_Name() {
            return upload_Name;
        }

        public void setUpload_Name(String upload_Name) {
            this.upload_Name = upload_Name;
        }

    }
}


