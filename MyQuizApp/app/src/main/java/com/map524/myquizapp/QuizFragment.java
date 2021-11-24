package com.map524.myquizapp;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class QuizFragment extends Fragment {
    int question;
    TextView questionText;


    public int getQuestion() {
        return question;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_quiz,container,false);
        this.questionText = v.findViewById(R.id.quizText);
        this.questionText.setText(Integer.toString(this.question));
        return v;
    }

    public void setQuestion(int question) {

        this.question = question;
    }
}