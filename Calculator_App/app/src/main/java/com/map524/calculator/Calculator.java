package com.map524.calculator;

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
        int count = 0;
        while (numbers_operands.size() > 0) {
            //check for operand
            //perform opertaion with the next value
            //remove values from array
            if (numbers_operands.get(0) == "+") {
                result+=Integer.parseInt(numbers_operands.get(1));
                numbers_operands.remove(0);
                numbers_operands.remove(1);
            } else if (numbers_operands.get(0) == "-") {
                result-=Integer.parseInt(numbers_operands.get(1));
                numbers_operands.remove(0);
                numbers_operands.remove(1);
            } else if(numbers_operands.get(0) == "*") {
                result*=Integer.parseInt(numbers_operands.get(1));
                numbers_operands.remove(0);
                numbers_operands.remove(1);
            } else {
                result/=Integer.parseInt(numbers_operands.get(1));
                numbers_operands.remove(0);
                numbers_operands.remove(1);
            }
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
