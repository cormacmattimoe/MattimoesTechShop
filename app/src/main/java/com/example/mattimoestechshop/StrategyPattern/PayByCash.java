package com.example.mattimoestechshop.StrategyPattern;

public class PayByCash implements PaymentMethodStrategy {
    @Override
    public void payForProduct(int amount) {
        System.out.println(amount + " paid using Cash.");
    }
}
