package com.map524.calculator;

import android.util.Log;

    import java.util.ArrayList;
    import java.util.List;

public class Calculator {

    private List <String> numbers_operands = new ArrayList<String>();
    void push(String value) {
        numbers_operands.add(value);
   }

   int calculate() {
        int result = Integer.parseInt(numbers_operands.get(0));
        numbers_operands.remove(0);
        //Log.d(null, String.valueOf(numbers_operands.isEmpty()));
        //int count = 0;
       for (String i: numbers_operands) {
           Log.d(null, i);
       }
        while (numbers_operands.size() > 0) {
            //check for operand
            //perform opertaion with the next value
            //remove values from array
            Log.d(null,numbers_operands.get(0));
            if (numbers_operands.get(0).equals("+")) {
                result+=Integer.parseInt(numbers_operands.get(1));
                Log.d(null,Integer.toString(result));
                numbers_operands.remove(0);
                numbers_operands.remove(0);
            } else if (numbers_operands.get(0).equals("-")) {
                result-=Integer.parseInt(numbers_operands.get(1));
                numbers_operands.remove(0);
                numbers_operands.remove(0);
            } else if(numbers_operands.get(0).equals("*")) {
                result*=Integer.parseInt(numbers_operands.get(1));
                numbers_operands.remove(0);
                numbers_operands.remove(0);
            } else {
                result/=Integer.parseInt(numbers_operands.get(1));
                numbers_operands.remove(0);
                numbers_operands.remove(0);
            }
            //Log.d(null, String.valueOf(result));
        }
        return result;
   }

   List <String> getNumberOperands() {
        return numbers_operands;
   }
   void show_numbers_operands() {
        numbers_operands.forEach(value->System.out.println(value));
   }


}
