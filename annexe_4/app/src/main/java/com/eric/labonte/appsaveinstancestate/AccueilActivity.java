package com.eric.labonte.appsaveinstancestate;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AccueilActivity extends AppCompatActivity {

    Button boutonStartActivityForResult;
    TextView texteSalutations;
    Ecouteur ec;
    ActivityResultLauncher<Intent> lanceur;
    Utilisateur user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boutonStartActivityForResult = findViewById(R.id.boutonStartActivityForResult);
        texteSalutations = findViewById(R.id.texteSalutations);

        ec = new Ecouteur();

        boutonStartActivityForResult.setOnClickListener(ec);

        //créer le lanceur
        lanceur = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new RetourBoomerang());

        try {
            user = (Utilisateur) savedInstanceState.getSerializable("user");
            if (user != null){
                texteSalutations.setText("Bonjour, " + user.champPrenom + " " + user.champNom);
            }
        }
        catch (NullPointerException npe){
            npe.printStackTrace();
            texteSalutations.setText("Bonjour!");
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("user", user);
    }

    private class RetourBoomerang implements ActivityResultCallback<ActivityResult>{

        @Override
        public void onActivityResult(ActivityResult result) {
            //retour du boomerang ici
            if(result.getResultCode() == RESULT_OK){
                user = (Utilisateur) result.getData().getSerializableExtra("user");

                texteSalutations.setText("Bonjour, " + user.champPrenom + " " + user.champNom);
            }

        }
    }

    private class Ecouteur implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            lanceur.launch(new Intent(AccueilActivity.this, IdentificationActivity.class));
        }
    }
}