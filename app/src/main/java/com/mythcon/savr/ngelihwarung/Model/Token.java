package com.mythcon.savr.ngelihwarung.Model;

/**
 * Created by SAVR on 27/03/2018.
 * pertama buat Class Token untuk dipanggil di MyFirebaseIdServices
 * selanjutnya buat class Service MyFirebaseMessaging
 */

public class Token {
    public String token;
    public boolean isServerToken;

    public Token() {
    }

    public Token(String token, boolean isServerToken) {
        this.token = token;
        this.isServerToken = isServerToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isServerToken() {
        return isServerToken;
    }

    public void setServerToken(boolean serverToken) {
        isServerToken = serverToken;
    }
}
