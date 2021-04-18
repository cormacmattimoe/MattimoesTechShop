package com.example.mattimoestechshop.Model;


import com.example.mattimoestechshop.StatePattern.InStock;
import com.example.mattimoestechshop.StatePattern.OutOfStock;
import com.example.mattimoestechshop.StatePattern.ProductState;

public class ProductItem {

    private String productName;
    private String productCategory;
    private String productManufacturer;
    private int productPrice;
    private int productStockOnhand;
    private String productDescription;
    ProductState inStock;
    ProductState outOfStock;
    ProductState productState;


    public ProductItem() {
        inStock = new InStock(this);
        outOfStock = new OutOfStock(this);

        productState = outOfStock;

        if(productStockOnhand == 0){
            productState = outOfStock;
        }else{
            productState = inStock;
        }
    }
    public void buyProduct(int quantityBought) {
        //If customer is buying product and the product is inStock set product to Instock
        //else if customer is buying product and product is not in stock set product to OutOfStock
        System.out.println("This is the stock on hand in productItem = "  + this.getProductStockOnhand());
        productState.buyProduct(quantityBought);



    }



    public void resupplyProduct(int  quantityResupplied) {
        //When updating stock as an admin the current state of that product is OutOfstock
        // this needs to be changed to inStock when updated
        productState.resupplyProduct(quantityResupplied);

    }


    public void returnProduct(int quantityReturned) {
        //When product is requested this should return the product if it is inStock
        productState.returnProduct(quantityReturned);
    }
    public ProductState getNoProductAvailable() { return outOfStock; }

    public ProductState getProductAvailable() { return inStock; }



    public ProductItem(String productName, String productCategory, String productManufacturer, int productPrice, int productStockOnhand, String productDescription, ProductState productState) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.productManufacturer = productManufacturer;
        this.productPrice = productPrice;
        this.productStockOnhand = productStockOnhand;
        this.productDescription = productDescription;
        this.productState = productState;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductManufacturer() {
        return productManufacturer;
    }

    public void setProductManufacturer(String productManufacturer) {
        this.productManufacturer = productManufacturer;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductStockOnhand() {
        return productStockOnhand;
    }


    public void setProductStockOnhand(int productAdded) {

        productStockOnhand = productStockOnhand + productAdded;
        if(productStockOnhand > 0) {
            setProductState(getProductAvailable());

        }else{
            setProductState(getNoProductAvailable());
        }
        System.out.println("This is the state of product available " + productState);
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public ProductState getProductState() {
        System.out.println("This is the product state productItem " + productState);
        return productState;
    }

    public void setProductState(ProductState productState) {
        System.out.println("This is the state = " + productState);
        this.productState = productState;
    }
/*
    //factory method creates constructor for adding items to the cart
    //Using factory pattern here
    public static ProductItem createShoppingCart(String productId, String productName, String productCategory, String productManufacturer, String productPrice, int productStockOnhand, String productDescription, ProductState productState){
        return new ProductItem(productId, productName, productCategory, productManufacturer, productPrice, productStockOnhand, productDescription, productState);
    }

 */

}


