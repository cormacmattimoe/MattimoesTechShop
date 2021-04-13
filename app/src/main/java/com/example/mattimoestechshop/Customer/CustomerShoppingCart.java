package com.example.mattimoestechshop.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mattimoestechshop.Admin.AdminAddItemToDatabase;
import com.example.mattimoestechshop.Admin.AdminHome;
import com.example.mattimoestechshop.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CustomerShoppingCart extends AppCompatActivity{

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    TextView totalAmount;
    Button checkoutBtn;
    RecyclerView rcvProducts;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customercart);

        rcvProducts = findViewById(R.id.rcvCart);
        checkoutBtn = findViewById(R.id.checkoutBtn);
        totalAmount = findViewById(R.id.totalAmountTx);
        rcvProducts.setHasFixedSize(true);
        rcvProducts.setLayoutManager(new LinearLayoutManager(this));

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CustomerShoppingCart.this, CustomerCheckout.class);
                startActivity(i);
            }
        });


    }
}