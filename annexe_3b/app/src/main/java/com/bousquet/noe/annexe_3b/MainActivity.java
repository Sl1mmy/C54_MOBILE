package com.bousquet.noe.annexe_3b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.SeekBar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SeekBar mediaSeekBar, notifSeekBar, sonnerieSeekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaSeekBar = findViewById(R.id.mediaSeekBar);
        notifSeekBar = findViewById(R.id.notificationsSeekBar);
        sonnerieSeekBar = findViewById(R.id.sonnerieSeekBar);

        try {
            ObjectInputStream ois = null;
            FileInputStream fos = openFileInput("fichier.ser");
            ois = new ObjectInputStream(fos);

            mediaSeekBar.setProgress((int) ois.readObject());
            notifSeekBar.setProgress((int) ois.readObject());
            sonnerieSeekBar.setProgress((int) ois.readObject());

        } catch (FileNotFoundException fnfe) {
            mediaSeekBar.setProgress(0);
            notifSeekBar.setProgress(0);
            sonnerieSeekBar.setProgress(0);

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
            oos.writeObject(mediaSeekBar.getProgress());
            oos.writeObject(notifSeekBar.getProgress());
            oos.writeObject(sonnerieSeekBar.getProgress());

        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            if(oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}