package com.example.mattimoestechshop.Model;



import java.util.ArrayList;
import java.util.List;

public class CustomerBasket {

    private String customerUsername;
    private String customerName;
    private String customerAddress;
    private String customerTotal;
    private String customerStatus;
    private ArrayList<Order> totalProducts;

    ArrayList<Order> shoppingCart = new ArrayList<>();

    public CustomerBasket() {

    }

    public CustomerBasket(String customerUsername, String customerName, String customerAddress, String customerTotal, String customerStatus, ArrayList<Order> totalProducts, ArrayList<Order> shoppingCart) {
        this.customerUsername = customerUsername;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerTotal = customerTotal;
        this.customerStatus = customerStatus;
        this.totalProducts = totalProducts;
        this.shoppingCart = shoppingCart;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerTotal() {
        return customerTotal;
    }

    public void setCustomerTotal(String customerTotal) {
        this.customerTotal = customerTotal;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public ArrayList<Order> getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(ArrayList<Order> totalProducts) {
        this.totalProducts = totalProducts;
    }

    public ArrayList<Order> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ArrayList<Order> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
