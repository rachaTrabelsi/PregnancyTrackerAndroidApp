package com.esprit.pregnancytracker.Models;

/**
 * Created by a on 04/01/2018.
 */

public class SelfBidou {
    private  String  week  ;
    private  String imageSelfi ;

    public SelfBidou() {
    }

    public SelfBidou(String week, String imageSelfi) {
        this.week = week;
        this.imageSelfi = imageSelfi;
    }

    public String getImageSelfi() {
        return imageSelfi;
    }

    public void setImageSelfi(String imageSelfi) {
        this.imageSelfi = imageSelfi;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

}
