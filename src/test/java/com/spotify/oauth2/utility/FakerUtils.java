package com.spotify.oauth2.utility;

import com.github.javafaker.Faker;

public class FakerUtils {

    public static String generateName(){
        Faker faker = new Faker();
        return "Playlist " +faker.regexify("[a-zA-Z0-9]{8}");
    }

    public static String generateDescription(){
        Faker faker = new Faker();
        return "Playlist Description " +faker.regexify("[a-zA-Z0-9]{10}");
    }

}
