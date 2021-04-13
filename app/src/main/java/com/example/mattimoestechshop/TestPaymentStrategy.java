package com.example.mattimoestechshop;


import com.example.mattimoestechshop.StrategyPattern.PayByCash;
import com.example.mattimoestechshop.StrategyPattern.PayByCreditCard;
import com.example.mattimoestechshop.StrategyPattern.PaybyPayPal;
import com.example.mattimoestechshop.StrategyPattern.PaymentMethodStrategy;

public class TestPaymentStrategy {



    public static void main(String[] args){
        PaymentMethodStrategy pms;
        int amount = 60;
        if (amount > 500){
            pms = new PaybyPayPal();
            pms.payForProduct(amount);
        }else if(amount < 500){
            pms = new PayByCreditCard();
            pms.payForProduct(amount);
        }else{
            pms = new PayByCash();
            pms.payForProduct(amount);
        }






    }
}
