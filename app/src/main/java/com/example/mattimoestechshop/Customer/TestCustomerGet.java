package com.example.mattimoestechshop.Customer;

import com.example.mattimoestechshop.BuilderPatternCustomer.Customer;

public class TestCustomerGet {



    public static void main(String args[]){
        Customer currentCustomer;
        String emailId = "boblee@gmail.com";

        CustomerCreateObjectFromDb cob = new CustomerCreateObjectFromDb();
        currentCustomer = cob.createCustomerFromDb(emailId);

        System.out.println("This is customer" + currentCustomer.getCustomerName());
}
}
