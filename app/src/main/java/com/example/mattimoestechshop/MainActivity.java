package com.example.mattimoestechshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mattimoestechshop.Authetication.UserLogin;
import com.example.mattimoestechshop.Authetication.UserRegister;
import com.example.mattimoestechshop.Customer.CustomerProductDetails;
import com.example.mattimoestechshop.Customer.CustomerProductViewPage;


public class MainActivity extends AppCompatActivity {
    Button mAdmin, mAddItem, mRegister, mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Welcome to Mattimoes Tech Shop");
        setContentView(R.layout.activity_main);
        mAddItem = findViewById(R.id.addItem);
        mRegister = findViewById(R.id.reg);
        mLogin = findViewById(R.id.loginBtn);
        /*
        mAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CreateNewPatient.class));
            }
        });
        */
        mAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CustomerProductViewPage.class));
            }
        });

                /*
        mHospitalManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), hospitalmanagerhub.class));
            }
        });

        mPorter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), porterhub.class));
            }
        });


        mCleaning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), cleaningstaffhub.class));
            }
        });

         */



        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), UserRegister.class));
            }
        });
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserLogin.class));
            }
        });
    }
}