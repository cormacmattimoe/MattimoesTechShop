package com.example.mattimoestechshop.Customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.example.mattimoestechshop.Adapters.ProductAdapter;
import com.example.mattimoestechshop.Authetication.UserLogin;
import com.example.mattimoestechshop.BuilderPatternCustomer.Customer;
import com.example.mattimoestechshop.Model.ProductItem;
import com.example.mattimoestechshop.Model.ShoppingCart;
import com.example.mattimoestechshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;

public class CustomerProductViewPage extends AppCompatActivity{

    ProductAdapter pAdapter;
    RecyclerView rcvAllItems;
    ArrayList<ProductItem> viewAllProducts = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button searchProductNameBtn, searchManuBtn, addToCartBtn;
    EditText searchEd, searchManuEd;
    ProductItem productItem;
    TextView productState;
    Customer currentCustomer;
    String custEmail;

    // This the view screen for customer and admin to view all the products.
    // It calls ListOfProductsAndState

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhomepage);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        rcvAllItems = findViewById(R.id.rcvProducts);
        searchProductNameBtn = findViewById(R.id.searchProNameBtn);
        searchManuBtn = findViewById(R.id.searchManuBtn);
        searchEd = findViewById(R.id.searchProdNameEd);
        searchManuEd =findViewById(R.id.searchManuEd);
        productState = findViewById(R.id.tvProductState3);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rcvAllItems.setLayoutManager(mLayoutManager);

        pAdapter = new ProductAdapter(viewAllProducts);
        //Add the divider line
        pAdapter.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rcvAllItems.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rcvAllItems.setHasFixedSize(true);

        rcvAllItems.setAdapter(pAdapter);

        Intent intent = getIntent();

        custEmail  = intent.getStringExtra("CustomerEmail");

            db.collection("customers").whereEqualTo("customerEmail", custEmail).get()
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
                                   ShoppingCart cart = new ShoppingCart();
                                   currentCustomer.setCustomerShoppingCart(cart);
                               }
                           }
                       }
                   });
                searchProductNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String productName = searchEd.getText().toString();
                // retrieveProductName(productName);
                retrieveProductName(productName);

            }
        });
        searchManuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String productManu = searchManuEd.getText().toString();

                // retrieveProductName(productName);
                retrieveProductManufacturer(productManu);

            }
        });
    }

    public ArrayList<ProductItem> retrieveProductName(final String productName ) {
        db.collection("products")
                .whereEqualTo("Product", productName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ProductItem tempStock = null;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String tempProductName = document.getString("Product");
                                int tempProductPrice = document.getLong("Price").intValue();
                                String tempProductManufacturer = document.getString("Manufacturer");
                                int tempQuantity = document.getLong("Quantity").intValue();

                                tempStock = new ProductItem();

                                tempStock.setProductName(tempProductName);
                                tempStock.setProductPrice(tempProductPrice);
                                tempStock.setProductManufacturer(tempProductManufacturer);
                                tempStock.setProductStockOnhand(tempQuantity);
                                System.out.println("This the product state " + tempStock.getProductState() + " This is the no product available " + tempStock.getNoProductAvailable());
                                if(tempStock.getProductState() == tempStock.getNoProductAvailable()){
                                    productState.setVisibility(View.VISIBLE);
                                }
                                viewAllProducts.add(tempStock);
                                pAdapter.notifyItemInserted(viewAllProducts.size() - 1);
                                System.out.println("This is product = " + productName + " Stock level = " + tempQuantity + " State = " + tempStock.getProductState());

                            }

                        } else {
                            System.out.println("Wrong");
                        }
                    }
                });
        return viewAllProducts;
    }
    public ArrayList<ProductItem> retrieveProductManufacturer(final String productManufacturer ) {
        viewAllProducts.clear();
        db.collection("products")
                .whereEqualTo("Manufacturer", productManufacturer)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ProductItem tempStock = null;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String tempProductName = document.getString("Product");
                                int tempProductPrice = document.getLong("Price").intValue();
                                String tempProductManufacturer = document.getString("Manufacturer");
                                int tempQuantity = document.getLong("Quantity").intValue();

                                tempStock = new ProductItem();

                                tempStock.setProductName(tempProductName);
                                tempStock.setProductPrice(tempProductPrice);
                                tempStock.setProductManufacturer(tempProductManufacturer);
                                tempStock.setProductStockOnhand(tempQuantity);
                                System.out.println("This the product state " + tempStock.getProductState() + " This is the no product available " + tempStock.getNoProductAvailable());
                                if(tempStock.getProductState() == tempStock.getNoProductAvailable()){
                                 //   productState.setVisibility(View.VISIBLE);
                                }
                                viewAllProducts.add(tempStock);
                                pAdapter.notifyItemInserted(viewAllProducts.size() - 1);

                            }

                        } else {
                            System.out.println("Wrong");
                        }
                        pAdapter.notifyDataSetChanged();
                    }
                });
        return viewAllProducts;
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
                Intent i = new Intent(CustomerProductViewPage.this, CustomerProductViewPage.class);
                startActivity(i);
                return true;
            case R.id.item2:
                Toast.makeText(getApplicationContext(), "Log out", Toast.LENGTH_LONG).show();
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent u = new Intent(CustomerProductViewPage.this, UserLogin.class);
                startActivity(u);

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}