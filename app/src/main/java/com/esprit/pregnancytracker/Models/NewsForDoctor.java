package com.esprit.pregnancytracker.Models;

/**
 * Created by a on 09/02/2018.
 */

public class NewsForDoctor {
    private String image ;
    private String url ;
    private String titre;
    private int id ;

    public NewsForDoctor() {
    }

    public NewsForDoctor(String image, String url, String titre, int id) {
        this.image = image;
        this.url = url;
        this.titre = titre;
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
