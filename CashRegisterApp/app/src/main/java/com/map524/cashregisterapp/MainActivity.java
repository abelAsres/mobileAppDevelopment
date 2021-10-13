package com.map524.cashregisterapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Need to create intent
    Intent myIntent = new Intent(this,SecondActivity.class);
    startActivity
    //Create second activity

    //second activity should be in manifest

    //

}