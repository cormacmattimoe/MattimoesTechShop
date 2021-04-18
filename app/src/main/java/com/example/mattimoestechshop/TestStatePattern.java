
package com.example.mattimoestechshop;

import com.example.mattimoestechshop.Model.ProductItem;

public class TestStatePattern {

    public static void main(String[] args){

        ProductItem productItem = new ProductItem();


        productItem.setProductState(productItem.getProductAvailable());


        productItem.resupplyProduct(100);

        productItem.buyProduct(5);

        productItem.getProductState();

        productItem.buyProduct(40);

        productItem.returnProduct(1);

        if(productItem.getProductState()  == productItem.getProductAvailable())
        {
            productItem.buyProduct(30);
            System.out.println("This is the final balance " + productItem.getProductStockOnhand());
        }

        productItem.buyProduct(30);
        System.out.println("This is the final balance " + productItem.getProductStockOnhand());

        productItem.getProductState();




    }
}


