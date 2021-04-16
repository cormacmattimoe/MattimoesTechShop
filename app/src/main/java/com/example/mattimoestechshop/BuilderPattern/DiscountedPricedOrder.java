package com.example.mattimoestechshop.BuilderPattern;

import com.example.mattimoestechshop.BuilderPatternCustomer.Customer;
import com.example.mattimoestechshop.Model.ProductItem;

public class DiscountedPricedOrder implements CompletedOrder {
    @Override
    public Customer customer() {
        return null;
    }

    @Override
    public ProductItem productItem() {
        return null;
    }

    @Override
    public Delivery getDelivery() {
       return new StandardDelivery();
    }
}
