package com.uadatacollector.uadatacollector.service.weatherProvider;

/**
 * Created by slavik on 2017-06-17.
 */
public enum WeatherProviderType {

    GISMETEO, SINOPTIK;

    public static WeatherProviderType getType(String providerCode) {
        switch (providerCode) {
            case "dvfhgk": return GISMETEO;
            case "khggty": return SINOPTIK;
            default: return GISMETEO;
        }
    }
}
