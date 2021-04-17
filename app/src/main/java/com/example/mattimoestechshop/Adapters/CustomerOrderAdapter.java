package com.example.mattimoestechshop.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mattimoestechshop.Model.Order;
import com.example.mattimoestechshop.R;

import java.util.ArrayList;

public class CustomerOrderAdapter extends RecyclerView.Adapter<CustomerOrderAdapter.MyViewHolder> {
    ArrayList<Order> listOfOrders;
    public static final String MESSAGE_KEY1 = "text";
    public static final String MESSAGE_KEY2 = "position";

    public void addItemDecoration(DividerItemDecoration dividerItemDecoration) {
    }

    // Provide a reference to the views for each data item
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tvOrderNumber, tvOrderName, tvOrderQuantity, tvOrderPrice, tvOrderStatus;


        public MyViewHolder(View itemView) {
            super(itemView);
            tvOrderNumber = (TextView) itemView.findViewById(R.id.orderNumberTv);
            tvOrderName = (TextView) itemView.findViewById(R.id.orderNameTv);
            tvOrderQuantity = (TextView) itemView.findViewById(R.id.orderQuantityTv);
            tvOrderPrice = (TextView) itemView.findViewById(R.id.orderPriceTv);
            tvOrderStatus = (TextView) itemView.findViewById(R.id.orderStatusTv);




        }

        @Override
        public void onClick(View view) {
            int position = this.getLayoutPosition();
            String name = listOfOrders.get(position).getOrderNumber();
            //Intent intent = new Intent(view.getContext(), UpdateActivity.class );
            //intent.putExtra(MESSAGE_KEY1 ,name);
            //intent.putExtra(MESSAGE_KEY2, position);
            //view.getContext().startActivity(intent); //start activity from another activity, here we are in MyAdapter class,
            // need to call start from the activity within that viewholder

        }

    }


    // Provide the dataset to the Adapter
    public CustomerOrderAdapter(ArrayList<Order> myDataset) {
        listOfOrders = myDataset;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public CustomerOrderAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.orderitem, parent, false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String orderNumber = listOfOrders.get(position).getOrderNumber();
        final String orderName = listOfOrders.get(position).getOrderName();
        final String orderQuantity = listOfOrders.get(position).getOrderQuantity();

        holder.tvOrderNumber.setText(orderNumber);
        holder.tvOrderName.setText(orderNumber);
        holder.tvOrderQuantity.setText(orderQuantity);
        /*
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Item clicked", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(view.getContext(), bedDetails.class);
                intent.putExtra("Name", name);
                view.getContext().startActivity(intent); //start activity from another activity, here we are in MyAdapter class,
                // need to call start from the activity within that viewholder
            }
        });

         */

    }
    // Return the size of your dataset
    @Override
    public int getItemCount() {
        return listOfOrders.size();
    }


}