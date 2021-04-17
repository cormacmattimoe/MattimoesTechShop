package com.example.mattimoestechshop.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mattimoestechshop.Adapters.CustomerCartAdapter;
import com.example.mattimoestechshop.Adapters.ProductDetailsAdapter;
import com.example.mattimoestechshop.Admin.AdminAddItemToDatabase;
import com.example.mattimoestechshop.Admin.AdminHome;
import com.example.mattimoestechshop.Model.ProductItem;
import com.example.mattimoestechshop.R;
import com.example.mattimoestechshop.StrategyPattern.PayByCash;
import com.example.mattimoestechshop.StrategyPattern.PayByCreditCard;
import com.example.mattimoestechshop.StrategyPattern.PaybyPayPal;
import com.example.mattimoestechshop.StrategyPattern.PaymentMethodStrategy;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CustomerShoppingCart extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    TextView totalAmount;
    Button checkoutBtn;
    RecyclerView rcvProducts;
    CustomerCartAdapter cAdapter;
    ArrayList<ProductItem> viewCart = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ProductItem tempItem;
    Button buyProductBtn;
    int amount;
    int am;
    PaymentMethodStrategy paymentMethodStrategy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customercart);

        rcvProducts = findViewById(R.id.rcvCart);
        checkoutBtn = findViewById(R.id.checkoutBtn);
        totalAmount = findViewById(R.id.totalAmountTx);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rcvProducts.setLayoutManager(mLayoutManager);

        cAdapter = new CustomerCartAdapter(viewCart);
        //Add the divider line
        cAdapter.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rcvProducts.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rcvProducts.setHasFixedSize(true);


        rcvProducts.setAdapter(cAdapter);
        retrieveProductName();

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount = Integer.parseInt(totalAmount.getText().toString());
                if (amount > 500) {
                    paymentMethodStrategy = new PaybyPayPal();
                    paymentMethodStrategy.payForProduct(amount);
                    Intent i = new Intent(CustomerShoppingCart.this, CustomerCheckoutPayPal.class);
                    startActivity(i);
                    finish();

                } else if (amount < 500 && amount > 100) {
                    paymentMethodStrategy = new PayByCreditCard();
                    paymentMethodStrategy.payForProduct(amount);
                    Intent i = new Intent(CustomerShoppingCart.this, CustomerCheckout.class);
                    startActivity(i);
                    finish();
                } else {
                    paymentMethodStrategy = new PayByCash();
                    paymentMethodStrategy.payForProduct(amount);
                    Intent i = new Intent(CustomerShoppingCart.this, CustomerCheckout.class);
                    startActivity(i);
                    finish();
                }
                Toast.makeText(CustomerShoppingCart.this, "Successfully paid", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public ArrayList<ProductItem> retrieveProductName() {
        db.collection("products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String tempProductName = document.getString("Product");
                                String tempProductPrice = document.getString("Price");
                                String tempProductManufacturer = document.getString("Manufacturer");
                                int tempQuantity = document.getLong("Quantity").intValue();

                                tempItem = new ProductItem();
                                tempItem.setProductName(tempProductName);
                                tempItem.setProductPrice(tempProductPrice);
                                tempItem.setProductManufacturer(tempProductManufacturer);
                                tempItem.setProductStockOnhand(tempQuantity);
                                viewCart.add(tempItem);
                                cAdapter.notifyItemInserted(viewCart.size() - 1);
                            }

                        } else {
                            System.out.println("Wrong");
                        }
                    }
                });
        return viewCart;
    }
}