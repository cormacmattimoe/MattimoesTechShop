package com.example.mattimoestechshop.ObserverPattern;

public interface Observable {

    public void addObserver(com.example.mattimoestechshop.ObserverPattern.Observer o);

    public void removeObserver(Observer o);

    public void notifyObserver();

}
