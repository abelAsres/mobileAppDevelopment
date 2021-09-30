package com.map524.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Calculator calculator;
    String number;
    Boolean clear_display;
    TextView display_view;
    TextView display_history;
    Button calcMode;
    Button clear_button;
    Button addition;
    Button subtraction;
    Button multiplication;
    Button division;
    Button equals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // call the super class onCreate to complete the creation of activity like
        // the view hierarchy
        super.onCreate(savedInstanceState);

        // set the user interface layout for this activity
        // the layout file is defined in the project res/layout/main_activity.xml file
        setContentView(R.layout.activity_main);

        display_view = (TextView) findViewById(R.id.display); // displays calculations
        display_history = (TextView) findViewById(R.id.history); //displays saved calculations
        calcMode = (Button) findViewById(R.id.calcMode); //detrmines if history is saved/not saved
        clear_button = (Button) findViewById(R.id.clear); //clear calculation display
        addition = (Button) findViewById(R.id.plus);
        subtraction = (Button) findViewById(R.id.minus);
        multiplication = (Button) findViewById(R.id.times);
        division = (Button) findViewById(R.id.divide);
        equals = (Button) findViewById(R.id.equal);
        clear_display=false; //determines if calculation display needs to be cleard
        number=""; //holds numbers entered before operand is selected
        disableOperands(); //ensures operands cannot be pushed without a number present for calculation
    }

    @Override
    protected void onStart() {
        super.onStart();
        calculator = new Calculator();
    }


    public void operand_number_clicked (View view) {
        Button button;
        button = (Button)view;
        String clickedValue = button.getText().toString();

        if (clickedValue.equals("+") || clickedValue.equals("-") || clickedValue.equals("*") || clickedValue.equals("/")) {
            //store number and operand for calculation
            calculator.push(number);
            calculator.push(clickedValue);
            display_view.setText(display_view.getText().toString()+clickedValue);
            number="";
            disableOperands();
        } else if (clickedValue.equals("=")) {
            disableOperands();
            calculator.push(number);
            number = "";
            clear_display = true;
            //perform calculation
            int result;
            result = calculator.calculate();
            display_view.setText(display_view.getText()+"= "+Integer.toString(result));
            if (calculator.getMode()) {
                display_history.setText(display_history.getText() +""+ display_view.getText()+"\n");
            }
        } else {
            if (clear_display) {
                display_view.setText("");
                clear_display=false;
            }
            number+=clickedValue;
            display_view.setText(display_view.getText().toString()+clickedValue);
            enableOperands();
        }
    }

    public void changeMode (View view) {
       if (calculator.changeMode()){
           calcMode.setText("Standard - No History");
       }else {
           calcMode.setText("Advance - With history");
           display_history.setText("");

       }
    }

    public void clear_display(View view) {
        display_view.setText("");
        calculator.clear_clac();
        number = "";

    }

    //disabling operands ensures that user cannot push operands without a number present ahead of it
    public void disableOperands () {
        addition.setClickable(false);
        subtraction.setClickable(false);
        multiplication.setClickable(false);
        division.setClickable(false);
        equals.setClickable(false);

        addition.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.purple_700));
        subtraction.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.purple_700));
        multiplication.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.purple_700));
        division.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.purple_700));
        equals.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.purple_700));
    }

    public void enableOperands() {
        addition.setClickable(true);
        subtraction.setClickable(true);
        multiplication.setClickable(true);
        division.setClickable(true);
        equals.setClickable(true);

        addition.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.purple_500));
        subtraction.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.purple_500));
        multiplication.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.purple_500));
        division.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.purple_500));
        equals.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.purple_500));
    }

}
