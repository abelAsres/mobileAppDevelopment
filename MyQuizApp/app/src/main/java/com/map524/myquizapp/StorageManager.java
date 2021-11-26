package com.map524.myquizapp;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class StorageManager {
    //save quiz results and retrieve
    String resultsFile = "results.txt";
    String questionAndAnswersFile = "QandA.txt";
    int questionCount = 0;

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

    public int  getCorrectAnswersFromInternalStorage(Activity activity){
        FileInputStream fileInputStream = null;
        int countCorrect = 0;
        int read;
        StringBuffer buffer = new StringBuffer();
        try {
            fileInputStream = activity.openFileInput(resultsFile);
            while(( read = fileInputStream.read() )!= -1 ){
                buffer.append((char)read);
            }
            countCorrect = countCorrectAnswers( buffer.toString());
        }catch (IOException ex){ex.printStackTrace();}
        finally {
            try {
                fileInputStream.close();
            }catch (IOException ex){ex.printStackTrace();}

        }
        return  countCorrect;
    }

    private int countCorrectAnswers(String str){
        String correct = "";
        int countCorrect = 0;
        int index = 0;
        for (int i = 0 ; i < str.toCharArray().length ; i++){
            if (str.toCharArray()[i] == '$'){
                questionCount++;
                correct = str.substring(index,i);
                Log.d("correct", correct);
                if(correct.equals("Correct")){
                    countCorrect++;
                }
                index = i+1;
            }
        }
        return countCorrect;
    }

    public void clearInternalStorage (@NonNull Activity activity){
        FileOutputStream fileOutputStream = null;
        try{
            fileOutputStream = activity.openFileOutput(resultsFile, Context.MODE_PRIVATE);
            fileOutputStream.write("".getBytes());
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            }catch (IOException exception){
                exception.printStackTrace();
            }
        }
    }

}
