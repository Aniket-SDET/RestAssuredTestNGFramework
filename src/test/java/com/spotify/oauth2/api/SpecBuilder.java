package com.spotify.oauth2.api;

import com.spotify.oauth2.enums.endPointEnums;
import com.spotify.oauth2.utility.ConfigLoader;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {



    public static RequestSpecification getRequestSpec(){

        return new RequestSpecBuilder()
                .setBaseUri(System.getProperty("BASE_URI"))
                //.setBaseUri("https://api.spotify.com")
                .setBasePath(endPointEnums.BASE_PATH.getPath())
                .setContentType("application/json")
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();

    }

    public static RequestSpecification getRequestSpecAccounts(){

        return new RequestSpecBuilder()
                .setBaseUri(System.getProperty("BASE_ACCOUNTS_URI"))
                //.setBaseUri("https://accounts.spotify.com")
                .addHeader("Authorization", "Basic " + ConfigLoader.getInstance().getAccountsHeaderAuth())
                .setContentType("application/x-www-form-urlencoded")
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL)
                .build();

    }

    public static ResponseSpecification getResponseSpec(){

        return new ResponseSpecBuilder()
                .expectContentType("application/json")
                .log(LogDetail.ALL)
                .build();

    }


}
