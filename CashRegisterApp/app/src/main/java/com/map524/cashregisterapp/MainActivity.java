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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ProductAdapter productAdapter;
    ArrayList<Product> productList;
    Product selectedProduct;
    ListView products;

    Calculator calculator;

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

        calculator = new Calculator();

        //populate listview with products available

        productList = new ArrayList<Product>();
        productList.add(new Product("this is product "+1,1,1));
        productList.add(new Product("this is product "+2,2,2));
        productList.add(new Product("this is product "+3,3,3));
        productList.add(new Product("this is product "+4,4,4));

        for(Product product : productList){
            Log.d("Product: ", product.getName());
        }

        productAdapter = new ProductAdapter(this,productList);
        products = (ListView) findViewById(R.id.productList);
        products.setAdapter(productAdapter);

        products.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedProduct=productList.get(i);
                Log.d("Product Name",selectedProduct.getName());
                productSelected.setText(selectedProduct.getName());
            }
        });
     /*   ArrayAdapter<Product> adapter =
                new ArrayAdapter<>(this,R.layout.product_item,R.id.product_text,productList);
        products.setAdapter(adapter);

        products.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedProduct=productList.get(i);
                Log.d("Product Name",selectedProduct.getName());
            }
        });*/
    }

    public void number_button_clicked (View view) {
        numberButton = (Button) view;
        String clickedValue = numberButton.getText().toString();
        float quantity;
        //set quantity to be purchased
        if(quantitySelected.getText().equals("Quantity")){
            quantitySelected.setText(clickedValue);
            quantity = Float.parseFloat(clickedValue);
        }else{
            quantitySelected.setText(quantitySelected.getText() + clickedValue);
            quantity =  Float.parseFloat(quantitySelected.getText().toString());
        }

        //save number clicked on pad and display, if out of stock display warning
        if(selectedProduct.getQuantity() < quantity){
            Snackbar.make(findViewById(R.id.buy), "Sorry not enough in stock...", Snackbar.LENGTH_LONG).show();
            quantitySelected.setText(quantitySelected.getText().toString().substring(0,quantitySelected.getText().toString().length() - 1));
        }else{
            getTotal();
        }
    }

    public void clear_button(View view) {
        quantitySelected.setText("Quantity");
        productSelected.setText("Product");
        purchaseTotal.setText("Total");
        selectedProduct=null;
    }

    public void buy_button(View view) {

        //check if product has been selected
        if( productSelected.getText().toString().equals("Product") || selectedProduct.getName().equals("This is a test obj")){
            Snackbar.make(findViewById(view.getId()), "Please select a product...", Snackbar.LENGTH_LONG).show();
        }
        //check is quantity to be purchased has been set
        else if (quantitySelected.getText().toString().equals("Quantity") || Integer.parseInt(quantitySelected.getText().toString()) == 0){
            Snackbar.make(findViewById(view.getId()), "Please enter an amount to purchase...", Snackbar.LENGTH_LONG).show();
        }
        //if no errors change purchase item quantity and message user and reset cash register
        else {
            selectedProduct.setQuantity(selectedProduct.getQuantity() - Float.parseFloat(quantitySelected.getText().toString()));
            Snackbar.make(findViewById(view.getId()), "thanks for the purchase", Snackbar.LENGTH_LONG).show();
            clear_button(clearButton);
        }
    }

    public void getTotal () {
        //calculate the total price based on current quantity selection and display
        Float totalPrice = calculator.getTotal(Float.parseFloat(quantitySelected.getText().toString()),
                selectedProduct.getPrice());
        Log.d("totalPrice: ",totalPrice.toString());
        Log.d("selectedQuantity: ",quantitySelected.getText().toString());
        Log.d("productPrice: ",Float.toString(selectedProduct.getPrice()));
        purchaseTotal.setText(totalPrice.toString());
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