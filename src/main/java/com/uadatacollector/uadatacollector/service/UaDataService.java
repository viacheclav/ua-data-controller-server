package com.uadatacollector.uadatacollector.service;

import com.uadatacollector.uadatacollector.service.entity.BankRate;
import com.uadatacollector.uadatacollector.service.entity.WeatherData;

import java.util.List;

/**
 * Created by slavik on 2017-05-25.
 */
public interface UaDataService {
    String getWeather();

    List<WeatherData> getWeatherDefault(String providerCode);

    String getCurrencyRate();

    List<BankRate> getCurrencyRate(String currency);


}
