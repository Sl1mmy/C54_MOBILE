package com.bousquet.noe.examen2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Vector<String> lines = new Vector<>();
        try {
            lines = readLines();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Vector<String> readLines() throws Exception {
        Vector<String> lines = new Vector<>();
        String line;
        FileInputStream fis = null;

        fis = openFileInput("coordonees.txt");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        line = br.readLine();
        while(line != null) {
            lines.add(line);
            line = br.readLine();
        }

        fermerFlux(br);
        return lines;
    }

    public void fermerFlux (BufferedReader br){
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}