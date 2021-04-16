package com.example.mattimoestechshop.BuilderPatternCustomer;

public class Customer {

    private String customerName;
    private String customerAddress;
    private String customerPhoneNumber;
    private String customerEmail;



    //Default PaymentMethod

    public Customer(){

    }
    private Customer(CustomerDetailsBuilder builder){
        this.customerName = builder.customerName;
        this.customerAddress = builder.customerAddress;
        this.customerPhoneNumber = builder.customerPhoneNumber;
        this.customerEmail = builder.customerEmail;
    }

    @Override
    public String toString() {
        return " Name = " + customerName + "\n Address = " + customerAddress + "\n Phone Number = " + customerPhoneNumber + "\n Email= " + customerEmail;
    }


    public Customer(String customerName, String customerAddress, String customerPhoneNumber, String customerEmail) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerEmail = customerEmail;
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

    public static class CustomerDetailsBuilder {
        private String customerName;
        private String customerAddress;
        private String customerPhoneNumber;
        private String customerEmail;
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
        public Customer build() {
            Customer customerobj = new Customer(this);
            return customerobj;
        }
    }
}


