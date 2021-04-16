package com.example.mattimoestechshop.ObserverPattern;

import java.util.ArrayList;


public class Mac implements Observable {
    ArrayList<Observer> customers = new ArrayList<>();

    private boolean inStock = true;

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
        notifyObserver();
    }


    @Override
    public void addObserver(com.example.mattimoestechshop.ObserverPattern.Observer o) {
        customers.add(o);
    }

    @Override
    public void removeObserver(com.example.mattimoestechshop.ObserverPattern.Observer o) {
        customers.remove(o);
    }

    @Override
    public void notifyObserver() {
        for(Observer customerOb : customers){
            customerOb.update();
        }
    }
}
