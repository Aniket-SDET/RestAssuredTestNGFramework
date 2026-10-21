package com.spotify.oauth2.utility;

import java.util.Properties;

public class DataLoader {

    private final Properties propLoader;
    //Singleton Rule 2 : Keep a private static variable that holds the one instance
    private static DataLoader dataLoader;

    //Singleton Rule 1 : Make the constructor private, so nobody outside can do new dataLoader()
    private DataLoader()  {
        propLoader = PropertyLoader.propertyLoader("src/test/resources/data.properties");
    }

    //Singleton Rule 3 : Provide a public static method that returns that instance
    public static DataLoader getInstance()  {

        //It created object only if the instance is null ; i.e. :: Only creates the object for the first time
        if(dataLoader == null ){
            dataLoader = new DataLoader();
        }

        return dataLoader;
    }

    //Methods to fetch required properties
    public String getGetPlaylistId(){
        String prop = propLoader.getProperty("get_playlist_id");
        if(prop!=null) return prop;
        else throw new RuntimeException("Property get_playlist_id is not specified in the properties file");
    }

    public String getUpdatePlaylistId(){
        String prop = propLoader.getProperty("update_playlis_id");
        if (prop!=null){
            return prop;
        }else{
            throw new RuntimeException("Property update_playlis_id is not specified in the properties file");
        }
    }



}
