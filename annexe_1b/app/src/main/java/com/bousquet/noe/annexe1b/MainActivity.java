package com.bousquet.noe.annexe1b;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    TextView questionA, questionB, questionC, questionD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionA = findViewById(R.id.questionA);
        questionB = findViewById(R.id.questionB);
        questionC = findViewById(R.id.questionC);
        questionD = findViewById(R.id.questionD);

        try {
            questionA.append(String.valueOf(trouverNombreLines()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            questionB.append(String.valueOf(trouverNombreChars()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            questionC.append(String.valueOf(trouverNombreCharC()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }

    public int trouverNombreLines() throws Exception{
        int nbLines = 0;
        FileInputStream fis = null;


        fis = openFileInput("lorem.txt");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        while (br.readLine() != null) {
            nbLines++;
        }

        br.close();
        return nbLines;
    }

    public int trouverNombreChars() throws Exception{
        int nbChars = 0;
        String line;
        FileInputStream fis = null;


        fis = openFileInput("lorem.txt");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        do {
            line = br.readLine();
            nbChars += line.length();

        } while (br.readLine() != null);


        return nbChars;
    }

    public int trouverNombreCharC() throws Exception {
        int nbCharC = 0;
        String line;
        FileInputStream fis = null;


        fis = openFileInput("lorem.txt");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        do {
            line = br.readLine();
            for(int i=0; i < line.length(); i++) {
                if(line.charAt(i) == 'c') {
                    nbCharC++;
                }
            }

        } while (br.readLine() != null);


        return nbCharC;
    }

    public void ecrireNom() throws Exception {
    //voir annexe1


    }
}