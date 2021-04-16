package com.example.mattimoestechshop.BuilderPattern;

import com.example.mattimoestechshop.Model.ProductItem;

import java.util.ArrayList;

public class OrderClass {
    private ArrayList<CompletedOrder> completedOrders = new ArrayList<CompletedOrder>();

    public void addProductItemToOrder(CompletedOrder completedOrder) {
        completedOrders.add(completedOrder);
    }

    public float getCost() {
        float cost = 0.0f;

        return cost;
    }

    public void showItems() {

        for (CompletedOrder completedOrder : completedOrders) {
            System.out.print("Item : " + completedOrder.productItem());
            System.out.print("Delivery : " + completedOrder.getDelivery());
            System.out.print("Order Type : " + completedOrder.customer());


        }
    }
}
