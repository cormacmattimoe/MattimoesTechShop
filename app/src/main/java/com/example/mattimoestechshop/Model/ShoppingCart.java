package com.example.mattimoestechshop.Model;



import com.example.mattimoestechshop.StatePattern.ProductState;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart extends ProductItem {

    private int totalOfGoods;
    private ArrayList<ProductItem> productItems = new ArrayList<ProductItem>();
    ProductItem productItem;
    int numberOfItems;


    public ShoppingCart(){

    }

    public ShoppingCart(int totalOfGoods, ArrayList<ProductItem> productItems, ProductItem productItem, int numberOfItems) {
        this.totalOfGoods = totalOfGoods;
        this.productItems = productItems;
        this.productItem = productItem;
        this.numberOfItems = numberOfItems;
    }

    public int getTotalOfGoods() {
        return totalOfGoods;
    }

    public void setTotalOfGoods(int totalOfGoods) {
        this.totalOfGoods = totalOfGoods;
    }

    public ArrayList<ProductItem> getProductItems() {
        return productItems;
    }

    public void setProductItems(ArrayList<ProductItem> productItems) {
        this.productItems = productItems;
    }

    public ProductItem getProductItem() {
        return productItem;
    }

    public void setProductItem(ProductItem productItem) {
        this.productItem = productItem;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public void addProductItem(ProductItem productItem) {
        totalOfGoods = totalOfGoods + (productItem.getProductStockOnhand() * productItem.getProductPrice());
        numberOfItems = numberOfItems + 1;
        this.productItems.add(productItem);
    }

    public void removeProductItem(ProductItem productItem) {
        totalOfGoods = totalOfGoods - (productItem.getProductStockOnhand() * productItem.getProductPrice());
        numberOfItems = numberOfItems - 1;
        this.productItems.remove(productItem);
    }
}