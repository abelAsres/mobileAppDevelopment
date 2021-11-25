package com.map524.myquizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    //TextView quizQuestion;
    StorageManager storageManager;
    TextView questionText;
    //ArrayList<String> questions;

    String[] questions;
    String[] answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storageManager = ((QuizApp) getApplication()).getStorageManager();
        Resources res = getResources();
        questions = res.getStringArray(R.array.questions);
        answers = res.getStringArray(R.array.answers);
        //questions = storageManager.getQuestions();
        FragmentManager fm = getFragmentManager();

        //WelcomeFragment welcomeFragObj = (WelcomeFragment) fm.findFragmentById(R.id.quiz_fragment);

        FragmentTransaction transaction = fm.beginTransaction();

        // that mean the area is empty
        // I'm able to add fragment here
        WelcomeFragment welcomeFragment = new WelcomeFragment();
        transaction.add(R.id.quiz_fragment, welcomeFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

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

        quizFragment.setQuestion(0,questions[0]);

        transaction.add(R.id.quiz_fragment, quizFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();
        Snackbar.make(findViewById(R.id.quiz_fragment), "You are starting the quiz",
                Snackbar.LENGTH_SHORT)
                .show();
    }


    public void checkAnswer (View view){

        questionText = findViewById(R.id.quizText);
        int answerIndex = Arrays.asList(questions).indexOf(questionText.getText().toString());
        String correct = "";


        if ((answers[answerIndex].equals("True") && view.getId() == R.id.trueButton)
                ||(answers[answerIndex].equals("False") && view.getId() == R.id.falseButton)) {
            //track answer as correct
            storageManager.saveQuestionAnswerInternalStorage(this,"Correct");
            correct = "Correct!!!";
        }else{
            if( view.getId() == R.id.trueButton){
                Log.d("checking","checkAnswer: clicked true"+answerIndex+" "+answers[answerIndex] +" "+ view.getId()+ " "+  R.id.trueButton);
                storageManager.saveQuestionAnswerInternalStorage(this,"Incorrect");
            }else{
                Log.d("checking","checkAnswer: clicked false"+answerIndex+" "+answers[answerIndex]);

                storageManager.saveQuestionAnswerInternalStorage(this,"Incorrect");
            }
            correct = "Sorry... Incorrect!!!";
        }

        FragmentManager fm = getFragmentManager();
        QuizFragment quizFragObj = (QuizFragment) fm.findFragmentById(R.id.quiz_fragment);
        FragmentTransaction transaction = fm.beginTransaction();
        QuizFragment quizFragment = new QuizFragment();
        quizFragment.setQuestion(quizFragObj.getNumber() + 1,questions[quizFragObj.getNumber() + 1]);
        transaction.replace(R.id.quiz_fragment, quizFragment);
        transaction.commit();

        Snackbar.make(findViewById(R.id.quiz_fragment), correct,
                Snackbar.LENGTH_SHORT)
                .show();
    }
}