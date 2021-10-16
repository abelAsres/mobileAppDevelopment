package com.map524.cashregisterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.map524.purchases.Purchase;

public class SinglePurchase extends AppCompatActivity {

    Purchase purchase;

    TextView puchaseItem;
    TextView purchaseTotal;
    TextView purchaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_purchase);

        Intent intent_from_history = getIntent();
        purchase = intent_from_history.getParcelableExtra("singlePurchase");

        puchaseItem = findViewById(R.id.singlePurchaseItem);
        purchaseTotal = findViewById(R.id.singlePurchaseTotal);
        purchaseDate = findViewById(R.id.singlePurchaseDate);

        puchaseItem.setText(purchase.getItem());
        purchaseTotal.setText((Double.toString(purchase.getPurchaseTotal())));
        purchaseDate.setText(purchase.getDate().toString());

    }
}