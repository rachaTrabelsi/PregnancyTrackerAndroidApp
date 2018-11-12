package com.esprit.pregnancytracker.Models;

import java.sql.Timestamp;

/**
 * Created by a on 28/12/2017.
 */

public class Dairy {
    private  int idNote;
    private  int idPatiente ;
    private String subject ;
    private String body;
    private String dateNote;
    private String dayMonth;
    private String year;
    private String dayOfWeek;






    public Dairy() {
    }

    public Dairy(int idNote, int idPatiente, String subject, String body, String dateNote) {
        this.idNote = idNote;
        this.idPatiente = idPatiente;
        this.subject = subject;
        this.body = body;
        this.dateNote = dateNote;
    }

    public int getIdNote() {
        return idNote;
    }

    public String getDayMonth() {
        return dayMonth;
    }

    public void setDayMonth(String dayMonth) {
        this.dayMonth = dayMonth;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setIdNote(int idNote) {
        this.idNote = idNote;
    }

    public int getIdPatiente() {
        return idPatiente;
    }

    public void setIdPatiente(int idPatiente) {
        this.idPatiente = idPatiente;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDateNote() {
        return dateNote;
    }

    public void setDateNote(String dateNote) {
        this.dateNote = dateNote;
    }
}
