package com.example.mattimoestechshop;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mattimoestechshop.R;

import com.example.mattimoestechshop.AddItemToCart;
import com.example.mattimoestechshop.UserRegister;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class UserLogin extends AppCompatActivity {
    EditText mEmail, mPassword;
    Button mLoginBtn;
    TextView mCreateBtn;
    String employeeRole;
    FirebaseAuth fAuth;
   // FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth.AuthStateListener mAuthStateListener;
    TextView title;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
       // fAuth = FirebaseAuth.getInstance();
        mLoginBtn = findViewById(R.id.bLogin);
        mCreateBtn = findViewById(R.id.createAc);
        fAuth = FirebaseAuth.getInstance();


        //checking if already logged in
        /*
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = fAuth.getCurrentUser();

                if(mFirebaseUser != null){
                    Toast.makeText(login.this, "You are logged in", Toast.LENGTH_SHORT).show();
                    db.collection("employees")
                                 .whereEqualTo("Email", email)
                                 .get()
                                 .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                     @Override
                                     public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                         if (task.isSuccessful()) {

                                             for (QueryDocumentSnapshot document : task.getResult()) {
                                                 employeeRole = document.getString("Role");
                                             }

                                             switch (employeeRole) {
                                                 case "Doctor":
                                                     Toast.makeText(login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                                     Intent myIntent = new Intent(login.this, doctorhub.class);
                                                     login.this.startActivity(myIntent);
                                                     break;
                                                 case "Porter":
                                                     Toast.makeText(login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                                     Intent i = new Intent(login.this, porterhub.class);
                                                     login.this.startActivity(i);
                                                     break;
                                                 case "Admin":
                                                     Toast.makeText(login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                                     Intent z = new Intent(login.this, CreateNewPatient.class);
                                                     login.this.startActivity(z);
                                                     break;
                                                 case "Cleaning Staff":
                                                     Toast.makeText(login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                                     Intent j = new Intent(login.this, cleaningstaffhub.class);
                                                     login.this.startActivity(j);
                                                     break;
                                                 case "Nurse":
                                                     Toast.makeText(login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                                     Intent v = new Intent(login.this, nursehub.class);
                                                     login.this.startActivity(v);
                                                     break;
                                                 case "Hospital Manager":
                                                     Toast.makeText(login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                                     Intent n = new Intent(login.this, hospitalmanagerhub.class);
                                                     login.this.startActivity(n);
                                                     break;

                                             }
                                         }
                                     }
                                 });

                }

                else{
                    Toast.makeText(LoginActivity.this, "Please Login", Toast.LENGTH_SHORT).show();
                }
            }
        };



         */


        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Autheticate user to Firebase
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
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
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {

                            Toast.makeText(UserLogin.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            Intent myIntent = new Intent(UserLogin.this, AddItemToCart.class);
                            UserLogin.this.startActivity(myIntent);
                        }

                        else {
                            Toast.makeText(UserLogin.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),UserLogin.class));

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