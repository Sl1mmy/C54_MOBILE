package com.bousquet.noe.annexe_3;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.IOException;
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

    public void serialiserListe() {
        ObjectOutputStream oos = null;
        try {
            FileOutputStream fos = contexte.openFileOutput("fichier.ser", Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos); //autre sorte de Buffer pour les objets
            oos.writeObject(listeMemos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null)
                    oos.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }


}
