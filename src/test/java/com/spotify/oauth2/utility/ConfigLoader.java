package com.spotify.oauth2.utility;

import java.util.Properties;

public class ConfigLoader {

    private final Properties propLoader;
    //Singleton Rule 2 : Keep a private static variable that holds the one instance
    private static ConfigLoader configLoader;

    //Singleton Rule 1 : Make the constructor private, so nobody outside can do new ConfigLoader()
    private ConfigLoader()  {
        propLoader = PropertyLoader.propertyLoader("src/test/resources/config.properties");
    }

    //Singleton Rule 3 : Provide a public static method that returns that instance
    public static ConfigLoader getInstance()  {

        //It created object only if the instance is null ; i.e. :: Only creates the object for the first time
        if(configLoader == null ){
            configLoader = new ConfigLoader();
        }

        return configLoader;
    }

    //Methods to fetch required properties
    public String getClientId(){
        String prop = propLoader.getProperty("client_id");
        if(prop!=null) return prop;
        else throw new RuntimeException("Property client_id is not specified in the properties file");
    }

    public String getRefreshToken(){
        String prop = propLoader.getProperty("refresh_token");
        if(prop!=null) return prop;
        else throw new RuntimeException("Property refresh_token is not specified in the properties file");
    }
    public String getGrantType(){
        String prop = propLoader.getProperty("grant_type");
        if(prop!=null) return prop;
        else throw new RuntimeException("Property grant_type is not specified in the properties file");
    }
    public String getAccountsHeaderAuth(){
        String prop = propLoader.getProperty("accounts_header");
        if(prop!=null) return prop;
        else throw new RuntimeException("Property accounts_header is not specified in the properties file");
    }



}
