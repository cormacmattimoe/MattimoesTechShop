package com.example.mattimoestechshop.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import com.example.mattimoestechshop.*;

public class AdminHome extends AppCompatActivity {

    Button viewStock, viewCustomerDetails, viewOrders,addStock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        viewStock = findViewById(R.id.stockBtn);
        viewCustomerDetails = findViewById(R.id.viewCustomerDetails);
        viewOrders = findViewById(R.id.viewOrdersBtn);
        addStock = findViewById(R.id.addStockBtn);




        viewStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(AdminHome.this, AdminProductViewPage.class);
                startActivity(i);
            }
        });

        viewCustomerDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(AdminHome.this, AdminViewCustomerDetails.class);
                startActivity(i);
            }
        });

        viewOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(AdminHome.this, AdminProductViewPage.class);
                startActivity(i);

            }
        });
        addStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminHome.this, AdminAddItemToDatabase.class);
                startActivity(i);
            }
        });

    }
}