package com.bousquet.noe.annexe_3;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class SingletonMemos {
    private static SingletonMemos instance;
    private Context contexte;
    private ArrayList<String> listeMemos;

    public static SingletonMemos getInstance(Context contexte){
        if (instance == null )
            instance = new SingletonMemos(contexte);
        return instance;
    }

    private SingletonMemos(Context contexte){
        this.contexte = contexte;
        listeMemos = new ArrayList<String>();
    }

    public void ajouterMemo(String memo) {
        listeMemos.add(memo);
    }

    public ArrayList<String> getListeMemos() {
        return listeMemos;
    }

    public void setListeMemos(ArrayList<String> listeMemos) {
        this.listeMemos = listeMemos;
    }

    public void serialiserListe() throws Exception {
        ObjectOutputStream oos = null;

        try {
            FileOutputStream fos = contexte.openFileOutput("fichier.ser", Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos); //autre sorte de Buffer pour les objets (pas pour characteres)
            oos.writeObject(listeMemos);
        }
        finally {
            if (oos != null)
                oos.close();
        }
    }

    public ArrayList<String> recupererListe() throws Exception { //UNSERIALIZE (inverse de serialiserListe)
        ObjectInputStream ois = null;
        ArrayList<String> temp = null;

        try {
            FileInputStream fis = contexte.openFileInput("fichier.ser");
            ois = new ObjectInputStream(fis);
            temp = (ArrayList<String>) ois.readObject();
        } finally {
            if (ois != null)
                ois.close();
        }
        return temp;
    }
}
