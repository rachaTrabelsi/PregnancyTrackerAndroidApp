package com.esprit.pregnancytracker.utils;

import java.util.Date;

/**
 * Created by Asus on 01/01/2018.
 */

public class PatienteSingleton {
    private static final PatienteSingleton ourInstance = new PatienteSingleton();

    public static PatienteSingleton getInstance() {
        return ourInstance;
    }
    private int idpatiente,nbsemaine , idMedecin;

    public int getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(int idMedecin) {
        this.idMedecin = idMedecin;
    }

    public String getDateinscription() {
        return dateinscription;
    }

    public void setDateinscription(String dateinscription) {
        this.dateinscription = dateinscription;
    }

    private String dateinscription;
   private  String username,password,confirmpassword,namepatiente,email,birthdate,image;
    private PatienteSingleton() {
    }

    public static PatienteSingleton getOurInstance() {
        return ourInstance;
    }

    public int getIdpatiente() {
        return idpatiente;
    }

    public void setIdpatiente(int idpatiente) {
        this.idpatiente = idpatiente;
    }

    public int getNbsemaine() {
        return nbsemaine;
    }

    public void setNbsemaine(int nbsemaine) {
        this.nbsemaine = nbsemaine;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public String getNamepatiente() {
        return namepatiente;
    }

    public void setNamepatiente(String namepatiente) {
        this.namepatiente = namepatiente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
