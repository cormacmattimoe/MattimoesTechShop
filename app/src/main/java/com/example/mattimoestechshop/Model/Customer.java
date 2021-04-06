package com.example.mattimoestechshop.Model;

public class Customer {

    private String username;
    private String password;
    private String name;

    public Customer() {
    }

    public Customer(String password, String name) {
        this.password = password;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}