package com.bousquet.noe.annexe_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;

public class ListeActivity extends AppCompatActivity {
    ListView liste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);
        liste = findViewById(R.id.listText);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, SingletonMemos.getInstance(this).getListeMemos());
        liste.setAdapter(adapter);

    }


}