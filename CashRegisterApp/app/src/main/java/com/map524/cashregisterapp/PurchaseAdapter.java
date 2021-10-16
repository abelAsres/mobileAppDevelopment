package com.map524.cashregisterapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.map524.product.Product;
import com.map524.purchases.Purchase;

import java.util.ArrayList;

public class PurchaseAdapter extends BaseAdapter {
    ArrayList<Purchase> purchaseList;
    Context context;

    @Override
    public int getCount() {
        return purchaseList.size();
    }

    public PurchaseAdapter(Context context,ArrayList<Purchase> purchaseList) {
        this.purchaseList = purchaseList;
        this.context = context;
    }

    @Override
    public Object getItem(int position) {
        return purchaseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(null,"Trying to bind to product_item");
        if (convertView == null){
            //inflate layout to access (layout of the row) it
            convertView = LayoutInflater.from(context).inflate(R.layout.history_item,null);
        }

        TextView purchaseItem = convertView.findViewById(R.id.purchaseItemName);
        TextView purchaseQuantity = convertView.findViewById(R.id.purchaseQuantity);
        TextView purchaseTotal = convertView.findViewById(R.id.purchaseTotal);

        purchaseItem.setText(purchaseList.get(position).getItem());
        purchaseQuantity.setText(Integer.toString(purchaseList.get(position).getPurchaseQuantity()));
        purchaseTotal.setText(Double.toString(purchaseList.get(position).getPurchaseTotal()));

        return convertView;
    }
}
