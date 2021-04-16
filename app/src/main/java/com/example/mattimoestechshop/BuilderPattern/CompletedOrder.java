package com.example.mattimoestechshop.BuilderPattern;

import com.example.mattimoestechshop.BuilderPatternCustomer.Customer;
import com.example.mattimoestechshop.Model.ProductItem;

public interface CompletedOrder {

    public Customer customer();
    public ProductItem productItem();
    public Delivery getDelivery();
}
