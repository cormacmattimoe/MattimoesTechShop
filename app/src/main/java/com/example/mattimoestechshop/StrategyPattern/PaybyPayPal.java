package com.example.mattimoestechshop.StrategyPattern;


import com.example.mattimoestechshop.Model.Order;

import java.util.ArrayList;

public class PaybyPayPal implements PaymentMethodStrategy{

    String emailId;
    String password;
    ArrayList<Order> shoppingCart;
    String name;

    public PaybyPayPal(){

    }
    public PaybyPayPal(String email, String passWord, ArrayList<Order> shoppingCart, String name){
        this.emailId=email;
        this.password=passWord;
        this.shoppingCart = shoppingCart;
        this.name = name;
    }

    @Override
    public void payForProduct(int amount) {
        System.out.println(amount + " paid using PayPal.");
    }

}

