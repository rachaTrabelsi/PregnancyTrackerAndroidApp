package com.esprit.pregnancytracker.utils;

/**
 * Created by Asus on 08/12/2017.
 */

public class ValuesClass {

    public static String waterVal,sleepVal,poid1Val,taille1Val,taille2Val,poid2Val,usernamepatiente,emailpatiente;
    public static int idPatiente ;


    public ValuesClass() {
    }

    public static int getIdPatiente() {
        return idPatiente;
    }

    public static void setIdPatiente(int idPatiente) {
        ValuesClass.idPatiente = idPatiente;
    }

    public static String getWaterVal() {
        return waterVal;
    }


    public static void setWaterVal(String waterVal) {
        ValuesClass.waterVal = waterVal;
    }

    public static String getSleepVal() {
        return sleepVal;
    }

    public static void setSleepVal(String sleepVal) {
        ValuesClass.sleepVal = sleepVal;
    }

    public static String getPoid1Val() {
        return poid1Val;
    }

    public static void setPoid1Val(String poid1Val) {
        ValuesClass.poid1Val = poid1Val;
    }

    public static String getTaille1Val() {
        return taille1Val;
    }

    public static void setTaille1Val(String taille1Val) {
        ValuesClass.taille1Val = taille1Val;
    }

    public static String getTaille2Val() {
        return taille2Val;
    }

    public static void setTaille2Val(String taille2Val) {
        ValuesClass.taille2Val = taille2Val;
    }

    public static String getPoid2Val() {
        return poid2Val;
    }

    public static void setPoid2Val(String poid2Val) {
        ValuesClass.poid2Val = poid2Val;
    }
    public  static String categorie ;

    public static String getCategorie() {
        return categorie;
    }

    public static void setCategorie(String categorie) {
        ValuesClass.categorie = categorie;
    }
}
