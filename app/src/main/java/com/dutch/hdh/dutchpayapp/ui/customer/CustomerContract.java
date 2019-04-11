package com.dutch.hdh.dutchpayapp.ui.customer;

public interface CustomerContract {

    interface View{

        void listOpen(int position);
        void listClose(int position);
        void viewChange(int page);

    }

    interface Presenter{

        //click
        void onListClick(int position);
    }
}
