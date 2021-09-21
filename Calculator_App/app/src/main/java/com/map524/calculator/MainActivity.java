package com.map524.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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
        Log.d(null,clickedValue);
        if (clickedValue.equals("=")) {

            Log.d(null,"checking if =");
            if (calculator.getNumberOperands().get(calculator.getNumberOperands().size()).equals("+")
            || calculator.getNumberOperands().get(calculator.getNumberOperands().size()).equals("-")
            || calculator.getNumberOperands().get(calculator.getNumberOperands().size()).equals("*")
            || calculator.getNumberOperands().get(calculator.getNumberOperands().size()).equals("/")) {
            //create a alert dialog due to error (cannot end with operand)

                Log.d(null,"cannot perform operation");
            } else {
                Log.d(null,"before calculation is performed");
                //perform calculation
                int result;
                result = calculator.calculate();
            }

        } else {
            Log.d(null,"calculator push");
            calculator.push(clickedValue);
            display_view.setText(display_view.getText().toString()+clickedValue);
        }
    }
}