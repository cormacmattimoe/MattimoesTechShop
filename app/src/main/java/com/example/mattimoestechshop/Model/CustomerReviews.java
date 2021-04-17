package com.example.mattimoestechshop.Model;

public class CustomerReviews {

    private String customerUsername;
    private String productId;
    private String rateValue;
    private String comment;
    private String orderNumber;

    public CustomerReviews(String customerUsername, String productId, String rateValue, String comment, String orderNumber) {
        this.customerUsername = customerUsername;
        this.productId = productId;
        this.rateValue = rateValue;
        this.comment = comment;
        this.orderNumber = orderNumber;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getRateValue() {
        return rateValue;
    }

    public void setRateValue(String rateValue) {
        this.rateValue = rateValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
}