package com.example.mattimoestechshop.Model;

public class RatingSystem {

    private String username;
    private String productId;
    private String rateValue;
    private String comment;

    public RatingSystem() {
    }

    public RatingSystem(String username, String productId, String rateValue, String comment) {
        this.username = username;
        this.productId = productId;
        this.rateValue = rateValue;
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
