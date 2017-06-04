package com.uadatacollector.uadatacollector.service.weatherProvider;

import com.uadatacollector.uadatacollector.service.entity.WeatherData;

import java.util.List;

/**
 * Created by slavik on 2017-06-04.
 */
public interface WeatherProvider {

    List<WeatherData> getWeather();
}
