package com.example.mattimoestechshop.Model;



import com.example.mattimoestechshop.StatePattern.ProductState;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private String totalOfGoods;
    private ArrayList<ProductItem> productItems = new ArrayList<ProductItem>();
    ProductItem productItem;


    public ShoppingCart(){

    }

    public ShoppingCart(String totalOfGoods, ArrayList<ProductItem> productItems) {
        this.totalOfGoods = totalOfGoods;
        this.productItems = productItems;
    }


    public String getTotalOfGoods() {
        return totalOfGoods;
    }

    public void setTotalOfGoods(String totalOfGoods) {
        this.totalOfGoods = totalOfGoods;
    }

    public ArrayList<ProductItem> getProductItems() {
        return productItems;
    }

    public void setProductItems(ArrayList<ProductItem> productItems) {
        this.productItems = productItems;
    }

    public void addProductItem(ProductItem productItem) {
        this.productItems.add(productItem);
    }
}