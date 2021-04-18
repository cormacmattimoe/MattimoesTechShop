package com.example.mattimoestechshop.ObserverPattern;

public class CustomerObserver implements Observer {

    Observable observable = null;

    public CustomerObserver(Observable observable) {
        this.observable = observable;
    }


    public void buyMac() {
        System.out.println("The Mac is now in stock");
    }

    public void unsubscribe() {
        observable.removeObserver(this);

    }

    @Override
    public void update() {
        buyMac();
        unsubscribe();
    }
}
