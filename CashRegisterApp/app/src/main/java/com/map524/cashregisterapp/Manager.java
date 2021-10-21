package com.map524.cashregisterapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.map524.product.Product;
import com.map524.purchases.Purchase;

import java.util.ArrayList;

public class Manager extends AppCompatActivity {

    Button history_button;
    ArrayList<Purchase> purchaseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);


        Log.d("ManagerClass","onCreate");
        history_button = findViewById(R.id.history_button);

        if (savedInstanceState != null) {
            Log.d("ManagerClass","retrieving save");
            Log.d("ManagerClass","savedInstance found");
            purchaseList = savedInstanceState.getParcelableArrayList("listOfPurchases");
        }

        if(getIntent().hasExtra("purchases")){
            Log.d("ManagerClass","Got an INTENT");
            Intent intent_from_main = getIntent();
            purchaseList = intent_from_main.getParcelableArrayListExtra("purchases");
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d("ManagerClass","onSave");
        outState.putParcelableArrayList("listOfPurchases",purchaseList);
        super.onSaveInstanceState(outState);
    }

    public void history_button_clicked (View view){
        Intent intent = new Intent(this,History.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("purchases",purchaseList);
        Log.d("clicked history button: ",purchaseList.size()+"");
        intent.putExtra("bundle",bundle);
        startActivity(intent);
    }
}