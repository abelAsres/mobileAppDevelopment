package com.map524.calculator;

import android.util.Log;

    import java.util.ArrayList;
    import java.util.List;

public class Calculator {
    private List <String> numbers_operands = new ArrayList<String>();
    private Boolean historyMode = false;
    void push(String value) {
        numbers_operands.add(value);
   }

   int calculate() {
        int result = Integer.parseInt(numbers_operands.get(0));
        numbers_operands.remove(0);
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
        }
        return result;
   }

   Boolean changeMode (){
        if (historyMode){
            historyMode=false;
        } else {
            historyMode = true;
        }
        Log.d(null,historyMode.toString());
        return historyMode;
   }

   Boolean getMode () {
        return historyMode;
   }

   public void clear_clac() {
        while (numbers_operands.size() > 0) {
            numbers_operands.remove(0);
        }
   }
}
