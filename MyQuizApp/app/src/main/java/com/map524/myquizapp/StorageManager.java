package com.map524.myquizapp;

import android.app.Activity;
import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class StorageManager {
    //save quiz results and retrieve
    String resultsFile = "results.txt";
    String questionAndAnswersFile = "QandA.txt";


    //Create a hash map to store answers and questions
    HashMap<String,String> quizMap = new HashMap<String, String>();//Creating HashMap
    //HashMap<String,String> answerMap = new HashMap<String,String>();



    //track current state of Quiz here

    public void saveQuestionAnswerInternalStorage(Activity activity,String correct){
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = activity.openFileOutput(resultsFile, Context.MODE_APPEND); // continue writting
            fileOutputStream.write((correct+"$").getBytes());

        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            try {
                fileOutputStream.close();
            }catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }



    public void getQuestionsAndAnswersFromInternalStorage(Activity activity){
        FileInputStream fileInputStream = null;
        int read;
        StringBuffer buffer = new StringBuffer();
        try {
            fileInputStream = activity.openFileInput(questionAndAnswersFile);
            while(( read = fileInputStream.read() )!= -1 ){
                buffer.append((char)read);
            }
            fromStringToHashMap( buffer.toString());
        }catch (IOException ex){ex.printStackTrace();}
        finally {
            try {
                fileInputStream.close();
            }catch (IOException ex){ex.printStackTrace();}

        }
    }

    private void fromStringToHashMap(String str){ // str come from the file
        // there is a $ between tasks

        String question = "";
        String answer = "";
        int index = 0;
        for (int i = 0 ; i < str.toCharArray().length ; i++){
            if (str.toCharArray()[i] == '?'){
                question = str.substring(index,i - 1);
                index = i+1;
            }
            if (str.toCharArray()[i] == '-'){
                answer = str.substring(index,i - 1);
                index = i+1;
                quizMap.put(question,answer);
            }
        }
    }

    public String getAnswer(String question){
        return quizMap.get(question);
    }

    public  ArrayList<String> getQuestions(){
        ArrayList<String> questions = new ArrayList<String>();
        quizMap.forEach((q,a) -> {
            questions.add(q);
        });
        return questions;
    }

}
