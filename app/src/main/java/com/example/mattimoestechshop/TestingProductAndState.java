package com.example.mattimoestechshop;

import com.example.mattimoestechshop.Admin.AdminAddProductToDatabase;
import com.example.mattimoestechshop.Model.ProductItem;

public class TestingProductAndState {



    public static void main(String[] args) {
        AdminAddProductToDatabase ad;
        ProductItem productItem;
        String pn = "Iphone 11";
        String manu ="Apple";
        String cat = "Phone";
        int quantityAdded = 20;
        String desc = "this is a long description";
        int price = 300;
        productItem = new ProductItem();
        productItem.setProductName(pn);
        productItem.setProductManufacturer(manu);
        productItem.setProductCategory(cat);
        productItem.setProductStockOnhand(quantityAdded);
        productItem.setProductDescription(desc);
        productItem.setProductPrice(price);
        System.out.println("This is the product state before resupply " + productItem.getProductState());
        productItem.resupplyProduct(quantityAdded);
        System.out.println("This is the product state after resupply " + productItem.getProductState());
        ad = new AdminAddProductToDatabase();
        ad.addProduct(productItem);
        System.out.println("This is the product state after added " + productItem.getProductState());

    }
}
