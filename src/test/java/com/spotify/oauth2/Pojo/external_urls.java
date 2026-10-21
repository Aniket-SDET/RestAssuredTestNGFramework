package com.spotify.oauth2.Pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class external_urls {

    private String spotify;

    public external_urls(){

    }

    public external_urls(String spotify){

        this.spotify = spotify;

    }

    public String getSpotify() {
        return spotify;
    }

    public void setSpotify(String spotify) {
        this.spotify = spotify;
    }
}
