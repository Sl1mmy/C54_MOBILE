package com.eric.labonte.appsaveinstancestate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class IdentificationActivity extends AppCompatActivity {

    TextView champPrenom;
    TextView champNom;
    Button boutonConfirmer;
    Ecouteur ec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification);

        champPrenom = findViewById(R.id.champPrenom);
        champNom = findViewById(R.id.champNom);
        boutonConfirmer = findViewById(R.id.boutonConfirmer);

        ec = new Ecouteur();

        boutonConfirmer.setOnClickListener(ec);
    }

    @Override
    protected void onStop() {
        super.onStop();
        try (FileOutputStream fis = openFileOutput("utilisateur.ser", Context.MODE_PRIVATE);
                ObjectOutputStream oos = new ObjectOutputStream(fis)
        )
    }

    private class Ecouteur implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            Utilisateur user = new Utilisateur(champPrenom.getText().toString(), champNom.getText().toString());

            Intent i = new Intent();
            i.putExtra("user", user);
            setResult(RESULT_OK, i); // revoit le boomerang à l'activité de départ
            finish();
        }
    }
}