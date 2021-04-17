package com.example.mattimoestechshop.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mattimoestechshop.BuilderPatternCustomer.Customer;
import com.example.mattimoestechshop.Model.ProductItem;
import com.example.mattimoestechshop.R;

import java.util.ArrayList;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder> {
    ArrayList<Customer> viewAllCustomers;
    public static final String MESSAGE_KEY1 = "text";
    public static final String MESSAGE_KEY2 = "position";
    ProductItem productItem;

    public void addItemDecoration(DividerItemDecoration dividerItemDecoration) {
    }

    // Provide a reference to the views for each data item
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView customerName, tvcustomerAddress, tvProductManufacturer, tvProductState , tvProductCategory, tvProductDescription;


        public MyViewHolder(View itemView) {
            super(itemView);
            customerName = (TextView) itemView.findViewById(R.id.nameEd);
            tvcustomerAddress = (TextView) itemView.findViewById(R.id.tvCustAddress);


        }

        @Override
        public void onClick(View view) {
            int position = this.getLayoutPosition();
            //Intent intent = new Intent(view.getContext(), UpdateActivity.class );
            //intent.putExtra(MESSAGE_KEY1 ,name);
            //intent.putExtra(MESSAGE_KEY2, position);
            //view.getContext().startActivity(intent); //start activity from another activity, here we are in MyAdapter class,
            // need to call start from the activity within that viewholder

        }

    }


    // Provide the dataset to the Adapter
    public CustomerAdapter(ArrayList<Customer> myDataset) {
        viewAllCustomers = myDataset;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public CustomerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.customerdetails, parent, false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String name = viewAllCustomers.get(position).getCustomerName();
        final String address = viewAllCustomers.get(position).getCustomerAddress();

        holder.customerName.setText(name);
        holder.tvcustomerAddress.setText(address);
        /*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Item clicked", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(view.getContext(), ProductDetails.class);

              //  intent.putExtra("ProductName", productName);
                view.getContext().startActivity(intent); //start activity from another activity, here we are in MyAdapter class,
                // need to call start from the activity within that viewholder
            }
        });

         */


    }

    @Override
    public int getItemCount() {
        return viewAllCustomers.size();
    }


}
