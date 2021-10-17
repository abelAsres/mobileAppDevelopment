package com.map524.cashregisterapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.map524.purchases.Purchase;

import java.util.ArrayList;

public class History extends AppCompatActivity {
    HistoryAdapter purchaseAdapter;
    RecyclerView recyclerList;
    ArrayList<Purchase> purchaseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        recyclerList = (RecyclerView) findViewById(R.id.purchaseList);



        if(getIntent().hasExtra("bundle")){
            Log.d(null,"Got the Intent");
            Bundle bundleFromManagerAcitivity = getIntent().getBundleExtra("bundle");
            purchaseList = bundleFromManagerAcitivity.getParcelableArrayList("purchases");
        }
        recyclerList.setLayoutManager(new LinearLayoutManager(this));
        purchaseAdapter = new HistoryAdapter(purchaseList, this, new HistoryAdapter.OnItemClickListener() {
            @Override
            public void OnHistoryItemClicked(Purchase item) {
                Intent intent = new Intent(getBaseContext(), SinglePurchase.class);
                int position = purchaseList.indexOf(item);
                Log.d(null, "OnHistoryItemClicked: "+ position);
                if (position > -1){
                    intent.putExtra("singlePurchase", purchaseList.get(position));
                    startActivity(intent);
                } else
                {
                    Snackbar.make(findViewById(R.id.history_button), "Error: cannot retrieve purchase...", Snackbar.LENGTH_LONG).show();
                }

            }
        });
        recyclerList.setAdapter(purchaseAdapter);
    }
}
/*

* */
