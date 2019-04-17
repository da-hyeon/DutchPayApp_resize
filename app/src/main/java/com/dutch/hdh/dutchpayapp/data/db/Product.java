package com.dutch.hdh.dutchpayapp.data.db;

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("product_Code")
    private String productCode;

    @SerializedName("product_Name")
    private String productName;

    @SerializedName("product_Price")
    private int productPrice;

    @SerializedName("product_Address")
    private String productAddress;

    @SerializedName("status")
    private String productStatus;

    @SerializedName("payproductcode")
    private String productPaymentNumber;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductAddress() {
        return productAddress;
    }

    public void setProductAddress(String productAddress) {
        this.productAddress = productAddress;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductPaymentNumber() {
        return productPaymentNumber;
    }

    public void setProductPaymentNumber(String productPaymentNumber) {
        this.productPaymentNumber = productPaymentNumber;
    }
}
