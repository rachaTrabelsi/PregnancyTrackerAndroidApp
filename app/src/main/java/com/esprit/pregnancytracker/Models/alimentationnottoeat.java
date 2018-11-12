package com.esprit.pregnancytracker.Models;

/**
 * Created by Asus on 23/12/2017.
 */

public class alimentationnottoeat {

    private int idnotAlim;
    private String titlenot;
    private String descriptionnot;
    private String imagenotoeat;

    public alimentationnottoeat() {
    }

    public alimentationnottoeat(int idnotAlim, String titlenot, String descriptionnot, String imagenotoeat) {
        this.idnotAlim = idnotAlim;
        this.titlenot = titlenot;
        this.descriptionnot = descriptionnot;
        this.imagenotoeat = imagenotoeat;
    }

    public int getIdnotAlim() {
        return idnotAlim;
    }

    public void setIdnotAlim(int idnotAlim) {
        this.idnotAlim = idnotAlim;
    }

    public String getTitlenot() {
        return titlenot;
    }

    public void setTitlenot(String titlenot) {
        this.titlenot = titlenot;
    }

    public String getDescriptionnot() {
        return descriptionnot;
    }

    public void setDescriptionnot(String descriptionnot) {
        this.descriptionnot = descriptionnot;
    }

    public String getImagenotoeat() {
        return imagenotoeat;
    }

    public void setImagenotoeat(String imagenotoeat) {
        this.imagenotoeat = imagenotoeat;
    }
}
