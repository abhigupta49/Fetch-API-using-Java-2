package org.example;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        URL url = null;
        HttpURLConnection connection = null ;
        int responseCode = 0;
        String urlApi = "https://api.zippopotam.us/us/33162";

        try {
            url  = new URL(urlApi);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = (HttpURLConnection) url.openConnection();
            responseCode = connection.getResponseCode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(responseCode==200){
            BufferedReader byteCodeConvert;
            try {
                byteCodeConvert = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            StringBuilder apiData = new StringBuilder();
            String readline = null;

            while(true){
                try {
                    if (!((readline=byteCodeConvert.readLine())!=null)) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                apiData.append(readline);
            }

            JSONObject jsonAPIResponse = new JSONObject(apiData.toString());
            System.out.println(jsonAPIResponse);
        }
        else{
            System.out.println("Bad request");
        }

    }
}