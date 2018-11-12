package com.esprit.pregnancytracker.Models;

/**
 * Created by Asus on 23/12/2017.
 */

public class sport {
    private int idSport;
    private String nameSport;
    private String descriptionSport;
    private String imageSoprt;

    public sport(int idSport, String nameSport, String descriptionSport, String imageSoprt) {
        this.idSport = idSport;
        this.nameSport = nameSport;
        this.descriptionSport = descriptionSport;
        this.imageSoprt = imageSoprt;
    }

    public int getIdSport() {
        return idSport;
    }

    public void setIdSport(int idSport) {
        this.idSport = idSport;
    }

    public String getNameSport() {
        return nameSport;
    }

    public void setNameSport(String nameSport) {
        this.nameSport = nameSport;
    }

    public String getDescriptionSport() {
        return descriptionSport;
    }

    public void setDescriptionSport(String descriptionSport) {
        this.descriptionSport = descriptionSport;
    }

    public String getImageSoprt() {
        return imageSoprt;
    }

    public void setImageSoprt(String imageSoprt) {
        this.imageSoprt = imageSoprt;
    }
}
