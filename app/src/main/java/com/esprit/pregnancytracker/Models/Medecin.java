package com.esprit.pregnancytracker.Models;

/**
 * Created by a on 27/11/2017.
 */

public class Medecin {
    private static final Medecin ourInstance = new Medecin();

    private int idMadecin;
    private String nameMedecin;
    private String passwordMedecin;
    private String emailMedecin;
    private String adresseMaedecin;
    private String image;
    public static Medecin getInstance() {
        return ourInstance;
    }
    public Medecin() {
    }

    public int getIdMadecin() {
        return idMadecin;
    }

    public void setIdMadecin(int idMadecin) {
        this.idMadecin = idMadecin;
    }

    public String getNameMedecin() {
        return nameMedecin;
    }

    public void setNameMedecin(String nameMedecin) {
        this.nameMedecin = nameMedecin;
    }

    public String getPasswordMedecin() {
        return passwordMedecin;
    }

    public void setPasswordMedecin(String passwordMedecin) {
        this.passwordMedecin = passwordMedecin;
    }

    public String getEmailMedecin() {
        return emailMedecin;
    }

    public void setEmailMedecin(String emailMedecin) {
        this.emailMedecin = emailMedecin;
    }

    public String getAdresseMaedecin() {
        return adresseMaedecin;
    }

    public void setAdresseMaedecin(String adresseMaedecin) {
        this.adresseMaedecin = adresseMaedecin;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
