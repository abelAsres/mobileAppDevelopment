package com.map524.cashregisterapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.map524.purchases.Purchase;

import java.util.ArrayList;

public class History extends AppCompatActivity {

    PurchaseAdapter purchaseAdapter;
    ArrayList<Purchase> purchaseList;
    ListView purchases;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Intent intent_from_main = getIntent();
        purchaseList = intent_from_main.getParcelableArrayListExtra("purchases");

        purchaseAdapter = new PurchaseAdapter(this,purchaseList);
        purchases = findViewById(R.id.purchaseList);
        purchases.setAdapter(purchaseAdapter);

        purchases.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), SinglePurchase.class);
                intent.putExtra("singlePurchase", purchaseList.get(i));
                startActivity(intent);
            }
        });
    }
}
