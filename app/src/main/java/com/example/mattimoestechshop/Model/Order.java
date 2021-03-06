package com.example.mattimoestechshop.Model;

import java.util.ArrayList;

public class Order {

    private String orderNumber;
    private String orderName;
    private String orderQuantity;
    private String orderPrice;
    private String orderStatus;
    private ArrayList<ProductItem> productItems;


    //basket, purchased, fulfilled//
    //payment method
    public Order() {
    }

    public Order(String orderNumber, String orderName, String orderQuantity, String orderPrice, String orderStatus, ArrayList<ProductItem> productItems) {
        this.orderNumber = orderNumber;
        this.orderName = orderName;
        this.orderQuantity = orderQuantity;
        this.orderPrice = orderPrice;
        this.orderStatus = orderStatus;
        this.productItems = productItems;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(String orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public ArrayList<ProductItem> getProductItems() {
        return productItems;
    }

    public void setProductItems(ArrayList<ProductItem> productItems) {
        this.productItems = productItems;
    }

    public void addProductItem(ProductItem productItem){
        this.productItems.add(productItem);
    }
}
