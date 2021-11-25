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
        this.questionText.setBackgroundResource((int)Math.floor(Math.random()*(2131034155-2131034123+1)+2131034123));
        this.questionText.setText(this.question);
        return v;
    }

    public void setQuestion(int number , String question) {

        this.number = number;
        this.question = question;
    }
}