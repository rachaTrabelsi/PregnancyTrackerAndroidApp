package com.esprit.pregnancytracker.Models;

import android.media.Image;
import android.widget.ImageView;

/**
 * Created by Asus on 10/12/2017.
 */

public class PagerModel {

    private int idPager;
    private String titlePager;
    private String descriptionPager;
    private String imagePager;

    public PagerModel(int idPager, String titlePager, String descriptionPager) {
        this.idPager = idPager;
        this.titlePager = titlePager;
        this.descriptionPager = descriptionPager;
        this.imagePager = imagePager;
    }

    public PagerModel(int idPager, String titlePager, String descriptionPager, String imagePager) {
        this.idPager = idPager;
        this.titlePager = titlePager;
        this.descriptionPager = descriptionPager;
        this.imagePager = imagePager;
    }

    public int getIdPager() {
        return idPager;
    }

    public void setIdPager(int idPager) {
        this.idPager = idPager;
    }

    public String getTitlePager() {
        return titlePager;
    }

    public void setTitlePager(String titlePager) {
        this.titlePager = titlePager;
    }

    public String getDescriptionPager() {
        return descriptionPager;
    }

    public void setDescriptionPager(String descriptionPager) {
        this.descriptionPager = descriptionPager;
    }

    public String getImagePager() {
        return imagePager;
    }

    public void setImagePager(String imagePager) {
        this.imagePager = imagePager;
    }
}
