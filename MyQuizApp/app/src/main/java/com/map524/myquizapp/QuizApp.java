package com.map524.myquizapp;

import android.app.Activity;
import android.app.Application;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class QuizApp extends Application {
    private StorageManager storageManager = new StorageManager();

    public StorageManager getStorageManager() {
        return storageManager;
    }
}
