package com.spotify.oauth2.utility;

import com.spotify.oauth2.Pojo.playlistRoot;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.oauth2.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestHTTPMethods {

    public static Response post(String path , String token ,Object payload) {

        return
                given(getRequestSpec())
                        .auth().oauth2(token)
                        .body(payload).
                when()
                        .post(path).
                then().
                        spec(getResponseSpec()).
                        extract().
                        response();

    }

    public static Response postAccounts(HashMap<String,String> formParams, String path){

        return
                given(getRequestSpecAccounts()).
                    formParams(formParams).
                when().
                    post(path).
                then().
                    spec(getResponseSpec()).
                    extract().
                    response();

    }


    public static Response get(String path, String token){

        return
                given(getRequestSpec())
                        .auth().oauth2(token).
                when().
                        get(path).
                then().
                        spec(getResponseSpec())
                        .extract()
                        .response();
    }


    public static Response put(String path , String token , Object payload){

        return
                given(getRequestSpec())
                        .auth().oauth2(token)
                        .body(payload).
                when().
                        put(path).
                then().
                        extract().response();

    }


}
