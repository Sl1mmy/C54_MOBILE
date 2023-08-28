package com.bousquet.noe.annexe1b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
import java.io.OutputStreamWriter;
import java.util.Scanner;

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
            questionB.append(String.valueOf(trouverNombreChars()));
            questionC.append(String.valueOf(trouverNombreCharC()));
            ecrireNom();
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

        fermerFlux(br);
        return nbLines;
    }

    public int trouverNombreChars() throws Exception{
        int nbChars = 0;
        String line;
        FileInputStream fis = null;


        fis = openFileInput("lorem.txt");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        line = br.readLine();
        while(line != null) {
            nbChars += line.length();
            line = br.readLine();
        }


        fermerFlux(br);
        return nbChars;
    }

    public int trouverNombreCharC() throws Exception {
        int nbCharC = 0;
        String line;
        FileInputStream fis = null;


        fis = openFileInput("lorem.txt");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        line = br.readLine();
        while(line != null) {
            for(int i=0; i < line.length(); i++) {
                if(line.charAt(i) == 'c') {
                    nbCharC++;
                }
            }
            line = br.readLine();
        }


        fermerFlux(br);
        return nbCharC;
    }


    public void ecrireNom() throws Exception{
        String stringAjouter = "Noe Bousquet";
        BufferedWriter bw = null;

        FileOutputStream fos = openFileOutput("lorem.txt", Context.MODE_APPEND);
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        bw = new BufferedWriter(osw);
        bw.newLine();
        bw.write(stringAjouter);

        bw.close();
    }

    public int nbMotsScanner (){
        int compteur = 0;
        Scanner sc = null;
        try {
            FileInputStream fis = openFileInput("annexe1b.txt");

            sc = new Scanner(fis);

            while(sc.hasNext()) {
                System.out.println (sc.next());
                compteur++;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            sc.close();
            return compteur;
        }
    }

    public void fermerFlux (BufferedReader br){
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}