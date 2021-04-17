package com.example.mattimoestechshop.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.mattimoestechshop.R;

public class CustomerCheckoutPayPal extends AppCompatActivity {

    EditText customerEmail, customerPassword;
    Button buyButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_checkout_pay_pal);
        customerEmail = findViewById(R.id.emailEd);
        customerPassword = findViewById(R.id.passwordText);
        buyButton = findViewById(R.id.buyBtn);

    }
}