package com.bousquet.noe.annexe_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button ajouterButton, listeButton, quitterButton;
    Ecouteur ec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ajouterButton = findViewById(R.id.buttonAjouter);
        listeButton = findViewById(R.id.buttonAfficher);
        quitterButton = findViewById(R.id.buttonQuitter);

        ec = new Ecouteur();
        ajouterButton.setOnClickListener(ec);
        listeButton.setOnClickListener(ec);
        quitterButton.setOnClickListener(ec);

        // récupérer liste dans le fichier de sérialisation, s'il existe
        try {
            ArrayList<String> temp = SingletonMemos.getInstance(this).recupererListe();
            SingletonMemos.getInstance(this).setListeMemos(temp);
        } catch (Exception e) { //pas de fichier de sérialisation, entre autres
            e.printStackTrace();
        }
    }

    private class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int id = view.getId();
            Intent i;

            if(id == ajouterButton.getId()) {
                i = new Intent(getApplicationContext(), AjouterActivity.class);
                startActivity(i);
            } else if(id == listeButton.getId()) {
                i = new Intent(getApplicationContext(), ListeActivity.class);
                startActivity(i);
            } else if(id == quitterButton.getId()) {
                finish();
            }
        }
    }
}