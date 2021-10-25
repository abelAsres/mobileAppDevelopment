package com.map524.cashregisterapp;
import com.google.android.material.snackbar.Snackbar;
import com.map524.Calculator.Calculator;
import com.map524.product.Product;
import com.map524.purchases.Purchase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ProductAdapter productAdapter;
    ArrayList<Product> productList;
    Product selectedProduct;
    ListView products;

    ArrayList<Purchase>purchaseList;

    Calculator calculator;

    TextView quantitySelected;
    TextView productSelected;
    TextView purchaseTotal;

    Button numberButton;
    Button clearButton;

    AlertDialog.Builder builder;

    SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd hh:mm:ss 'EDT' yyyy");//formating according to my need

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MainActivityClass","onCreate");
        if (savedInstanceState == null) {
            Log.d("savedInstanceState", "there was nothing saved");
            //populate listview with products available
            purchaseList = new ArrayList<>(1);
            productList = new ArrayList<>();
            productList.add(new Product("Pants",11.99,100));
            productList.add(new Product("Shoes",25.99,200));
            productList.add(new Product("Hats",5.99,15));
            productList.add(new Product("Shirts",4.99,20));
        }else {
            Log.d("savedInstanceState","recieved package");
            productList = savedInstanceState.getParcelableArrayList("listOfProducts");
            purchaseList = savedInstanceState.getParcelableArrayList("listOfPurchases");
        }

        // initialize
        quantitySelected = findViewById(R.id.quantityPruchase);
        productSelected = findViewById(R.id.productName);
        purchaseTotal = findViewById(R.id.totalPrice);

        quantitySelected.setText(R.string.quantity_view);
        productSelected.setText(R.string.product_view);
        purchaseTotal.setText(R.string.total_view);
        selectedProduct=new Product();

        calculator = new Calculator();

        builder = new AlertDialog.Builder(this);

        productAdapter = new ProductAdapter(this,productList);
        products = findViewById(R.id.productList);
        products.setAdapter(productAdapter);

        products.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedProduct=productList.get(i);
                Log.d("Product Name",selectedProduct.getName());
                productSelected.setText(selectedProduct.getName());
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(null, "pausing main activity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(null, "resuming main activity");
        for (Product product : productList){
            Log.d(product.getName(),Integer.toString(product.getQuantity()));
        }
        productAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(null, "starting main activity");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivityClass", "destroying main activity");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Log.d("MainActivityClass","onSave");
        outState.putParcelableArrayList("listOfProducts", productList);
        outState.putParcelableArrayList("listOfPurchases",purchaseList);

        super.onSaveInstanceState(outState);

    }

    // This callback is called only when there is a saved instance that is previously saved by using
    // onSaveInstanceState(). We restore some state in onCreate(), while we can optionally restore
    // other state here, possibly usable after onStart() has completed.
    // The savedInstanceState Bundle is same as the one used in onCreate().
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
       Log.d("MainActivityClass","onRestore");
    }

    public void save_purchase (){
        Log.d(null,"trying to save purchase");
        Purchase purchase = new Purchase(selectedProduct.getName(),
                Double.parseDouble(purchaseTotal.getText().toString()),
                Integer.parseInt(quantitySelected.getText().toString()),
                formatter.format(Calendar.getInstance().getTime()));
        purchaseList.add(purchase);
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

        //Ensure a product has been selected
        if (productSelected.getText().equals("Product")){
            Snackbar.make(findViewById(R.id.buy), "Please select a product...", Snackbar.LENGTH_LONG).show();
            backspace();
            quantitySelected.setText(R.string.quantity_view);
        }
        //save number clicked on pad and display, if out of stock display warning
        else if(selectedProduct.getQuantity() < quantity){
            Snackbar.make(findViewById(R.id.buy), "Sorry not enough in stock...", Snackbar.LENGTH_LONG).show();
            backspace();
        }else{
            getTotal();
        }
    }

    public void manager_button_clicked(View view) {
        //Log.d("buy button purchase date",purchaseList.get(0).getDateOfPurchase());
        Intent intent = new Intent(this,Manager.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("purchases",purchaseList);
        Log.d("clicked history button: ",purchaseList.size()+"");
        intent.putExtra("bundle",bundle);
        startActivity(intent);
    }

    public void backspace(){
        quantitySelected.setText(quantitySelected.getText().toString().substring(0,quantitySelected.getText().toString().length() - 1));
    }

    public void clear_button_clicked(View view) {
        quantitySelected.setText(R.string.quantity_view);
        productSelected.setText(R.string.product_view);
        purchaseTotal.setText(R.string.total_view);
        selectedProduct=null;
    }

    public void buy_button_clicked(View view) {
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
            selectedProduct.setQuantity(selectedProduct.getQuantity() - Integer.parseInt(quantitySelected.getText().toString()));

            int indexOfProduct = productList.indexOf(selectedProduct);

            if (indexOfProduct != -1) {
                productList.get(indexOfProduct).setQuantity(selectedProduct.getQuantity());
                productAdapter.notifyDataSetChanged();
            }

            Log.d("made Purchase","just about to create buidler");
            builder.create();
            builder.setTitle("Thank you for your purchase");
            builder.setMessage("Your purchase is "+quantitySelected.getText().toString()+" for $"+ purchaseTotal.getText().toString());
            builder.show();

            save_purchase();
            //Snackbar.make(findViewById(view.getId()), "thanks for the purchase", Snackbar.LENGTH_LONG).show();
            clear_button_clicked(clearButton);
        }
    }

    public void getTotal () {
        //calculate the total price based on current quantity selection and display
        Double totalPrice = calculator.getTotal(Double.parseDouble(quantitySelected.getText().toString()),
                selectedProduct.getPrice());
        Log.d("totalPrice: ",totalPrice.toString());
        Log.d("selectedQuantity: ",quantitySelected.getText().toString());
        Log.d("productPrice: ",Double.toString(selectedProduct.getPrice()));
        purchaseTotal.setText(String.format("%,.2f",totalPrice));
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