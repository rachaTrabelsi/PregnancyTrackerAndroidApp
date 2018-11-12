package com.esprit.pregnancytracker.Models;

import java.sql.Timestamp;

/**
 * Created by a on 18/12/2017.
 */

public class RendezVous {
    private  int idRendezVous;
    private String dateRendezVous ;
    private int idPatiente ;
    private int idMedecin ;
    private boolean etat;
    private String doctorName;
    private String patienteName;

    public String getPatienteName() {
        return patienteName;
    }

    public void setPatienteName(String patienteName) {
        this.patienteName = patienteName;
    }

    public RendezVous() {
    }


    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public RendezVous(int idRendezVous, String dateRendezVous, int idPatiente, int idMedecin, boolean etat) {
        this.idRendezVous = idRendezVous;
        this.dateRendezVous = dateRendezVous;
        this.idPatiente = idPatiente;
        this.idMedecin = idMedecin;
        this.etat = etat;
    }

    public int getIdRendezVous() {
        return idRendezVous;
    }

    public void setIdRendezVous(int idRendezVous) {
        this.idRendezVous = idRendezVous;
    }

    public String getDateRendezVous() {
        return dateRendezVous;
    }

    public void setDateRendezVous(String dateRendezVous) {
        this.dateRendezVous = dateRendezVous;
    }

    public int getIdPatiente() {
        return idPatiente;
    }

    public void setIdPatiente(int idPatiente) {
        this.idPatiente = idPatiente;
    }

    public int getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(int idMedecin) {
        this.idMedecin = idMedecin;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }
}
