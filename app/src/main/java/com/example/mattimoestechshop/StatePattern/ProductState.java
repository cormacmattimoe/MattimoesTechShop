package com.example.mattimoestechshop.StatePattern;

public interface ProductState {

   void buyProduct(int quantityBought);
   void resupplyProduct(int quantityResupplied);
   void returnProduct(int quantityReturned);



}
