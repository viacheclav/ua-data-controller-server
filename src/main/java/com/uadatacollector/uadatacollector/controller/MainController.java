package com.uadatacollector.uadatacollector.controller;

import com.uadatacollector.uadatacollector.service.UaDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
