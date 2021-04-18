package com.example.mattimoestechshop;

import com.example.mattimoestechshop.BuilderPatternCustomer.Customer;

public class TestBuilderPatternCustomer {

    public static void main(String[] args) {
        Customer customerDetails = new Customer.CustomerDetailsBuilder()
                .addCustomerName("Cormac ")// required parameters
                .addCustomerAddress("Dublin") // optional
                .addCustomerPhoneNumber("08606984984") // optional
                .addCustomerEmail("cmay@gmail.com").build(); // to get back customer information
        System.out.println("Customer Name : " + customerDetails.getCustomerName() + "\n"
                + "Customer Address : " + customerDetails.getCustomerAddress() + "\n"
                + "Customer PhoneNumber : " + customerDetails.getCustomerPhoneNumber() +
                "\n" + "Customer Email : " + customerDetails.getCustomerEmail());
    }
    }
