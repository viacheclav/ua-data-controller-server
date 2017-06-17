package com.uadatacollector.uadatacollector.service.weatherProvider;

/**
 * Created by slavik on 2017-06-04.
 */
public class WeatherProviderFactory {
    public static WeatherProvider getInstance(WeatherProviderType weatherProviderType){
        switch (weatherProviderType) {
            case GISMETEO: return new WeatherProviderGismeteo();
            case SINOPTIK: return new WeatherProviderSinoptik();
            default: return new WeatherProviderGismeteo();
        }
    }
}
