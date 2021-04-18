
package com.example.mattimoestechshop.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.mattimoestechshop.BuilderPatternCustomer.Customer;
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

public class CustomerCheckoutCreditCard extends AppCompatActivity {
    EditText cardName, cardNumber, cardExpiry, cardCVV;
    Button buyBtn;
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    String emailAdId = currentUser.getEmail();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Customer currentCustomer;
    String custId;
    String customerId;
    ArrayList <ProductItem> shoppingCart = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_checkout_credit_card);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        cardName = findViewById(R.id.nameOnCardEd);
        cardNumber = findViewById(R.id.cardNumTv);
        cardExpiry = findViewById(R.id.cardExpiryEd);
        cardCVV = findViewById(R.id.cvvEd);
        buyBtn = findViewById(R.id.buyButton);


        Intent intent = getIntent();
        shoppingCart = (ArrayList<ProductItem>)getIntent().getSerializableExtra("ShoppingCart");
        customerId  = intent.getStringExtra("CustomerId");




        db.collection("customers")
                .whereEqualTo("customerEmail", emailAdId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String custname = document.getString("customerName");
                                String customerEmail = document.getString("customerEmail");
                                String custAddress = document.getString("customerAddress");
                                String custPhone = document.getString("custPhoneNumber");

                                currentCustomer = new Customer();
                                currentCustomer.setCustomerName(custname);
                                currentCustomer.setCustomerEmail(customerEmail);
                                currentCustomer.setCustomerAddress(custAddress);
                                currentCustomer.setCustomerPhoneNumber(custPhone);
                            }
                        }
                    }
                });

        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("customers").whereEqualTo("customerEmail", emailAdId)
                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful())
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                custId = document.getId();
                                db.collection("customers").document(custId)
                                        .collection("Order").document().set("order");
                            }
                    }
                });
            }
        });
    }
}

