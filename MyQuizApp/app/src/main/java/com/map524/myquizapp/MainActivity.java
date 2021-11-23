package com.map524.myquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void checkAnswer(View view){

        FragmentManager fm = getFragmentManager();


        QuizFragment quizFragObj = (QuizFragment) fm.findFragmentById(R.id.quiz_fragment);

        FragmentTransaction transaction = fm.beginTransaction();

        if(quizFragObj == null){
            // that mean the area is empty
            // I'm able to add fragment here
            QuizFragment quizFragment = new QuizFragment();
            transaction.add(R.id.quiz_fragment, quizFragment);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        }else{
            transaction.remove(quizFragObj);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        }

        transaction.commit();




        /*QuizFragment nextQuizFragment = new QuizFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.quiz_fragment,QuizFragment.class);
        transaction.commit();
        */
    }
}