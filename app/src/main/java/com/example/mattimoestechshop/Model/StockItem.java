package com.example.mattimoestechshop.Model;

public class StockItem {

    private String productName;
    private String productCategory;
    private String productManufacturer;
    private String productPrice;
    private String productQuantity;
    private String productDescription;
    private String productImage;

    public StockItem() {
    }

    public StockItem(String productName, String productCategory, String productManufacturer, String productPrice, String productQuantity, String productDescription, String productImage) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.productManufacturer = productManufacturer;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productDescription = productDescription;
        this.productImage = productImage;
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

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductDescription() {
        return getProductDescription();
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}


