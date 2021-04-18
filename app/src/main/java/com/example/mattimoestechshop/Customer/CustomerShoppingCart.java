package com.example.mattimoestechshop.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
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
import com.example.mattimoestechshop.Authetication.UserLogin;
import com.example.mattimoestechshop.BuilderPatternCustomer.Customer;
import com.example.mattimoestechshop.Model.ProductItem;
import com.example.mattimoestechshop.Model.ShoppingCart;
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
    ShoppingCart shoppingCart = new ShoppingCart();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ProductItem tempItem;
    Button buyProductBtn;
    int amount;
    int am;
    PaymentMethodStrategy paymentMethodStrategy;
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    String emailAdId = currentUser.getEmail();
    Customer currentCustomer;
    String customerId;
    String customerIdFromIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customercart);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        rcvProducts = findViewById(R.id.rcvCart);
        checkoutBtn = findViewById(R.id.checkoutBtn);
        totalAmount = findViewById(R.id.totalAmountTx);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rcvProducts.setLayoutManager(mLayoutManager);

        cAdapter = new CustomerCartAdapter(shoppingCart.getProductItems());
        //Add the divider line
        cAdapter.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rcvProducts.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rcvProducts.setHasFixedSize(true);
        Intent intent = getIntent();

        customerIdFromIntent  = intent.getStringExtra("CustomerId");

        rcvProducts.setAdapter(cAdapter);
        retrieveItemsFromShoppingCart();

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

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
                                ShoppingCart cart = new ShoppingCart();
                                currentCustomer.setCustomerShoppingCart(cart);
                            }
                        }
                    }
                });

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount = Integer.parseInt(totalAmount.getText().toString());
                if (amount > 500) {
                    paymentMethodStrategy = new PaybyPayPal();
                    paymentMethodStrategy.payForProduct(amount);
                    Intent i = new Intent(CustomerShoppingCart.this, CustomerCheckoutPayPal.class);
                    i.putExtra("ShoppingCart", shoppingCart.getProductItems());
                    i.putExtra("CustomerId", customerIdFromIntent);
                    startActivity(i);
                    finish();

                } else if (amount < 500 && amount > 100) {
                    paymentMethodStrategy = new PayByCreditCard();
                    paymentMethodStrategy.payForProduct(amount);
                    Intent i = new Intent(CustomerShoppingCart.this, CustomerCheckoutCreditCard.class);
                    i.putExtra("ShoppingCart", shoppingCart.getProductItems());
                    i.putExtra("CustomerId", customerIdFromIntent);
                    startActivity(i);
                    finish();
                } else {
                    paymentMethodStrategy = new PayByCash();
                    paymentMethodStrategy.payForProduct(amount);
                    Intent i = new Intent(CustomerShoppingCart.this, PayByCash.class);
                    i.putExtra("ShoppingCart", shoppingCart.getProductItems());
                    i.putExtra("CustomerId", customerIdFromIntent);
                    startActivity(i);
                    finish();
                }
                Toast.makeText(CustomerShoppingCart.this, "Successfully paid", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public ShoppingCart retrieveItemsFromShoppingCart() {
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
                                ShoppingCart cart = new ShoppingCart();
                                currentCustomer.setCustomerShoppingCart(cart);
                            }
                        }
                    }
                });


        db.collection("customers").document(customerIdFromIntent).collection("ShoppingCart")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String tempProductName = document.getString("Product");
                                int tempProductPrice = document.getLong("Price").intValue();
                                String tempProductManufacturer = document.getString("Manufacturer");
                                int tempQuantity = document.getLong("Quantity").intValue();

                                tempItem = new ProductItem();
                                tempItem.setProductName(tempProductName);
                                tempItem.setProductPrice(tempProductPrice);
                                tempItem.setProductManufacturer(tempProductManufacturer);
                                tempItem.setProductStockOnhand(tempQuantity);
                                shoppingCart.addProductItem(tempItem);
                                cAdapter.notifyItemInserted(shoppingCart.getNumberOfItems() - 1);
                            }
                            totalAmount.setText(String.valueOf(shoppingCart.getTotalOfGoods()));
                        } else {
                            System.out.println("Wrong");
                        }
                    }
                });
        return shoppingCart;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item1:
                Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_LONG).show();
                Intent i = new Intent(CustomerShoppingCart.this, CustomerProductViewPage.class);
                startActivity(i);
                return true;
            case R.id.item2:
                Toast.makeText(getApplicationContext(), "Log out", Toast.LENGTH_LONG).show();
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent u = new Intent(CustomerShoppingCart.this, UserLogin.class);
                startActivity(u);

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

