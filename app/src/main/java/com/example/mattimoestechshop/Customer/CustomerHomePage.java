package com.example.mattimoestechshop.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mattimoestechshop.R;

public class CustomerHomePage extends AppCompatActivity {

    Button customerBasket,viewProducts;
    EditText productNameSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);

        viewProducts = findViewById(R.id.viewProductsBtn);
        customerBasket = findViewById(R.id.viewMyBasket);

        viewProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(CustomerHomePage.this, CustomerProductViewPage.class);
                startActivity(i);
            }
        });

        customerBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(CustomerHomePage.this, CustomerShoppingCart.class);
                startActivity(i);

            }
        });
    }
}