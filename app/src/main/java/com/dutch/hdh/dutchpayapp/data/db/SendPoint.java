package com.dutch.hdh.dutchpayapp.data.db;

public class SendPoint {

    private String giveAmount;              //  양도금액
    private String buttonnumber;            // 1 핸드폰등록된 연락처 , 2.해당유저 가입 그룹 , 3.직접 추가
    private String usercode;                //포인트 양도하는 회원 코드
    private String pointqrcode;             //상품권 화 되는 바코드 번호 or qr코드 or 직접 입력 번호
    private String phonenumber;
    private String username;

    public SendPoint() {

    }

    public SendPoint(String giveAmount, String buttonnumber, String usercode, String pointqrcode, String phonenumber, String username) {
        this.giveAmount = giveAmount;
        this.buttonnumber = buttonnumber;
        this.usercode = usercode;
        this.pointqrcode = pointqrcode;
        this.phonenumber = phonenumber;
        this.username = username;
    }


    public String getGiveAmount() {
        return giveAmount;
    }

    public void setGiveAmount(String giveAmount) {
        this.giveAmount = giveAmount;
    }

    public String getButtonnumber() {
        return buttonnumber;
    }

    public void setButtonnumber(String buttonnumber) {
        this.buttonnumber = buttonnumber;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getPointqrcode() {
        return pointqrcode;
    }

    public void setPointqrcode(String pointqrcode) {
        this.pointqrcode = pointqrcode;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
