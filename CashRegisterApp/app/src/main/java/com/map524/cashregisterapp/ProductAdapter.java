package com.map524.cashregisterapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.map524.product.Product;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {
    ArrayList<Product> productList;
    Context context;

    public ProductAdapter( Context context,ArrayList<Product> productList) {
        this.productList = productList;
        this.context = context;
        Log.d("In: ", "ProductAdapter constructor");
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //This function will bind each element in productList to each row in ListView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(null,"Trying to bind to product_item");
        if (convertView == null){
            //inflate layout to access (layout of the row) it
            convertView = LayoutInflater.from(context).inflate(R.layout.product_item,null);
        }

        TextView productName = convertView.findViewById(R.id.product_name);
        TextView productPrice = convertView.findViewById(R.id.product_price);
        TextView productQuantity = convertView.findViewById(R.id.product_quantity);

        Log.d("productName",productList.get(position).getName());
        productName.setText(productList.get(position).getName());
        productPrice.setText(Double.toString(productList.get(position).getPrice()));
        productQuantity.setText(Float.toString(productList.get(position).getQuantity()));

        return convertView;
    }
}
