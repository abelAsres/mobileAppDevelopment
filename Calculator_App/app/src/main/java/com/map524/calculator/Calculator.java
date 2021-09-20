package com.map524.calculator;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    private List <String> numbers_operands = new ArrayList<String>();

    void push(String value) {
       //check is the value is an operand

       //if value is (+-*/) add value to List
        

        numbers_operands.add(value);
   }

   int calculate() {
        numbers_operands.forEach(value-> {

        });
        return 0;
   }

   void show_numbers_operands() {
        numbers_operands.forEach(value->System.out.println(value));
   }


}
