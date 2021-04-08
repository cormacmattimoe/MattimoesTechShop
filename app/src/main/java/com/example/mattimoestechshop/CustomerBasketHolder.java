package com.example.mattimoestechshop;


import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


public class CustomerBasketHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView customerBasketName, pricetxt;
    public ImageView basket_Image;

    public LinearLayout view_foreground;

    public void setCustomerBasketName(TextView customerBasketName){
        this.customerBasketName = customerBasketName;
    }

    public CustomerBasketHolder(View itemView) {
        super(itemView);

        customerBasketName = itemView.findViewById(R.id.basket_name);
        pricetxt = itemView.findViewById(R.id.basket_price);
        customerBasketName = itemView.findViewById(R.id.basket_image);
        view_foreground = itemView.findViewById(R.id.view_foreground);

    }

    @Override
    public void onClick(View view){

    }
}

