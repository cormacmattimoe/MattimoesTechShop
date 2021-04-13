package com.example.mattimoestechshop.StrategyPattern;


import com.example.mattimoestechshop.Model.Order;

import java.util.List;

public class PayByCreditCard implements PaymentMethodStrategy {

    String name;
    String cardNumber;
    String cvvNumber;
    String dateOfExpiryMonth;
    String dateOfExpiryYear;
    String total;
    List<Order> cart;

    public PayByCreditCard(){

    }

    public PayByCreditCard(String name, String cardNumber, String cvvNumber, String dateOfExpiryMonth, String dateOfExpiryYear, String total, List<Order> cart) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.cvvNumber = cvvNumber;
        this.dateOfExpiryMonth = dateOfExpiryMonth;
        this.dateOfExpiryYear = dateOfExpiryYear;
        this.total = total;
        this.cart = cart;
    }

    @Override
    public void payForProduct(int totalAmount) {

        System.out.println(totalAmount + " paid using Credit Card.");
    }
}
