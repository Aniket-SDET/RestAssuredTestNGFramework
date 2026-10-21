package com.spotify.oauth2.enums;

public enum statusCodeEnums {

    CODE_200(200),
    CODE_201(201),
    CODE_400(400),
    CODE_401(401);

    private final int statusCode;

    statusCodeEnums(int statusCode){
        this.statusCode=statusCode;
    }

    public int getStatusCode(){
        return statusCode;
    }

}
