package com.dutch.hdh.dutchpayapp.ui.customer;

public class CustomerPresenter implements CustomerContract.Presenter{

    CustomerContract.View mView;
    boolean flag1; boolean flag2;
    boolean flag3; boolean flag4;

    public CustomerPresenter(CustomerContract.View mView) {
        this.mView = mView;
        this.flag1 = false;
        this.flag2 = false;
        this.flag3 = false;
        this.flag4 = false;
    }

    @Override
    public void onListClick(int position) {
        switch (position){
            case 1 :
                flagCheck(flag1,position);
                flag1 = (!flag1);
                break;
            case 2 :
                flagCheck(flag2,position);
                flag2 = (!flag2);
                break;
            case 3 :
                flagCheck(flag3,position);
                flag3 = (!flag3);
                break;
            case 4 :
                flagCheck(flag4,position);
                flag4 = (!flag4);
                break;
        }
    }

    private void flagCheck(boolean flag,int pos){
        if(flag){
            mView.listClose(pos);
        } else {
            mView.listOpen(pos);
        }
    }

    public void onFAQClick(){
        mView.viewChange(1);
    }

    public void onQuestionClick(){
        mView.viewChange(2);
    }

    public void onSendClick(){
        //이메일 보내기
    }
}
