package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.Pojo.playlistRoot;
import com.spotify.oauth2.enums.endPointEnums;
import com.spotify.oauth2.utility.RestHTTPMethods;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.spotify.oauth2.utility.RenewToken.getToken;

public class PlaylistApi {

    @Step
    public static Response post(playlistRoot requestPlaylist){

        return RestHTTPMethods.post(endPointEnums.CURRENT_USER_PLAYLIST.getPath(), getToken(), requestPlaylist);

    }

    public static Response post(playlistRoot requestPlaylist, String token){

        return RestHTTPMethods.post(endPointEnums.CURRENT_USER_PLAYLIST.getPath(), token ,requestPlaylist);

    }


    public static Response get(String playlistId){

        return RestHTTPMethods.get(endPointEnums.GET_PLAYLIST.getPath(playlistId) , getToken());

    }

    public static Response get(Boolean currentUser){

        if (!Boolean.TRUE.equals(currentUser)) {
            throw new IllegalArgumentException("currentUser must be true to fetch current user playlists");
        }

        return RestHTTPMethods.get(endPointEnums.CURRENT_USER_PLAYLIST.getPath(), getToken());


    }

    public static Response put(String playlistId , playlistRoot requestPlaylist) {

        return RestHTTPMethods.put(endPointEnums.PUT_PLAYLIST.getPath(playlistId), getToken(), requestPlaylist);

    }


}
