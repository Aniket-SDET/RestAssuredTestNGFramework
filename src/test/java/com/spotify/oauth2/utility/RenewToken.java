package com.spotify.oauth2.utility;

import io.restassured.response.Response;

import java.io.FileNotFoundException;
import java.time.Instant;
import java.util.HashMap;

import static com.spotify.oauth2.api.SpecBuilder.getResponseSpec;
import static com.spotify.oauth2.utility.RestHTTPMethods.postAccounts;
import static io.restassured.RestAssured.given;


public class RenewToken {

    private static String access_token;
    private static Instant expiryTime;

    public synchronized static String getToken(){

        try{
            if(access_token == null || Instant.now().isAfter(expiryTime)){

                System.out.println("Renewing token ...");
                Response response = renew_Token();
                access_token = response.path("access_token");
                int expiryTimeInAPIResponse = response.path("expires_in");
                expiryTime = Instant.now().plusSeconds(expiryTimeInAPIResponse - 300);

            } else {
                System.out.println("Token is good to use ...");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error in renewing Token");
        }

        return access_token;

    }



    private static Response renew_Token()  {

        HashMap<String,String> formParam = new HashMap<>();
        formParam.put("client_id",ConfigLoader.getInstance().getClientId());
        formParam.put("refresh_token",ConfigLoader.getInstance().getRefreshToken());
        formParam.put("grant_type",ConfigLoader.getInstance().getGrantType());

        Response response = postAccounts(formParam,"api/token");

        if(response.statusCode() != 200){
           throw new RuntimeException("Token not refreshed !! , Check details again and retry..");
        }

        return response;


    }



}
