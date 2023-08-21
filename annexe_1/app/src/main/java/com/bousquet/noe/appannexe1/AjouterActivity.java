package com.bousquet.noe.appannexe1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class AjouterActivity extends AppCompatActivity {
    Button ajouterButton;
    TextView texte;
    Ecouteur ec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);

        ajouterButton = findViewById(R.id.buttonAjouterTexte);
        texte = findViewById(R.id.addEntry);
        Ecouteur ec = new Ecouteur();

        ajouterButton.setOnClickListener(ec);
    }

    private class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            FileOutputStream fos;
            BufferedWriter bw = null;
            try {
                //pour ecrire vers un fichier
                fos = openFileOutput("memos.txt", Context.MODE_APPEND); //append ecrit a la fin du fichier
                //tranforme le data en characters aulieux de binaire
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                bw = new BufferedWriter(osw);
                bw.write(texte.getText().toString());
                bw.newLine();


            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                fermerFlux(bw);
            }

            finish();
        }
    }

    public void fermerFlux (Writer bw) {
        try {
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}