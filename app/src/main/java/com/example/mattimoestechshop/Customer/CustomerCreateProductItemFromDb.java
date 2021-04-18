package com.example.mattimoestechshop.Customer;

import android.view.View;

import androidx.annotation.NonNull;

import com.example.mattimoestechshop.Model.ProductItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CustomerCreateProductItemFromDb {
    ProductItem tempStock;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public ProductItem retrieveProductItemSelected(String productName) {
        db.collection("products")
                .whereEqualTo("Product", productName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String tempProductName = document.getString("Product");
                                int tempProductPrice = document.getLong("Price").intValue();
                                String tempProductManufacturer = document.getString("Manufacturer");
                                int tempQuantity = document.getLong("Quantity").intValue();

                                tempStock = new ProductItem();
                                tempStock.setProductName(tempProductName);
                                tempStock.setProductPrice(tempProductPrice);
                                tempStock.setProductManufacturer(tempProductManufacturer);
                                tempStock.setProductStockOnhand(tempQuantity);
                            }

                        } else {
                            System.out.println("Wrong");
                        }
                    }
                });
        return tempStock;
    }
}
