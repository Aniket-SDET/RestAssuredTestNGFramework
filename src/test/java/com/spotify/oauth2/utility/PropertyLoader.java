package com.spotify.oauth2.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Properties;

public class PropertyLoader {

    public static Properties propertyLoader(String filePath)  {

        Properties properties = new Properties();
        BufferedReader reader;

        try{
            reader = new BufferedReader(new FileReader(filePath));

            try{
                properties.load(reader);
                reader.close();
            } catch (Exception e) {
                throw new RuntimeException("Failed to load properties !!!" + filePath);
            }

        } catch (Exception e){
            throw new RuntimeException("Properties file not found" +filePath);
        }

        return properties;

    }

}
