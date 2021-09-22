package com.map524.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Calculator calculator = new Calculator();
    String number = "";
    Boolean clear_display=false;
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

        if (clickedValue.equals("+") || clickedValue.equals("-") || clickedValue.equals("*") || clickedValue.equals("/")) {
            //create a alert dialog due to error (cannot end with operand)
            Log.d(null,"click operand");
            calculator.push(number);
            calculator.push(clickedValue);
            display_view.setText(display_view.getText().toString()+clickedValue);
            number="";
        } else if (clickedValue.equals("=")) {
            Log.d(null,"clicked =");
            //if (calculator.getNumberOperands().get(calculator.getNumberOperands().size()).equals("+")
            //|| calculator.getNumberOperands().get(calculator.getNumberOperands().size()).equals("-")
            //|| calculator.getNumberOperands().get(calculator.getNumberOperands().size()).equals("*")
            //|| calculator.getNumberOperands().get(calculator.getNumberOperands().size()).equals("/")) {
            //create a alert dialog due to error (cannot end with operand)
            //    Log.d(null,"cannot perform operation");
            //} else {
                Log.d(null,"before calculation is performed");
                calculator.push(number);
                number="";
                clear_display=true;
                //perform calculation
                int result;
                result = calculator.calculate();
                display_view.setText(Integer.toString(result));
            //}
        } else {
            if (clear_display) {
                display_view.setText("");
                clear_display=false;
            }
            Log.d(null,"calculator push");
            number+=clickedValue;
            Log.d(null,number);
            display_view.setText(display_view.getText().toString()+clickedValue);
        }
    }
}