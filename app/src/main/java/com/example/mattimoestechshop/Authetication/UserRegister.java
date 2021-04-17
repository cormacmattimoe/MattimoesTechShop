package com.example.mattimoestechshop.Authetication;

import android.content.Intent;
import android.os.Bundle;
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

import com.example.mattimoestechshop.BuilderPatternCustomer.Customer;
import com.example.mattimoestechshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UserRegister extends AppCompatActivity {
    private static final String TAG = "Welcome";
    EditText mName, mEmail, mPassword,mAddress,mPhone;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String userID;
    Button mAdmin, mRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mName = findViewById(R.id.Name);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.password);
        mAddress = findViewById(R.id.addressEd);
        mPhone = findViewById(R.id.phoneNumberEd);
        mRegisterBtn = findViewById(R.id.signupButton);
        mLoginBtn = findViewById(R.id.alreadytv);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                final String fullName = mName.getText().toString();
                if (TextUtils.isEmpty(fullName)) {
                    mName.setError("Name is Required.");
                    return;
                }
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
                }

                // register the user in firebase
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            db.collection("users").document(fullName);

                            Toast.makeText(UserRegister.this, "You have been registered", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), UserLogin.class));
                        } else {
                            Toast.makeText(UserRegister.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                });
                // Create a new user with a , password, name and phonenumber
                Map<String, Object> user = new HashMap<>();
                user.put("Password", password);
                user.put("Name", fullName);

                // Add a new document with a generated ID
                db.collection("users").document(email)
                        .set(user)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(UserRegister.this, "New Customer created", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(UserRegister.this, "User already exists", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                String name = mName.getText().toString();
                String address = mAddress.getText().toString();
                String number = mPhone.getText().toString();
                String custemail = mEmail.getText().toString();

                Customer customerDetails = new Customer.CustomerDetailsBuilder()
                        .addCustomerName(name)// required parameters
                        .addCustomerAddress(address) // required parameters
                        .addCustomerPhoneNumber(number) // required parameters
                        .addCustomerEmail(custemail) //required parameters
                        .build(); // Build CustomerDetails
                db.collection("customers").document()
                        .set(customerDetails);
            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserLogin.class));
            }
        });
    }
}