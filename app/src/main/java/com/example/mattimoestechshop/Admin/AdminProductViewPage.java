package com.example.mattimoestechshop.Admin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mattimoestechshop.Adapters.AdminProductAdapter;
import com.example.mattimoestechshop.Adapters.ProductAdapter;
import com.example.mattimoestechshop.Model.ProductItem;
import com.example.mattimoestechshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AdminProductViewPage extends AppCompatActivity {

    AdminProductAdapter adminPAdapter;
    RecyclerView rcvAllItems;
    ArrayList<ProductItem> viewAllProducts = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button searchProductNameBtn, searchProductManuBtn, addToCartBtn;
    EditText searchEd, searchMan;
    ProductItem productItem;
    TextView productState;

    // This the view screen for customer and admin to view all the products.
    // It calls ListOfProductsAndState

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhomepage);
        rcvAllItems = findViewById(R.id.rcvProducts);
        searchProductNameBtn = findViewById(R.id.searchProNameBtn);
        searchProductManuBtn = findViewById(R.id.searchManuBtn);
        searchEd = findViewById(R.id.searchProdNameEd);
        searchMan = findViewById(R.id.searchManuEd);
        productState = findViewById(R.id.tvProductState3);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rcvAllItems.setLayoutManager(mLayoutManager);

        adminPAdapter = new AdminProductAdapter(viewAllProducts);
        //Add the divider line
        adminPAdapter.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rcvAllItems.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rcvAllItems.setHasFixedSize(true);

        rcvAllItems.setAdapter(adminPAdapter);
        retrieveAllProducts();


        searchProductNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String productName = searchEd.getText().toString();
                // retrieveProductName(productName);
                retrieveProductName(productName);

            }
        });
        searchProductManuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String productManu = searchMan.getText().toString();
                // retrieveProductName(productName);
                retrieveProductManufacturer(productManu);

            }
        });
    }

    public ArrayList<ProductItem> retrieveAllProducts() {
        db.collection("products")
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
                                System.out.println("This the product state " + tempStock.getProductState() + " This is the no product available " + tempStock.getNoProductAvailable());
                                viewAllProducts.add(tempStock);
                                adminPAdapter.notifyItemInserted(viewAllProducts.size() - 1);
                                System.out.println("This is product = " + tempProductName + " Stock level = " + tempQuantity + " State = " + tempStock.getProductState());

                            }

                        } else {
                            System.out.println("Wrong");
                        }
                    }
                });
        return viewAllProducts;
    }

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
                                System.out.println("This the product state " + tempStock.getProductState() + " This is the no product available " + tempStock.getNoProductAvailable());
                                viewAllProducts.add(tempStock);
                                adminPAdapter.notifyItemInserted(viewAllProducts.size() - 1);
                                System.out.println("This is product = " + productName + " Stock level = " + tempQuantity + " State = " + tempStock.getProductState());

                            }

                        } else {
                            System.out.println("Wrong");
                        }
                    }
                });
        return viewAllProducts;
    }

    public ArrayList<ProductItem> retrieveProductManufacturer(final String productManufacturer ) {
        db.collection("products")
                .whereEqualTo("Manufacturer", productManufacturer)
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
                                System.out.println("This the product state " + tempStock.getProductState() + " This is the no product available " + tempStock.getNoProductAvailable());
                                viewAllProducts.add(tempStock);
                                adminPAdapter.notifyItemInserted(viewAllProducts.size() - 1);

                            }

                        } else {
                            System.out.println("Wrong");
                        }
                    }
                });
        return viewAllProducts;
    }
}