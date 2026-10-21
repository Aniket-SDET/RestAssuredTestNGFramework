package com.spotify.oauth2.Pojo;

public class error {

    private int status;
    private String message;

    public error(){

    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
