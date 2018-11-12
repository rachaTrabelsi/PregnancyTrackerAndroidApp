package com.esprit.pregnancytracker.Models;

/**
 * Created by Asus on 23/12/2017.
 */

public class alimentationtoeat {

    private int idAlimentaion;
    private String title;
    private String description;
    private String imagetoeat;

    public alimentationtoeat() {
    }

    public alimentationtoeat(int idAlimentaion, String title, String description, String imagetoeat) {
        this.idAlimentaion = idAlimentaion;
        this.title = title;
        this.description = description;
        this.imagetoeat = imagetoeat;
    }

    public int getIdAlimentaion() {
        return idAlimentaion;
    }

    public void setIdAlimentaion(int idAlimentaion) {
        this.idAlimentaion = idAlimentaion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagetoeat() {
        return imagetoeat;
    }

    public void setImagetoeat(String imagetoeat) {
        this.imagetoeat = imagetoeat;
    }
}
