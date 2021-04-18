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

import com.example.mattimoestechshop.Adapters.ProductDetailsAdapter;
import com.example.mattimoestechshop.Admin.AdminAddProductToDatabase;
import com.example.mattimoestechshop.Authetication.UserLogin;
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
import java.util.HashMap;
import java.util.Map;

public class CustomerProductDetails extends AppCompatActivity {

    ProductDetailsAdapter pAdapter;
    TextView productState;
    RecyclerView rcvItem;
    ArrayList<ProductItem> shoppingItem = new ArrayList<>();
    ArrayList<ProductItem> addProductsToCart = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button addToCartBtn;
    int productQuantity;
    ProductItem tempStock;
    String name;
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    String emailAdId = currentUser.getEmail();
    String customerEmail;
    Customer currentCustomer;
    ShoppingCart cart = new ShoppingCart();
    String custId;
    EditText totalQuantity;
    String productId;
    int tempQuantity;
    int quanAfterAdd;
    int quantityInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showproductdetails);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        rcvItem = findViewById(R.id.rcvProducts);
        addToCartBtn = findViewById(R.id.btnUpdateQuan);
        productState = findViewById(R.id.productStateDet);
        totalQuantity = findViewById(R.id.updateQuanEd);

        final Intent intent = getIntent();
        name = intent.getStringExtra("Name");


        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rcvItem.setLayoutManager(mLayoutManager);

        pAdapter = new ProductDetailsAdapter(shoppingItem);
        //Add the divider line
        pAdapter.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rcvItem.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rcvItem.setHasFixedSize(true);


        rcvItem.setAdapter(pAdapter);

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
                                ShoppingCart cart = new ShoppingCart();
                                currentCustomer.setCustomerShoppingCart(cart);
                            }
                        }
                    }
                });
        retrieveProductName();
        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(tempQuantity == 0){
                    Toast.makeText(CustomerProductDetails.this, "This Product is out of stock", Toast.LENGTH_SHORT).show();
                }else {

                    //   ArrayList<ProductItem> addProductsToCart = (ArrayList<ProductItem>)viewAllProducts.clone();
                    //  shoppingItem.add(tempStock);
                    //    System.out.println("This is your cart" + shoppingItem.toString());

                    cart = currentCustomer.getCustomerShoppingCart();
                    cart.addProductItem(tempStock);
                    String quantityString = totalQuantity.getText().toString();
                    if (quantityString.isEmpty()) {
                        Toast.makeText(CustomerProductDetails.this, "Please enter amount", Toast.LENGTH_SHORT).show();
                    } else {
                        quantityInput = Integer.parseInt(quantityString);
                        quanAfterAdd = tempQuantity - quantityInput;
                        tempStock.setProductStockOnhand(quanAfterAdd);

                        db.collection("products").whereEqualTo("Product", name).get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful())
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        productId = document.getId();
                                        db.collection("products").document(productId).update("Quantity", quanAfterAdd);
                                    }
                            }
                        });
                    }
                }
                System.out.println("This is your cart  as customer" + currentCustomer.toString());
                System.out.println("This is your cart as carting" + cart.toString());
                db.collection("customers").whereEqualTo("customerEmail", emailAdId)
                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful())
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                custId = document.getId();
                                Map<String, Object> newCartItem = new HashMap<>();
                                newCartItem.put("Product", tempStock.getProductName());
                                newCartItem.put("Manufacturer", tempStock.getProductManufacturer());
                                newCartItem.put("Category", tempStock.getProductCategory());
                                newCartItem.put("Description", tempStock.getProductDescription());
                                newCartItem.put("Price", tempStock.getProductPrice());
                                newCartItem.put("Quantity", quantityInput);
                                db.collection("customers").document(custId)
                                        .collection("ShoppingCart").add(newCartItem);
                //               db.collection("customers").document(custId).update("customerShoppingCart", cart);;

                                Intent i = new Intent(CustomerProductDetails.this, CustomerShoppingCart.class);
                                i.putExtra("CustomerId",custId);
                                startActivity(i);
                            }
                    }
                });

            }
        });
    }
    public ArrayList<ProductItem> retrieveProductName() {
        db.collection("products")
                .whereEqualTo("Product", name)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String tempProductName = document.getString("Product");
                                int tempProductPrice = document.getLong("Price").intValue();
                                String tempProductManufacturer = document.getString("Manufacturer");
                                tempQuantity = document.getLong("Quantity").intValue();

                                tempStock = new ProductItem();
                                tempStock.setProductName(tempProductName);
                                tempStock.setProductPrice(tempProductPrice);
                                tempStock.setProductManufacturer(tempProductManufacturer);
                                tempStock.setProductStockOnhand(tempQuantity);
                                if(tempStock.getProductState() == tempStock.getNoProductAvailable()){
                                    productState.setVisibility(View.VISIBLE);
                                }
                                shoppingItem.add(tempStock);
                                pAdapter.notifyItemInserted(shoppingItem.size() - 1);
                            }

                        } else {
                            System.out.println("Wrong");
                        }
                    }
                });
        return shoppingItem;
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
                Intent i = new Intent(CustomerProductDetails.this, CustomerProductViewPage.class);
                startActivity(i);
                return true;
            case R.id.item2:
                Toast.makeText(getApplicationContext(), "Log out", Toast.LENGTH_LONG).show();
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent u = new Intent(CustomerProductDetails.this, UserLogin.class);
                startActivity(u);

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}