package com.example.mattimoestechshop.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mattimoestechshop.CustomerBasket;
import com.example.mattimoestechshop.Model.Order;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import com.example.mattimoestechshop.*;

public class CustomerBasketAdapter extends RecyclerView.Adapter<CustomerBasketHolder>{

    private List<Order> listData;
    private CustomerBasket customerBasket;

    public CustomerBasketAdapter(List<Order> listData, CustomerBasket customerBasket) {
        this.listData = listData;
        this.customerBasket = customerBasket;
    }

    @Override
    public CustomerBasketHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(customerBasket);
        View itemView = layoutInflater.inflate(R.layout.shoppingcartitem, viewGroup, false);
        return new CustomerBasketHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerBasketHolder holder, int position) {

    }
    @Override
    public int getItemCount() {
        return listData.size();
    }

}