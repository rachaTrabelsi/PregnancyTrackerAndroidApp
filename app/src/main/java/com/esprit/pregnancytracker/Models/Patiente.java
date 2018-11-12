package com.esprit.pregnancytracker.Models;

/**
 * Created by a on 20/11/2017.
 */
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Patiente {

    @SerializedName("idPatiente")
    private int idPatiente;
    @SerializedName("namePatiente")
    private String  namePatiente ;
    @SerializedName("password")
    private String password ;
    @SerializedName("confirmpassoword")
    private String confirmpassoword ;
    @SerializedName("image")
    private String image ;
    @SerializedName("birdhdate")
    private Date birdhdate ;
    @SerializedName("email")
    private String email ;
    private int nbsemaine;
    private  int medecindirect;
    private String Username;

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public int getMedecindirect() {
        return medecindirect;
    }

    public void setMedecindirect(int medecindirect) {
        this.medecindirect = medecindirect;
    }

    public Patiente() {
    }

    public int getNbsemaine() {
        return nbsemaine;
    }

    public void setNbsemaine(int nbsemaine) {
        this.nbsemaine = nbsemaine;
    }

    public String getNamePatiente() {
        return namePatiente;
    }

    public void setNamePatiente(String namePatiente) {
        this.namePatiente = namePatiente;
    }

    public String getPassword() {
        return password;
    }

    public void setPassord(String password) {
        this.password = password;
    }

    public String getConfirmpassoword() {
        return confirmpassoword;
    }

    public void setConfirmpassoword(String confirmpassoword) {
        this.confirmpassoword = confirmpassoword;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getIdPatiente() {
        return idPatiente;
    }

    public void setIdPatiente(int idPatiente) {
        this.idPatiente = idPatiente;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirdhdate() {
        return birdhdate;
    }

    public void setBirdhdate(Date birdhdate) {
        this.birdhdate = birdhdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Patiente{" +
                "idPatiente=" + idPatiente +
                ", namePatiente='" + namePatiente + '\'' +
                ", password='" + password + '\'' +
                ", confirmpassoword='" + confirmpassoword + '\'' +
                ", image='" + image + '\'' +
                ", birdhdate=" + birdhdate +
                ", email='" + email + '\'' +
                '}';
    }
}








