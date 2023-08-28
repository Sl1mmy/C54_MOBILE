package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            System.out.println(nbLignes());
            System.out.println(nbChar());
            System.out.println(nbC());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

        public int nbLignes() throws Exception {
            BufferedReader br = null;
            int compteur = 0;
            try {
                FileInputStream fis = openFileInput("annexe1b.txt");
                InputStreamReader isr = new InputStreamReader(fis);
                br = new BufferedReader(isr);

                while (br.readLine() != null) {
                    compteur++;
                }

            } finally {
                fermerFlux(br);
                return compteur;
            }

        }

    public int nbChar() throws Exception {
        BufferedReader br = null;
        int compteur = 0;
        try {
            FileInputStream fis = openFileInput("annexe1b.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);

            while (br.read() != 0) {
                compteur++;
            }

        } finally {
            fermerFlux(br);
            return compteur;
        }

    }

    public int nbC() throws Exception {
        BufferedReader br = null;
        int nbDeC = 0;
        try {
            FileInputStream fis = openFileInput("annexe1b.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);

            int c = br.read();

            while (c != -1) { //-1 veut dire fin du fichier
                if ((char)c == 'c')
                    nbDeC++;
                c = br.read();

            }

        } finally {
            fermerFlux(br);
            return nbDeC;
        }

    }

    public void ecrireNom() {
        String stringAjouter = "Marcelo Figueroa";
        BufferedWriter bw = null;
        try {
            FileOutputStream fos = openFileOutput("annexe1b.txt", Context.MODE_APPEND);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            bw = new BufferedWriter(osw);
            bw.newLine();
            bw.write(stringAjouter);

        }
        catch (Exception fnfe){
            fnfe.printStackTrace();
        }
        finally {
            fermerFlux(bw);
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
