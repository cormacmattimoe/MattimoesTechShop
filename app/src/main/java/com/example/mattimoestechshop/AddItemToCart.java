package com.example.mattimoestechshop;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AddItemToCart extends AppCompatActivity {

    EditText productName, manufacturer, category, price, quantity, description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additemtocart);


        productName = findViewById(R.id.etProductName);
        manufacturer = findViewById(R.id.etManufacturer);
        category = findViewById(R.id.etCategory);
        price = findViewById(R.id.etPrice);
        quantity = findViewById(R.id.etQuantity);
        description = findViewById(R.id.etDescription);
    }





}
