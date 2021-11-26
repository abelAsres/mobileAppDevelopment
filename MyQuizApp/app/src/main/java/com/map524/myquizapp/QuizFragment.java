package com.map524.myquizapp;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Random;

public class QuizFragment extends Fragment {
    String question;
    int number;
    TextView questionText;
    Random rand = new Random();
    public String getQuestion() {
        return question;
    }
    public int getNumber(){
        return this.number;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_quiz,container,false);
        this.questionText = v.findViewById(R.id.quizText);
        Log.d("Random color", ""+R.color.Black);
        this.questionText.setBackgroundResource((int)Math.floor(Math.random()*(R.color.Maroon-R.color.SteelBlue+1)+R.color.SteelBlue));
        this.questionText.setText(this.question);
        return v;
    }

    public void setQuestion(int number , String question) {

        this.question = question;
        this.number = number;

    }
}