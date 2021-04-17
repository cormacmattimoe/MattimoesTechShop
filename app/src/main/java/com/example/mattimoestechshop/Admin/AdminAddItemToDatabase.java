package com.example.mattimoestechshop.Admin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mattimoestechshop.Model.ProductItem;
import com.example.mattimoestechshop.R;
import com.example.mattimoestechshop.StatePattern.ProductState;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class AdminAddItemToDatabase extends AppCompatActivity {


    EditText productName, productManufacturer, productCategory, productPrice, productQuantity, productDescription;
    ImageView imageView;
    Button btnAddImage, btnAddProduct;
    int numberOfStock;

    //Firebase
    FirebaseDatabase database;
    DatabaseReference stockList;
    StorageReference storageReference;
    FirebaseStorage storage;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ProductItem productItem;
    ProductState productState;

    Uri saveUri;
    ProductItem newItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additemtocart);

        //Firebase Init
        database = FirebaseDatabase.getInstance();

        productName = findViewById(R.id.productNameEd);
        productManufacturer = findViewById(R.id.productManEd);
        productCategory = findViewById(R.id.productCatEd);
        productPrice = findViewById(R.id.productPriceEd);
        productQuantity = findViewById(R.id.productQuantityEd);
        productDescription = findViewById(R.id.productDescEd);
        btnAddProduct = findViewById(R.id.addBtn);


        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    getProductInformationFromScreen();
                    Toast.makeText(AdminAddItemToDatabase.this, "Product added successfully ", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), AdminAddItemToDatabase.class));
                }
            }
        });
    }


    public void getProductInformationFromScreen() {
        String pn = productName.getText().toString();
        String manu = productManufacturer.getText().toString();
        String cat = productCategory.getText().toString();
        String quantityString = productQuantity.getText().toString();
        int quantityAdded = Integer.parseInt(quantityString);
        String desc = productDescription.getText().toString();
        String price = productPrice.getText().toString();
        productItem = new ProductItem();
        productItem.setProductName(pn);
        productItem.setProductManufacturer(manu);
        productItem.setProductCategory(cat);
        productItem.setProductStockOnhand(quantityAdded);
        productItem.setProductDescription(desc);
        productItem.setProductPrice(price);
        productItem.resupplyProduct(quantityAdded);
        Map<String, Object> newItem = new HashMap<>();
        newItem.put("Product", pn);
        newItem.put("Manufacturer", manu);
        newItem.put("Category", cat);
        newItem.put("Description", desc);
        newItem.put("Price", price);
        newItem.put("Quantity", quantityAdded);
        AdminAddProductToDatabase ad  = new AdminAddProductToDatabase();
        ad.addProduct(newItem);
        System.out.println("This is the product state after write to db " + productItem.getProductState());


    }

}
