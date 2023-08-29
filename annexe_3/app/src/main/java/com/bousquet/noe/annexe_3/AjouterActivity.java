package com.bousquet.noe.annexe_3;

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

    @Override
    protected void onStop() {
        super.onStop();
        try {
            SingletonMemos.getInstance(this).serialiserListe();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class Ecouteur implements View.OnClickListener {
        @Override
        public void onClick(View view) {

            //l'ajouter LOCALEMENT
            SingletonMemos.getInstance(AjouterActivity.this).ajouterMemo(texte.getText().toString());
            finish();
        }
    }




}