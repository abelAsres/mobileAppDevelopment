package com.map524.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Calculator calculator = new Calculator();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonClicked (View view) {
        Button button;
        button = (Button)view;
        TextView display_view = (TextView) findViewById(R.id.display);

        String clickedValue = button.getText().toString();

        if (clickedValue == "=") {
            calculator.push(display_view.getText().toString());
            calculator.calculate();
        } else {
            display_view.setText(display_view.getText().toString()+clickedValue);
        }
    }

    public int calculate() {
        return calculator.calculate();
    }
}