package com.uadatacollector.uadatacollector.controller;

import com.uadatacollector.uadatacollector.service.UaDataService;
import com.uadatacollector.uadatacollector.service.entity.BankRate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by slavik on 2017-05-20.
 */
@RestController
@RequestMapping("/api")
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    private final UaDataService uaDataService;

    public MainController(UaDataService uaDataService) {
        this.uaDataService = uaDataService;
    }

    @GetMapping("/weather")
    public String getWeather(){
        logger.info("getWeather of controller");
        return uaDataService.getWeather();
    }

    @GetMapping("/currency-rate")
    public String getCurrencyRate(){
        logger.info("getCurrencyRate of controller");
        return uaDataService.getCurrencyRate();
    }

    @GetMapping("/currency-rate-general/{currency}")
    public List<BankRate> getCurrencyRateGeneral(@PathVariable String currency){
        logger.info("getCurrencyRateGeneral of controller");
        return uaDataService.getCurrencyRate(currency);
    }
}
