package com.uadatacollector.uadatacollector.service.weatherProvider;

/**
 * Created by slavik on 2017-06-04.
 */
public class WeatherProviderFactory {
    public static WeatherProvider getInstance(){
        return new WeatherProviderDefaultImpl();
    }
}
