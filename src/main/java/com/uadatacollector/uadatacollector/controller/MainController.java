package com.uadatacollector.uadatacollector.controller;

import com.uadatacollector.uadatacollector.adminService.AdminService;
import com.uadatacollector.uadatacollector.service.UaDataService;
import com.uadatacollector.uadatacollector.service.entity.BankRate;
import com.uadatacollector.uadatacollector.service.entity.WeatherData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by slavik on 2017-05-20.
 */
@RestController
@RequestMapping("/api")
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    private final UaDataService uaDataService;
    private final AdminService adminService;

    public MainController(UaDataService uaDataService, AdminService adminService) {
        this.uaDataService = uaDataService;
        this.adminService = adminService;
    }

    @GetMapping("/weather-default/{city}/{providerCode}")
    public List<WeatherData> getWeatherDefault(@PathVariable String city, @PathVariable String providerCode){
        logger.info("getWeatherDefault of controller");
        return uaDataService.getWeatherDefault(city, providerCode);
    }

    @GetMapping("/currency-rate")
    public String getCurrencyRate(){
        logger.info("getCurrencyRate of controller");
        return uaDataService.getCurrencyRate();
    }

    @GetMapping("/currency-rate-general/{currency}")
    public List<BankRate> getCurrencyRateGeneral(HttpServletRequest request, @PathVariable String currency){
        logger.info("getCurrencyRateGeneral of controller");
//        adminService.saveUserStatisticData(request);
        return uaDataService.getCurrencyRate(currency);
    }
}
