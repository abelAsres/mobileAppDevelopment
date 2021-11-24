package com.map524.myquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    //TextView quizQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getFragmentManager();

        WelcomeFragment welcomeFragObj = (WelcomeFragment) fm.findFragmentById(R.id.quiz_fragment);

        FragmentTransaction transaction = fm.beginTransaction();

        // that mean the area is empty
        // I'm able to add fragment here
        WelcomeFragment welcomeFragment = new WelcomeFragment();
        transaction.add(R.id.quiz_fragment, welcomeFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();
    }


    public void startQuiz(View view){
        //Remove welcome screen
        FragmentManager fm = getFragmentManager();
        WelcomeFragment welcomeFragObj = (WelcomeFragment) fm.findFragmentById(R.id.quiz_fragment);
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.remove(welcomeFragObj);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);


        //start quiz
        QuizFragment quizFragment = new QuizFragment();


        quizFragment.setQuestion(1);

        //quizQuestion = findViewById(R.id.quizText);

        transaction.add(R.id.quiz_fragment, quizFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();
        Snackbar.make(findViewById(R.id.quiz_fragment), "You are starting the quiz",
                Snackbar.LENGTH_SHORT)
                .show();
    }


    public void checkAnswer (View view){

        switch (view.getId()){
            case R.id.trueButton:
                // check answer if it was correct
                break;
            case R.id.falseButton:
                //check if answer was correct
                break;
            default:
                throw new RuntimeException("Unknown button ID");
        }


        FragmentManager fm = getFragmentManager();
        QuizFragment quizFragObj = (QuizFragment) fm.findFragmentById(R.id.quiz_fragment);
        FragmentTransaction transaction = fm.beginTransaction();
        QuizFragment quizFragment = new QuizFragment();
        quizFragment.setQuestion(quizFragObj.getQuestion() + 1);
        transaction.replace(R.id.quiz_fragment, quizFragment);
        transaction.commit();
        Snackbar.make(findViewById(R.id.quiz_fragment), "You completed questin: "+quizFragObj.getQuestion(),
                Snackbar.LENGTH_SHORT)
                .show();
        //quizQuestion = findViewById(R.id.quizText);
        //quizQuestion.setText(Integer.toString(quizFragment.getQuestion()));
        //Log.d("quiz", "checkAnswer: "+quizFragment.getQuestion());
        //track progress
    }
}