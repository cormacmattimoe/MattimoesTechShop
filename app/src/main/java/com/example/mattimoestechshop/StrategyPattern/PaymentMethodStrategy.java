package com.example.mattimoestechshop.StrategyPattern;

public interface PaymentMethodStrategy {

    //This creates the strategy pattern and allows the classes which are associated with this pattern to be implemented.
    // These classes are PayByPayPal, PayByCash,  and PayByCreditCard
    //
    void payForProduct(int amount);

}
