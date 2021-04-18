
package com.example.mattimoestechshop.StatePattern;


import com.example.mattimoestechshop.Model.ProductItem;

public class OutOfStock implements ProductState{
    ProductItem productItem;


    public OutOfStock(ProductItem newProductItem)
    {
        productItem = newProductItem;
    }



    public void buyProduct(int quantityBought) {
        //If customer is buying product and the product is inStock set product to Instock
        //else if customer is buying product and product is not in stock set product to OutOfStock
        productItem.setProductState(productItem.getNoProductAvailable());
    }
    public void resupplyProduct(int quantityResupplied) {
        //When updating stock as an admin the current state of that product is OutOfstock
        // this needs to be changed to inStock when updated
        productItem.setProductStockOnhand(+ quantityResupplied);
        productItem.setProductState(productItem.getProductAvailable());

    }

    public void returnProduct(int quantityReturned) {
        //When product is requested this should return the product if it is inStock
        productItem.setProductStockOnhand(+ quantityReturned);
        productItem.setProductState(productItem.getProductAvailable());
    }
}



