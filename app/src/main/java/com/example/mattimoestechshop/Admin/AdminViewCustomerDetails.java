package com.example.mattimoestechshop.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.mattimoestechshop.Adapters.CustomerAdapter;
import com.example.mattimoestechshop.BuilderPatternCustomer.Customer;
import com.example.mattimoestechshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AdminViewCustomerDetails extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Customer> viewAllCustomers = new ArrayList<>();
    CustomerAdapter cAdapter;
    RecyclerView rcvCustomers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customer_details);
        rcvCustomers = findViewById(R.id.rcvCustomers);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        rcvCustomers.setLayoutManager(mLayoutManager);

        cAdapter = new CustomerAdapter(viewAllCustomers);
        //Add the divider line
        cAdapter.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rcvCustomers.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rcvCustomers.setHasFixedSize(true);

        rcvCustomers.setAdapter(cAdapter);
        retrieveCustomerDetails();
    }


    public ArrayList<Customer> retrieveCustomerDetails(){
        db.collection("customers")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            Customer tempCustomer = null;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String name = document.getString("customerName");
                                String address = document.getString("customerAddress");

                                tempCustomer = new Customer();
                                tempCustomer.setCustomerName(name);
                                tempCustomer.setCustomerAddress(address);
                                viewAllCustomers.add(tempCustomer);
                                cAdapter.notifyItemInserted(viewAllCustomers.size() - 1);
                            }

                        } else {
                            System.out.println("Wrong");
                        }
                    }
                });
        return viewAllCustomers;
    }
}