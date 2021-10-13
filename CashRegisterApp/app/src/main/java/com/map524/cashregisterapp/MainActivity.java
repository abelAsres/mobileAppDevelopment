package com.map524.cashregisterapp;
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
        quantitySelected = (TextView) findViewById(R.id.totalPrice);
        productSelected = (TextView) findViewById(R.id.productName);
        purchaseTotal = (TextView) findViewById(R.id.quantityPruchase);

        quantitySelected.setText("Total");;
        productSelected.setText("Product");
        purchaseTotal.setText("Quantity");

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
        String clickedValue = numberButton.getText().toString();
        //
    }

    public void clear_button(View view) {
        quantitySelected.setText("Quantity");;
        productSelected.setText("Product");
        purchaseTotal.setText("0.00");
        selectedProduct=null;
    }

    public void buy_button(View view) {
        //check the quantity
        //adjust quantity if there is enough and message user about purchase
        if (selectedProduct.inStock(Integer.parseInt(quantitySelected.getText().toString()))) {
            selectedProduct.setQuantity(selectedProduct.getQuantity() - Integer.parseInt(quantitySelected.getText().toString()));
            //message the user regarding the purchase and clear sale from register
            clear_button(clearButton);
        }
        //display warning if not enough

        selectedProduct.setQuantity(selectedProduct.getQuantity() - Integer.parseInt(quantitySelected.getText().toString()));
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