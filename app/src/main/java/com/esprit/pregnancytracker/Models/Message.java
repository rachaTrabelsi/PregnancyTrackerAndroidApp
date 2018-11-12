package com.esprit.pregnancytracker.Models;

import java.sql.Date;

/**
 * Created by a on 09/03/2018.
 */

public class Message {
    private  int idMessage;
    private String dateEnvoi ;
    private int idExpediteur;
    private int idDestinataire ;
    private String body ;
    private String subject;
    private String NameExpd;
    private String Image;


    public Message() {
    }


    public Message(int idMessage, String dateEnvoi, int idExpediteur, int idDestinataire, String body, String subject, String nameExpd, String image) {
        this.idMessage = idMessage;
        this.dateEnvoi = dateEnvoi;
        this.idExpediteur = idExpediteur;
        this.idDestinataire = idDestinataire;
        this.body = body;
        this.subject = subject;
        NameExpd = nameExpd;
        Image = image;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(String dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public String getNameExpd() {
        return NameExpd;
    }

    public void setNameExpd(String nameExpd) {
        NameExpd = nameExpd;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }




    public int getIdExpediteur() {
        return idExpediteur;
    }

    public void setIdExpediteur(int idExpediteur) {
        this.idExpediteur = idExpediteur;
    }

    public int getIdDestinataire() {
        return idDestinataire;
    }

    public void setIdDestinataire(int idDestinataire) {
        this.idDestinataire = idDestinataire;
    }
}
