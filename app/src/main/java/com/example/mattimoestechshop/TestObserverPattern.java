

package com.example.mattimoestechshop;

import com.example.mattimoestechshop.ObserverPattern.CustomerObserver;
import com.example.mattimoestechshop.ObserverPattern.Mac;
import com.example.mattimoestechshop.ObserverPattern.Observable;
import com.example.mattimoestechshop.ObserverPattern.Observer;

public class TestObserverPattern {


        public static void main(String args[]) {
            Observable pObvervable = new Mac();

            Observer observer1 = new CustomerObserver(pObvervable);
            Observer observer2 = new CustomerObserver(pObvervable);
            Observer observer3 = new CustomerObserver(pObvervable);

            pObvervable.addObserver(observer1);
            pObvervable.addObserver(observer2);
            pObvervable.addObserver(observer3);

            pObvervable.notifyObserver();
        }




}
