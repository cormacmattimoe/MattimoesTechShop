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
import com.example.mattimoestechshop.Model.Order;
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
import java.util.HashMap;
import java.util.Map;

public class CustomerCheckoutPayPal extends AppCompatActivity {

    EditText customerEmail, customerPassword;
    Button buyButton;
    String customerId;
    ArrayList<ProductItem> shoppingCart = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    ;
    String emailAdId = currentUser.getEmail();
    ArrayList<ProductItem> order = new ArrayList<ProductItem>();
    Customer currentCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_checkout_pay_pal);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        customerEmail = findViewById(R.id.emailEd);
        customerPassword = findViewById(R.id.passwordText);
        buyButton = findViewById(R.id.buyBtn);

        Intent intent = getIntent();
        shoppingCart = (ArrayList<ProductItem>) getIntent().getSerializableExtra("ShoppingCart");
        customerId = intent.getStringExtra("CustomerId");

        db.collection("customers").whereEqualTo("customerEmail", emailAdId).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                customerId = document.getId();
                                String custname = document.getString("customerName");
                                String customerEmail = document.getString("customerEmail");
                                String custAddress = document.getString("customerAddress");
                                String custPhone = document.getString("custPhoneNumber");

                                currentCustomer = new Customer();
                                currentCustomer.setCustomerName(custname);
                                currentCustomer.setCustomerEmail(customerEmail);
                                currentCustomer.setCustomerAddress(custAddress);
                                currentCustomer.setCustomerPhoneNumber(custPhone);
                                Order order = new Order();
                                currentCustomer.setOrderModel(order);
                            }
                        }
                    }
                });

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                order = shoppingCart;
                currentCustomer.setOrder(order);
                for (ProductItem p : order) {

                    Map<String, Object> orderItem = new HashMap<>();
                    orderItem.put("Product", p.getProductName());
                    orderItem.put("Manufacturer", p.getProductManufacturer());
                    orderItem.put("Category", p.getProductCategory());
                    orderItem.put("Description", p.getProductDescription());
                    orderItem.put("Price", p.getProductPrice());
                    orderItem.put("Quantity", p.getProductStockOnhand());
                    db.collection("customers").document(customerId)
                            .collection("Order").add(orderItem);
                    db.collection("customers").document(customerId)
                            .collection("ShoppingCart").document().delete();


                }
            }
        });
    }
}