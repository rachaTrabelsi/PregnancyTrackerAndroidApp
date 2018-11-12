package com.esprit.pregnancytracker.Models;

/**
 * Created by Asus on 23/12/2017.
 */

public class sleep {

    private int idSleep;
    private String titlesleep;
    private String descriptionsleep;
    private String imageSleep;

    public sleep(int idSleep, String titlesleep, String descriptionsleep, String imageSleep) {
        this.idSleep = idSleep;
        this.titlesleep = titlesleep;
        this.descriptionsleep = descriptionsleep;
        this.imageSleep = imageSleep;
    }

    public int getIdSleep() {
        return idSleep;
    }

    public void setIdSleep(int idSleep) {
        this.idSleep = idSleep;
    }

    public String getTitlesleep() {
        return titlesleep;
    }

    public void setTitlesleep(String titlesleep) {
        this.titlesleep = titlesleep;
    }

    public String getDescriptionsleep() {
        return descriptionsleep;
    }

    public void setDescriptionsleep(String descriptionsleep) {
        this.descriptionsleep = descriptionsleep;
    }

    public String getImageSleep() {
        return imageSleep;
    }

    public void setImageSleep(String imageSleep) {
        this.imageSleep = imageSleep;
    }
}
