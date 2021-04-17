package com.example.mattimoestechshop.Customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mattimoestechshop.BuilderPatternCustomer.Customer;
import com.example.mattimoestechshop.Model.User;
import com.example.mattimoestechshop.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class CustomerCreateAccount extends AppCompatActivity {

    Button createBtn;
    EditText custName, custAddress, custNumber, custEmail;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    String emailAdId = currentUser.getEmail();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createcustomeraccount);

        custName = findViewById(R.id.custNameEd);
        custAddress =findViewById(R.id.custAddressEd);
        custNumber = findViewById(R.id.custNumberEd);
        custEmail = findViewById(R.id.custEmailEd);
        createBtn = findViewById(R.id.createAccBtn);

        final Query dbCall = db.collection("customers").whereEqualTo("userDetails", emailAdId);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
                Toast.makeText(CustomerCreateAccount.this, "Account created successfully ", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), CustomerHomePage.class));
            }
        });
    }

    public void createAccount() {
        String name = custName.getText().toString();
        String address = custAddress.getText().toString();
        String number = custNumber.getText().toString();
        String email = custEmail.getText().toString();

        Customer customerDetails = new Customer.CustomerDetailsBuilder()
                .addCustomerName(name)// required parameters
                .addCustomerAddress(address) // optional
                .addCustomerPhoneNumber(number) // optional
                .addCustomerEmail(email)
                .addUserDetails(emailAdId).build(); // to get back customer information
        db.collection("customers").document()
                .set(customerDetails);
    }



}