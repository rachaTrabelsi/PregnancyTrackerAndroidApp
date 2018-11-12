package com.esprit.pregnancytracker.Models;

/**
 * Created by Asus on 04/01/2018.
 */

public class Notification {
    private int idnotif;
    private String descnotif;
    private String titlenotif;
    private String imagenotif;

    public Notification(int idnotif, String descnotif, String titlenotif, String imagenotif) {
        this.idnotif = idnotif;
        this.descnotif = descnotif;
        this.titlenotif = titlenotif;
        this.imagenotif = imagenotif;
    }

    public Notification(String descnotif, String titlenotif, String imagenotif) {
        this.descnotif = descnotif;
        this.titlenotif = titlenotif;
        this.imagenotif = imagenotif;
    }

    public Notification() {
    }

    public int getIdnotif() {
        return idnotif;
    }

    public void setIdnotif(int idnotif) {
        this.idnotif = idnotif;
    }

    public String getDescnotif() {
        return descnotif;
    }

    public void setDescnotif(String descnotif) {
        this.descnotif = descnotif;
    }

    public String getTitlenotif() {
        return titlenotif;
    }

    public void setTitlenotif(String titlenotif) {
        this.titlenotif = titlenotif;
    }

    public String getImagenotif() {
        return imagenotif;
    }

    public void setImagenotif(String imagenotif) {
        this.imagenotif = imagenotif;
    }
}
