package com.map524.anothermovieapp;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.HttpsURLConnection;

public class NetworkingService {


    private String apiKey = "&api_key=b6cc214404645f70e9a23fb883752578";
    private String genreUrl = "https://api.themoviedb.org/3/discover/movie?with_genres=";

    public static ExecutorService networkExecutorService = Executors.newFixedThreadPool(4);
    public static Handler networkingHandler = new android.os.Handler(Looper.getMainLooper());

    //this will notify the main activity when data fetch is complete
    interface NetworkingListener{
        void dataListener(String jsonString);
    }

    public NetworkingListener networkingListener;
    //look up genre of movies
    public void getMovieByGenre(int genreId){
    // TODO 1: need to fetch and return movies from genre to caller
        //create new runnable object to run in the background
        networkExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                HttpsURLConnection httpsURLConnection = null;
                try {
                    //make http call here
                    String urlString = genreUrl + genreId + apiKey;
                    URL urlObj = new URL(urlString);
                    httpsURLConnection = (HttpsURLConnection) urlObj.openConnection();
                    httpsURLConnection.setRequestMethod("GET");
                    httpsURLConnection.setRequestProperty("Content-Type","application/json");

                    //create reader obj to read data from connection
                    InputStream in = httpsURLConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(in);
                    int inputStramData = 0;
                    String jsonData ="";

                    while((inputStramData = reader.read()) != -1) {
                        char current = (char) reader.read();
                        jsonData += current;
                    }
                    final String data = jsonData;
                    //when the data is complete
                    //use handler to run code in main thread
                    networkingHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            networkingListener.dataListener(data);
                        }
                    });

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException exception) {
                    exception.printStackTrace();
                }finally {
                    httpsURLConnection.disconnect();
                }

            }
        });
    }

    //search movies by title
    public void searchByMovieByTitle(String movieTitle){
        // TODO 2: need to fetch and return movie to caller
    }
}
