package com.example.mattimoestechshop.Authetication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mattimoestechshop.Admin.AdminHome;
import com.example.mattimoestechshop.BuilderPatternCustomer.Customer;
import com.example.mattimoestechshop.Customer.CustomerCreateAccount;
import com.example.mattimoestechshop.Customer.CustomerProductViewPage;
import com.example.mattimoestechshop.Model.Admin;

import com.example.mattimoestechshop.Model.ProductItem;
import com.example.mattimoestechshop.Model.ShoppingCart;
import com.example.mattimoestechshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;

public class UserLogin extends AppCompatActivity {
    EditText mEmail, mPassword;
    Button mLoginBtn;
    TextView mCreateBtn;
    String employeeRole;
    Customer currentCustomer;
    FirebaseAuth fAuth;
    // FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth.AuthStateListener mAuthStateListener;
    TextView title;
    ProgressBar progressBar;
    Admin admin;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    String emailAdId = currentUser.getEmail();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        // fAuth = FirebaseAuth.getInstance();
        mLoginBtn = findViewById(R.id.bLogin);
        mCreateBtn = findViewById(R.id.createAc);
        fAuth = FirebaseAuth.getInstance();


        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Autheticate user to Firebase
                final String email = mEmail.getText().toString().trim();
                final String password = mPassword.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is Required.");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required.");
                    return;
                }

                if (password.length() < 6) {
                    mPassword.setError("Password must be more than 6 Characters");
                    return;
                }
                if (password.contains(" ")) {
                    mPassword.setError("Password contains spaces and needs to be changed");
                    return;
                } else if (email.equals("admin123@gmail.com") && password.equals("admin123")) {

                    Intent d = new Intent(UserLogin.this, AdminHome.class);
                    startActivity(d);
                }

                // db call -- get userdetails out


                fAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            db.collection("customers").whereEqualTo("customerEmail", email).get()
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

                                                    Intent i = new Intent(UserLogin.this, CustomerProductViewPage.class);
                                                    i.putExtra("CustomerEmail",customerEmail);
                                                    startActivity(i);
                                                    Toast.makeText(UserLogin.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();

                                                    //Complete Current customer set all teh info based on db
                                                    // Shoppingcart cart = new ShoppingCart();
                                                    // currentCustomer.setShoppingCart(cart)
                                                    // then intent sends over current customer object
                                                    //
                                                 //   Intent intent = new Intent(getApplicationContext(),UserLogin.class);
                                                //    Bundle mBundle = new Bundle();
                                                //    mBundle.putSerializable("CurrentCustomer",currentCustomer);
                                               //     intent.putExtras(mBundle);
                                                //    startActivity(intent);
                                                    startActivity(new Intent(getApplicationContext(), CustomerProductViewPage.class));

                                                }
                                            }
                                        }
                                    });



                        } else {
                            Toast.makeText(UserLogin.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), UserLogin.class));
                        }
                    }
                });
            }
        });


        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserRegister.class));
            }
        });
    }
}
