package com.spotify.oauth2.enums;

public enum endPointEnums {

    BASE_PATH("/v1"),
    CURRENT_USER_PLAYLIST("/me/playlists"),
    PUT_PLAYLIST("/playlists/%s"),
    GET_PLAYLIST("/playlists/%s");


    private final String endPoint;

    endPointEnums(String endPoint){
        this.endPoint = endPoint;
    }

    public String getPath(){
        return endPoint;
    }

    public String getPath(Object... params){
        return String.format(endPoint,params);
    }


}
