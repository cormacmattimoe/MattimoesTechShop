package com.example.mattimoestechshop.Adapters;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mattimoestechshop.Model.ProductItem;
import com.example.mattimoestechshop.R;

import java.util.ArrayList;

public class AdminProductDetailsAdapter extends RecyclerView.Adapter<AdminProductDetailsAdapter.MyViewHolder> {
    ArrayList<ProductItem> viewingAllProducts;
    public static final String MESSAGE_KEY1 = "text";
    public static final String MESSAGE_KEY2 = "position";
    ProductItem productItem;

    public void addItemDecoration(DividerItemDecoration dividerItemDecoration) {
    }

    // Provide a reference to the views for each data item
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tvProductName, tvProductPrice, tvProductManufacturer, tvProductState , tvProductQuantity,tvProductCategory, tvProductDescription;


        public MyViewHolder(View itemView) {
            super(itemView);
            tvProductName = (TextView) itemView.findViewById(R.id.nameEd);
            tvProductPrice = (TextView) itemView.findViewById(R.id.tvCustAddress);
            tvProductManufacturer = (TextView) itemView.findViewById(R.id.productManutxt);
            tvProductState = itemView.findViewById(R.id.tvProductState2);
            tvProductQuantity = itemView.findViewById(R.id.tvProductQuantity738);



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
    public AdminProductDetailsAdapter(ArrayList<ProductItem> myDataset) {
        viewingAllProducts = myDataset;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdminProductDetailsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                      int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.adminproductforsale, parent, false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String productName = viewingAllProducts.get(position).getProductName();
        final String productPrice = viewingAllProducts.get(position).getProductPrice();
        final String productManu = viewingAllProducts.get(position).getProductManufacturer();
        final int productQuan = viewingAllProducts.get(position).getProductStockOnhand();

        holder.tvProductName.setText(productName);
        holder.tvProductPrice.setText(productPrice);
        holder.tvProductManufacturer.setText(productManu);
        holder.tvProductQuantity.setText(String.valueOf(productQuan));

     /*
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Item clicked", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(view.getContext(), CustomerProductDetails.class);

                intent.putExtra("Name", productName);
                intent.putExtra("Price", productPrice);
                intent.putExtra("Manufacturer", productManu);

                view.getContext().startActivity(intent); //start activity from another activity, here we are in MyAdapter class,
                // need to call start from the activity within that viewholder
            }
        });

      */


    }
    // Return the size of your dataset
    @Override
    public int getItemCount() {
        return viewingAllProducts.size();
    }


}
