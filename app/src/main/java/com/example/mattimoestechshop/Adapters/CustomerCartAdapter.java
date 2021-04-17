package com.example.mattimoestechshop.Adapters;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mattimoestechshop.Customer.CustomerProductDetails;
import com.example.mattimoestechshop.Model.ProductItem;
import com.example.mattimoestechshop.R;

import java.util.ArrayList;

public class CustomerCartAdapter extends RecyclerView.Adapter<CustomerCartAdapter.MyViewHolder> {
    ArrayList<ProductItem> viewingAllProducts;
    public static final String MESSAGE_KEY1 = "text";
    public static final String MESSAGE_KEY2 = "position";
    String name;
    Intent intent;


    public void addItemDecoration(DividerItemDecoration dividerItemDecoration) {
    }

    // Provide a reference to the views for each data item
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tvProductName, tvProductPrice, tvProductManufacturer,tvProductQuantity, tvProductCategory, tvProductDescription, tvProductState;





        public MyViewHolder(View itemView) {
            super(itemView);
            tvProductName = (TextView) itemView.findViewById(R.id.nameEd);
            tvProductPrice = (TextView) itemView.findViewById(R.id.tvCustAddress);
            tvProductManufacturer = (TextView) itemView.findViewById(R.id.productManutxt);
            tvProductState = itemView.findViewById(R.id.tvProductState);
            tvProductQuantity = itemView.findViewById(R.id.quanTvProd);


        }

        @Override
        public void onClick(View view) {
            int position = this.getLayoutPosition();
            String name = viewingAllProducts.get(position).getProductName();
            //Intent intent = new Intent(view.getContext(), UpdateActivity.class );
            //intent.putExtra(MESSAGE_KEY1 ,name);
            //intent.putExtra(MESSAGE_KEY2, position);
            //view.getContext().startActivity(intent); //start activity from another activity, here we are in MyAdapter class,
            // need to call start from the activity within that viewholder

        }

    }


    // Provide the dataset to the Adapter
    public CustomerCartAdapter(ArrayList<ProductItem> myDataset) {
        viewingAllProducts = myDataset;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public CustomerCartAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,

                                                                 int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.productforsale, parent, false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final String productName = viewingAllProducts.get(position).getProductName();
        final String productPrice = viewingAllProducts.get(position).getProductPrice();
        final String productManu = viewingAllProducts.get(position).getProductManufacturer();
        final int productQuan = viewingAllProducts.get(position).getProductStockOnhand();

        holder.tvProductName.setText(productName);
        holder.tvProductPrice.setText(productPrice);
        holder.tvProductManufacturer.setText(productManu);
        holder.tvProductQuantity.setText(String.valueOf(productQuan));


    }
    // Return the size of your dataset
    @Override
    public int getItemCount() {
        return viewingAllProducts.size();
    }


}
