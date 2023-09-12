package com.eric.appexamen1;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Spinner spinner;

    Groupe[] liste = {new Groupe ("c23", R.drawable.c23),new Groupe("c34", R.drawable.c34),new Groupe("c44", R.drawable.c44)  };

    int index;
    boolean startup = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        spinner = findViewById(R.id.spinner);




        Vector<String> vec = new Vector<>();
        for ( Groupe g : liste)
        {
            vec.add(g.getNomCours());
        }



        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,vec );
        spinner.setAdapter(adapter);

        Ecouteur ec = new Ecouteur();
        spinner.setOnItemSelectedListener(ec);

        try {
            ObjectInputStream ois = null;
            FileInputStream fos = openFileInput("fichier.ser");
            ois = new ObjectInputStream(fos);

            spinner.setSelection((int) ois.readObject());

        } catch (FileNotFoundException fnfe) {
            spinner.setSelection(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        ObjectOutputStream oos = null;

        try {
            FileOutputStream fos = this.openFileOutput("fichier.ser", Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(spinner.getLastVisiblePosition());
        } catch(Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private class Ecouteur implements AdapterView.OnItemSelectedListener
    {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            index = position;

            imageView.setImageResource(liste[position].getAdresseImage());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

}