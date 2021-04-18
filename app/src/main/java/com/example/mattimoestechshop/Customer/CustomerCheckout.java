package com.example.mattimoestechshop.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mattimoestechshop.Authetication.UserLogin;
import com.example.mattimoestechshop.R;
import com.example.mattimoestechshop.StrategyPattern.PayByCash;
import com.example.mattimoestechshop.StrategyPattern.PayByCreditCard;
import com.example.mattimoestechshop.StrategyPattern.PaybyPayPal;
import com.example.mattimoestechshop.StrategyPattern.PaymentMethodStrategy;

public class CustomerCheckout extends AppCompatActivity {

    Button buyProductBtn;
    int amount;
    int am;
    EditText totalAmount;
    PaymentMethodStrategy paymentMethodStrategy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_checkout);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        buyProductBtn = findViewById(R.id.buyBtn);
        totalAmount = findViewById(R.id.amountTxt);




        buyProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                amount = Integer.parseInt(totalAmount.getText().toString());
                if (amount > 500) {
                    paymentMethodStrategy = new PaybyPayPal();
                    paymentMethodStrategy.payForProduct(amount);
                    startActivity(new Intent(getApplicationContext(), CustomerCheckoutPayPal.class));
                } else if (amount < 500 && amount > 100) {
                    paymentMethodStrategy = new PayByCreditCard();
                    paymentMethodStrategy.payForProduct(amount);
                    startActivity(new Intent(getApplicationContext(), CustomerCheckoutCreditCard.class));
                } else {
                    paymentMethodStrategy = new PayByCash();
                    paymentMethodStrategy.payForProduct(amount);
                    startActivity(new Intent(getApplicationContext(), CustomerCheckoutCash.class));
                }
                Toast.makeText(CustomerCheckout.this, "Successfully paid", Toast.LENGTH_SHORT).show();
            }
        });
    }
}