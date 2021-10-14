package com.map524.cashregisterapp;
import com.google.android.material.snackbar.Snackbar;
import com.map524.Calculator.Calculator;
import com.map524.product.Product;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Calculator calculator;


    Product productList[];
    Product selectedProduct;

    ListView products;

    TextView quantitySelected;
    TextView productSelected;
    TextView purchaseTotal;

    Button numberButton;
    Button buyButton;
    Button clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // intialize test views
        quantitySelected = (TextView) findViewById(R.id.quantityPruchase);
        productSelected = (TextView) findViewById(R.id.productName);
        purchaseTotal = (TextView) findViewById(R.id.totalPrice);

        quantitySelected.setText("Quantity");;
        productSelected.setText("Product");
        purchaseTotal.setText("Total");
        selectedProduct=new Product();

        //populate listview with products available
        products = (ListView) findViewById(R.id.productList);
        productList = new Product[]{new Product(),new Product(),new Product()};
        ArrayAdapter<Product> adapter =
                new ArrayAdapter<>(this,R.layout.product_item,R.id.product_text,productList);
        products.setAdapter(adapter);

        products.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedProduct=productList[i];
                Log.d("Product Name",selectedProduct.getName());
            }
        });
    }

    public void number_button_clicked (View view) {
        numberButton = (Button) view;
        if (quantitySelected.getText().equals("Quantity")){
            quantitySelected.setText("");
        }
        String clickedValue = numberButton.getText().toString();
        quantitySelected.setText(quantitySelected.getText() + clickedValue);
    }

    public void clear_button(View view) {
        quantitySelected.setText("Quantity");;
        productSelected.setText("Product");
        purchaseTotal.setText("Total");
        selectedProduct=null;
    }

    public void buy_button(View view) {
        //check if product has been selected
        if(selectedProduct.getName().equals("This is a test obj") || productSelected.getText().toString().equals("Product")){
            Log.d("check product selection fail","product not selected");
            buyButton = findViewById(R.id.buy);
            Snackbar.make(findViewById(R.id.buy), "Please select a product...", Snackbar.LENGTH_LONG).show();
        }
        //check is quantity to be purchased has been set
        else if (quantitySelected.getText().toString().equals("Quantity") || Integer.parseInt(quantitySelected.getText().toString()) == 0){
            Log.d("check quantity selection fail","quantity not selected");
            buyButton = findViewById(R.id.buy);
            Snackbar.make(findViewById(R.id.buy), "Please enter an amount to purchase...", Snackbar.LENGTH_LONG).show();
        }
        //if no errors change purchase item quantity and message user and reset cash register
        else {
            buyButton = findViewById(R.id.buy);
            selectedProduct.setQuantity(selectedProduct.getQuantity() - Integer.parseInt(quantitySelected.getText().toString()));
            Snackbar.make(buyButton, "thanks for the purchase", Snackbar.LENGTH_LONG).show();
            clear_button(clearButton);
        }
    }

    public void getTotal () {
        calculator.getTotal(Float.parseFloat(quantitySelected.getText().toString()),selectedProduct.getPrice());
    }
/*
    //Need to create intent
    Intent myIntent = new Intent(this,SecondActivity.class);
    startActivity
    //Create second activity

    //second activity should be in manifest

    //
*/
}