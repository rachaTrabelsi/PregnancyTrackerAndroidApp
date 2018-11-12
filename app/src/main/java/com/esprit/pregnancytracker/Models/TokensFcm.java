package com.esprit.pregnancytracker.Models;

/**
 * Created by a on 21/12/2017.
 */

public class TokensFcm {
    private int idUSer ;
    private String Token;

    public int getIdUSer() {
        return idUSer;
    }

    public void setIdUSer(int idUSer) {
        this.idUSer = idUSer;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
