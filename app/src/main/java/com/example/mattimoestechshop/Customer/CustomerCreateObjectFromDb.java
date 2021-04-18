package com.example.mattimoestechshop.Customer;

import androidx.annotation.NonNull;

import com.example.mattimoestechshop.BuilderPatternCustomer.Customer;
import com.example.mattimoestechshop.Model.ShoppingCart;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class CustomerCreateObjectFromDb {
    Customer currentCustomer;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    public Customer createCustomerFromDb(String emailId){
        db.collection("customers")
                .whereEqualTo("customerEmail", emailId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String custname = document.getString("customerName");
                                String customerEmail = document.getString("customerEmail");
                                String custAddress = document.getString("customerAddress");
                                String custPhone = document.getString("custPhoneNumber");

                                currentCustomer = new Customer();
                                currentCustomer.setCustomerName(custname);
                                currentCustomer.setCustomerEmail(customerEmail);
                                currentCustomer.setCustomerAddress(custAddress);
                                currentCustomer.setCustomerPhoneNumber(custPhone);
                                ShoppingCart cart = new ShoppingCart();
                                currentCustomer.setCustomerShoppingCart(cart);
                            }
                        }
                    }
                });
        return currentCustomer;
    }
}
