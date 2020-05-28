package com.interfacegrafica.com;

public class Constants {
    private static String humidity;
    private static String temperature;
    private static String port;

    public static void setHumudity(String value){
        humidity = value;
    }

    public static void setTemperature(String value){
        temperature = value;
    }
    
    public static String getTemperature(){
        return temperature;

    }

    public static String getHumidity(){
        return humidity;
            
    }

    public static void setPort(String value){
        port = value;
    }
    
    public static String getPort(){
        return port;

    }
}