package com.example.mattimoestechshop;

import androidx.annotation.NonNull;

import com.example.mattimoestechshop.Model.ProductItem;
import com.example.mattimoestechshop.StatePattern.InStock;
import com.example.mattimoestechshop.StatePattern.OutOfStock;
import com.example.mattimoestechshop.StatePattern.ProductState;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ListOfProductsAndState {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<ProductItem> allProducts = new ArrayList<>();
    InStock inStock;
    OutOfStock outOfStock;
    Boolean state;
    ProductState sls;

    public ArrayList<ProductItem> retrieveProductName(final String productName ) {
        db.collection("products")
                .whereEqualTo("Product", productName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ProductItem tempStock = null;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String tempProductName = document.getString("Product");
                                String tempProductPrice = document.getString("Price");
                                String tempProductManufacturer = document.getString("Manufacturer");
                                int tempQuantity = document.getLong("Quantity").intValue();

                                tempStock = new ProductItem();

                                tempStock.setProductName(tempProductName);
                                tempStock.setProductPrice(tempProductPrice);
                                tempStock.setProductManufacturer(tempProductManufacturer);
                                tempStock.setProductStockOnhand(tempQuantity);
                                allProducts.add(tempStock);
                                System.out.println("This is product = " + productName + " Stock level = " + tempQuantity + " State = " + tempStock.getProductState());

                            }

                        } else {
                            System.out.println("Wrong");
                        }
                    }
                });
        return allProducts;
    }
}
