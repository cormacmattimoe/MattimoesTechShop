package com.example.mattimoestechshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mattimoestechshop.Model.StockItem;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AddItemToCart extends AppCompatActivity {


    EditText productName, productManufacturer, productCategory, productPrice, productQuantity, productDescription;
    ImageView imageView;
    Button btnAddImage, btnAddProduct;

    //Firebase
    FirebaseDatabase database;
    DatabaseReference stockList;
    StorageReference storageReference;
    FirebaseStorage storage;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    Uri saveUri;
    StockItem newItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additemtocart);

        //Firebase Init
        database = FirebaseDatabase.getInstance();

        productName = findViewById(R.id.etProductName);
        productManufacturer = findViewById(R.id.etManufacturer);
        productCategory = findViewById(R.id.etCategory);
        productPrice = findViewById(R.id.etPrice);
        productQuantity = findViewById(R.id.etQuantity);
        productDescription = findViewById(R.id.etDescription);
        btnAddProduct = findViewById(R.id.addBtn);


        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { {
                    addItem();
                Toast.makeText(AddItemToCart.this, "Product added successfully ", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),AddItemToCart.class));
                }
            }
        });
    }


    public void addItem() {
        String pn = productName.getText().toString();
        String manu = productManufacturer.getText().toString();
        String cat = productCategory.getText().toString();
        String desc = productDescription.getText().toString();
        String price = productPrice.getText().toString();
        Map<String, Object> newItem = new HashMap<>();
        newItem.put("Product", pn);
        newItem.put("Manufacturer", manu);
        newItem.put("Category", cat);
        newItem.put("Description", desc);
        newItem.put("Price", price);
        db.collection("products").document()
                .set(newItem);
    }

}
