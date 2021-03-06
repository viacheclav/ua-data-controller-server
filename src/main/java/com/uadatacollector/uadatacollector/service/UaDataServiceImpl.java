package com.uadatacollector.uadatacollector.service;

import com.uadatacollector.uadatacollector.service.currencyProvider.CurrencyProviderFactory;
import com.uadatacollector.uadatacollector.service.entity.BankRate;
import com.uadatacollector.uadatacollector.service.entity.WeatherData;
import com.uadatacollector.uadatacollector.service.weatherProvider.WeatherProviderFactory;
import com.uadatacollector.uadatacollector.service.weatherProvider.WeatherProviderType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.cache.annotation.CacheResult;
import java.util.Arrays;
import java.util.List;


/**
 * Created by slavik on 2017-05-25.
 */
@Component
public class UaDataServiceImpl implements UaDataService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Override
    @CacheResult(cacheName = "weatherDefault")
    public List<WeatherData> getWeatherDefault(String city, String providerCode){
        return WeatherProviderFactory.getInstance(WeatherProviderType.getType(providerCode)).getWeather(city);
    }

    @Override
    @CacheResult(cacheName = "currency")
    public String getCurrencyRate(){
        logger.info("getCurrencyRate");
        String url = "http://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
        return getData(url);
    }


    @Override
    @CacheResult(cacheName = "currencyGeneral")
    public List<BankRate> getCurrencyRate(String currency) {
        List<String> forCurrencies = Arrays.asList("usd", "eur");
        return CurrencyProviderFactory.getInstance(currency).getRates(forCurrencies);

    }

    private String getData(String url){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

}
