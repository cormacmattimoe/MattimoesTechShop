package com.example.mattimoestechshop.BuilderPatternCustomer;

import com.example.mattimoestechshop.Model.Order;
import com.example.mattimoestechshop.Model.ProductItem;
import com.example.mattimoestechshop.Model.ShoppingCart;
import com.example.mattimoestechshop.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable{

    private String customerName;
    private String customerAddress;
    private String customerPhoneNumber;
    private String customerEmail;
    private String  userDetails;
    private ShoppingCart customerShoppingCart;
    private ArrayList<Order> orders;


    //Default PaymentMethod

    public Customer(){

    }

    private Customer(CustomerDetailsBuilder builder){
        this.customerName = builder.customerName;
        this.customerAddress = builder.customerAddress;
        this.customerPhoneNumber = builder.customerPhoneNumber;
        this.customerEmail = builder.customerEmail;
        this.userDetails = builder.userDetails;
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

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(String userDetails) {
        this.userDetails = userDetails;
    }

    public ShoppingCart getCustomerShoppingCart() {
        return customerShoppingCart;
    }

    public void setCustomerShoppingCart(ShoppingCart customerShoppingCart) {
        this.customerShoppingCart = customerShoppingCart;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public Customer(String customerName, String customerAddress, String customerPhoneNumber, String customerEmail, String userDetails, ShoppingCart customerShoppingCart, ArrayList<Order> orders) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerEmail = customerEmail;
        this.userDetails = userDetails;
        this.customerShoppingCart = customerShoppingCart;
        this.orders = orders;
    }

    public Customer(String customerName, String customerAddress, String customerPhoneNumber, String customerEmail, String userDetails) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerEmail = customerEmail;
        this.userDetails = userDetails;
    }


    public static class CustomerDetailsBuilder {
        private String customerName;
        private String customerAddress;
        private String customerPhoneNumber;
        private String customerEmail;
        private String userDetails;
        public CustomerDetailsBuilder addCustomerName(String name) {
            this.customerName = name;
            return this;
        }
        public CustomerDetailsBuilder addCustomerAddress(String address) {
            this.customerAddress = address;
            return this;
        }
        public CustomerDetailsBuilder addCustomerPhoneNumber(String phoneNumber) {
            this.customerPhoneNumber = phoneNumber;
            return this;
        }
        public CustomerDetailsBuilder addCustomerEmail(String customerEmail) {
            this.customerEmail = customerEmail;
            return this;
        }
        public CustomerDetailsBuilder addUserDetails(String userDetails) {
            this.userDetails = userDetails;
            return this;
        }
        public Customer build() {
            Customer customerobj = new Customer(this);
            return customerobj;
        }
    }
}


