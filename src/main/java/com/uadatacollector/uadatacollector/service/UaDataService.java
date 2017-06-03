package com.uadatacollector.uadatacollector.service;

import com.uadatacollector.uadatacollector.service.entity.BankRate;

import java.util.List;

/**
 * Created by slavik on 2017-05-25.
 */
public interface UaDataService {
    String getWeather();

    String getCurrencyRate();

    List<BankRate> getCurrencyRate(String currency);
}
