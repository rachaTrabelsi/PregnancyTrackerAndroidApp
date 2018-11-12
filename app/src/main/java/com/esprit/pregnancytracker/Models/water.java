package com.esprit.pregnancytracker.Models;

/**
 * Created by Asus on 23/12/2017.
 */

public class water {
    private int idWater;
    private String titleWater;
    private String descriptionWater;
    private String imageWater;

    public water(int idWater, String titleWater, String descriptionWater, String imageWater) {
        this.idWater = idWater;
        this.titleWater = titleWater;
        this.descriptionWater = descriptionWater;
        this.imageWater = imageWater;
    }

    public int getIdWater() {
        return idWater;
    }

    public void setIdWater(int idWater) {
        this.idWater = idWater;
    }

    public String getTitleWater() {
        return titleWater;
    }

    public void setTitleWater(String titleWater) {
        this.titleWater = titleWater;
    }

    public String getDescriptionWater() {
        return descriptionWater;
    }

    public void setDescriptionWater(String descriptionWater) {
        this.descriptionWater = descriptionWater;
    }

    public String getImageWater() {
        return imageWater;
    }

    public void setImageWater(String imageWater) {
        this.imageWater = imageWater;
    }
}
