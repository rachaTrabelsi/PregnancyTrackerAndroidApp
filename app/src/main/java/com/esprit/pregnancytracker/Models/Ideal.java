package com.esprit.pregnancytracker.Models;

/**
 * Created by Asus on 28/11/2017.
 */

public class Ideal {

  private int idIdeal ;
    private Float imcIdealMax,imcIdealMin,recommendedWeightMin,getRecommendedWeightMax,weightPerWeek;

    public Ideal() {
    }

    public Ideal(int idIdeal, Float imcIdealMax, Float imcIdealMin, Float recommendedWeightMin, Float getRecommendedWeightMax, Float weightPerWeek) {
        this.idIdeal = idIdeal;
        this.imcIdealMax = imcIdealMax;
        this.imcIdealMin = imcIdealMin;
        this.recommendedWeightMin = recommendedWeightMin;
        this.getRecommendedWeightMax = getRecommendedWeightMax;
        this.weightPerWeek = weightPerWeek;
    }

    public int getIdIdeal() {
        return idIdeal;
    }

    public void setIdIdeal(int idIdeal) {
        this.idIdeal = idIdeal;
    }

    public Float getImcIdealMax() {
        return imcIdealMax;
    }

    public void setImcIdealMax(Float imcIdealMax) {
        this.imcIdealMax = imcIdealMax;
    }

    public Float getImcIdealMin() {
        return imcIdealMin;
    }

    public void setImcIdealMin(Float imcIdealMin) {
        this.imcIdealMin = imcIdealMin;
    }

    public Float getRecommendedWeightMin() {
        return recommendedWeightMin;
    }

    public void setRecommendedWeightMin(Float recommendedWeightMin) {
        this.recommendedWeightMin = recommendedWeightMin;
    }

    public Float getGetRecommendedWeightMax() {
        return getRecommendedWeightMax;
    }

    public void setGetRecommendedWeightMax(Float getRecommendedWeightMax) {
        this.getRecommendedWeightMax = getRecommendedWeightMax;
    }

    public Float getWeightPerWeek() {
        return weightPerWeek;
    }

    public void setWeightPerWeek(Float weightPerWeek) {
        this.weightPerWeek = weightPerWeek;
    }
}
