package com.example.mattimoestechshop.Admin;

import com.example.mattimoestechshop.Model.ProductItem;
import com.google.firebase.firestore.FirebaseFirestore;

public class AdminAddProductToDatabase {
    FirebaseFirestore db = FirebaseFirestore.getInstance();



    public void addProduct(Object productItem) {
        db.collection("products").document()
                .set(productItem);
    }
}
