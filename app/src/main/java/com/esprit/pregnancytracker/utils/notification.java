package com.esprit.pregnancytracker.utils;

/**
 * Created by Asus on 28/12/2017.
 */

public class notification {

    public int idnotif;
    public String titrenotif;
    public String descnotif,imagenotif;

    public int getIdnotif() {
        return idnotif;
    }

    public void setIdnotif(int idnotif) {
        this.idnotif = idnotif;
    }

    public String getTitrenotif() {
        return titrenotif;
    }

    public void setTitrenotif(String titrenotif) {
        this.titrenotif = titrenotif;
    }

    public String getDescnotif() {
        return descnotif;
    }

    public void setDescnotif(String descnotif) {
        this.descnotif = descnotif;
    }

    public notification(int idnotif, String titrenotif, String descnotif) {
        this.idnotif = idnotif;
        this.titrenotif = titrenotif;
        this.descnotif = descnotif;
    }

    public notification(int idnotif, String titrenotif, String descnotif, String imagenotif) {
        this.idnotif = idnotif;
        this.titrenotif = titrenotif;
        this.descnotif = descnotif;
        this.imagenotif = imagenotif;
    }

    public String getImagenotif() {
        return imagenotif;
    }

    public void setImagenotif(String imagenotif) {
        this.imagenotif = imagenotif;
    }

    public notification() {
    }
}
