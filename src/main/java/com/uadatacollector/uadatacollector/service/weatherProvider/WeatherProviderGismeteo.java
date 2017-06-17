package com.uadatacollector.uadatacollector.service.weatherProvider;

import com.uadatacollector.uadatacollector.service.entity.Weather;
import com.uadatacollector.uadatacollector.service.entity.WeatherData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by slavik on 2017-06-04.
 */
@Component
public class WeatherProviderGismeteo implements WeatherProvider {

    private static final String DEFAULT_URL_RESOURCE_WEATHER = "https://www.gismeteo.ua/ua/weather-kyiv-4944/";
    private static final String DEFAULT_URL_RESOURCE_WEATHER_KRAKOW = "https://www.gismeteo.ua/ua/weather-krakow-3212/";

    @Override
    public List<WeatherData> getWeather(String city) {
        String baseUrl = "krakow".equals(city) ? DEFAULT_URL_RESOURCE_WEATHER_KRAKOW : DEFAULT_URL_RESOURCE_WEATHER;
        List<WeatherData> weatherData = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(baseUrl).get();
            weatherData = getWeather(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherData;
    }

    private List<WeatherData> getWeather(Document doc) {
        return Stream.of(1, 2, 3).map(i -> {
            Elements tab = doc.select("#tab_wdaily" + i);
            Elements minMax = tab.select(".c");
            List<Weather> weatherList = doc.select("#tbwdaily" + i + " tr").stream()
                    .map(el -> Weather.newBuilder()
                            .time(el.select("th").text())
                            .title(el.select(".cltext").text())
                            .temperature(el.select(".temp").select(".c").text())
                            .image(el.select("img").attr("src"))
                            .build()
                    ).collect(Collectors.toList());
            return WeatherData.newBuilder()
                    .dayName(tab.select("dl dt").text())
                    .date(tab.select("dl dd").text())
                    .min(minMax.get(0).text())
                    .max(minMax.get(1).text())
                    .weathers(weatherList)
                    .build();
        }).collect(Collectors.toList());
    }

}
