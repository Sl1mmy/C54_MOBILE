package com.eric.labonte.appsaveinstancestate;

import java.io.Serializable;

public class Utilisateur implements Serializable {

    String champPrenom;
    String champNom;

    public Utilisateur(String champPrenom, String champNom) {
        this.champPrenom = champPrenom;
        this.champNom = champNom;
    }

    public String getChampPrenom() {
        return champPrenom;
    }

    public String getChampNom() {
        return champNom;
    }
}
