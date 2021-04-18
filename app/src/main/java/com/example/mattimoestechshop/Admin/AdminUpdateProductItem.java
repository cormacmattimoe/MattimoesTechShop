package com.example.mattimoestechshop.Admin;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mattimoestechshop.Adapters.AdminProductDetailsAdapter;
import com.example.mattimoestechshop.BuilderPatternCustomer.Customer;
import com.example.mattimoestechshop.Customer.CustomerCheckout;
import com.example.mattimoestechshop.Model.ProductItem;
import com.example.mattimoestechshop.Model.ShoppingCart;
import com.example.mattimoestechshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AdminUpdateProductItem extends AppCompatActivity {

    AdminProductDetailsAdapter pAdapter;
    TextView productState;
    RecyclerView rcvAllItems;
    ArrayList<ProductItem> adminItem = new ArrayList<>();
    ArrayList<ProductItem> addProductsToCart = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button updateAmountBtn;
    ProductItem productItem;
    int productQuantity;
    ProductItem tempStock;
    String name;
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    String emailAdId = currentUser.getEmail();
    String customerEmail;
    Customer currentCustomer;
    ShoppingCart cart = new ShoppingCart();
    String custId;
    EditText totalQuantity;
    String productId;
    int tempQuantity;
    int quanAfterAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update_product_item);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        rcvAllItems = findViewById(R.id.rcvProducts);
        updateAmountBtn = findViewById(R.id.btnUpdateQuan);
        totalQuantity = findViewById(R.id.updateQuanEd);

        Intent intent = getIntent();
        name = intent.getStringExtra("Name");


        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rcvAllItems.setLayoutManager(mLayoutManager);

        pAdapter = new AdminProductDetailsAdapter(adminItem);
        //Add the divider line
        pAdapter.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rcvAllItems.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rcvAllItems.setHasFixedSize(true);


        rcvAllItems.setAdapter(pAdapter);

        retrieveProductName();





     /*   if (prod == "") {
            state = noStock.stockLevelsState();
            Toast.makeText(ProductDetails.this, "Stock needs to be updated", Toast.LENGTH_LONG).show();
        }
        else {
            state = hasStock.stockLevelsState();
        }

      */

        updateAmountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String quantityString = totalQuantity.getText().toString();
                int quantityInput = Integer.parseInt(quantityString);
                quanAfterAdd = tempQuantity + quantityInput;
                db.collection("products").whereEqualTo("Product", name).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful())
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                productId = document.getId();
                                db.collection("products").document(productId).update("Quantity", quanAfterAdd);
                                Toast.makeText(AdminUpdateProductItem.this, "Product quantity updated successfully ", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), AdminProductViewPage.class));
                            }
                    }
                });
            }
        });

    }
    public ArrayList<ProductItem> retrieveProductName() {
        db.collection("products")
                .whereEqualTo("Product", name)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String tempProductName = document.getString("Product");
                                int tempProductPrice = document.getLong("Price").intValue();
                                String tempProductManufacturer = document.getString("Manufacturer");
                                tempQuantity = document.getLong("Quantity").intValue();

                                tempStock = new ProductItem();
                                tempStock.setProductName(tempProductName);
                                tempStock.setProductPrice(tempProductPrice);
                                tempStock.setProductManufacturer(tempProductManufacturer);
                                tempStock.setProductStockOnhand(tempQuantity);
                                adminItem.add(tempStock);
                                pAdapter.notifyItemInserted(adminItem.size() - 1);
                            }

                        } else {
                            System.out.println("Wrong");
                        }
                    }
                });
        return adminItem;
    }
}