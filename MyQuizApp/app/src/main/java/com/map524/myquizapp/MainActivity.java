package com.map524.myquizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    int currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storageManager = ((QuizApp) getApplication()).getStorageManager();
        Resources res = getResources();
        questions = res.getStringArray(R.array.questions);
        answers = res.getStringArray(R.array.answers);
        FragmentManager fm = getFragmentManager();

        questionText = findViewById(R.id.quizText);
        //WelcomeFragment welcomeFragObj = (WelcomeFragment) fm.findFragmentById(R.id.quiz_fragment);

        FragmentTransaction transaction = fm.beginTransaction();
        if(savedInstanceState == null){
            currentQuestion = 0;
            WelcomeFragment welcomeFragment = new WelcomeFragment();
            transaction.add(R.id.quiz_fragment, welcomeFragment);
        }else{
            currentQuestion = savedInstanceState.getInt("currentQuestion");
            QuizFragment quizFragment = new QuizFragment();
            quizFragment.setQuestion(currentQuestion,questions[currentQuestion]);
            transaction.add(R.id.quiz_fragment, quizFragment);
        }
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if(currentQuestion > 0){
            Log.d("saving", "onSaveInstanceState: ");
            outState.putInt("currentQuestion", currentQuestion);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.storagemanager_menu,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.getAverage:

                return true;
            case R.id.resetFile:
                storageManager.clearInternalStorage(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

        quizFragment.setQuestion(currentQuestion,questions[currentQuestion]);

        transaction.add(R.id.quiz_fragment, quizFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();
        Snackbar.make(findViewById(R.id.quiz_fragment), "You are starting the quiz",
                Snackbar.LENGTH_SHORT)
                .show();
    }


    public void checkAnswer (View view){
        FragmentManager fm = getFragmentManager();
        QuizFragment quizFragObj = (QuizFragment) fm.findFragmentById(R.id.quiz_fragment);

        currentQuestion++;
        String correct = "";


        if ((answers[currentQuestion].equals("True") && view.getId() == R.id.trueButton)
                ||(answers[currentQuestion].equals("False") && view.getId() == R.id.falseButton)) {
            //track answer as correct
            storageManager.saveQuestionAnswerInternalStorage(this,"Correct");
            correct = "Correct!!!";
        }else{
            storageManager.saveQuestionAnswerInternalStorage(this,"Incorrect");
            correct = "Sorry... Incorrect!!!";
        }

        if(currentQuestion > 9){
            Log.d("completed 10 quesitons", "checkAnswer: ");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Your Score is : "+ storageManager.getCorrectAnswersFromInternalStorage(this) +" out of 10")
                    .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
            // Create the AlertDialog object and return it
            AlertDialog dialog = builder.create();
            dialog.show();
        }else{
            FragmentTransaction transaction = fm.beginTransaction();
            QuizFragment quizFragment = new QuizFragment();
            quizFragment.setQuestion(currentQuestion,questions[currentQuestion]);
            transaction.replace(R.id.quiz_fragment, quizFragment);
            transaction.commit();

            Log.d("CorrectCount", ""+storageManager.getCorrectAnswersFromInternalStorage(this));
            Snackbar.make(findViewById(R.id.quiz_fragment), correct,
                    Snackbar.LENGTH_SHORT)
                    .show();
        }

    }
}