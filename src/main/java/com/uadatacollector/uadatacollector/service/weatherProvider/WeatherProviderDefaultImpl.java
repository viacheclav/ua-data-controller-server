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
public class WeatherProviderDefaultImpl implements WeatherProvider {

    private static final String DEFAULT_URL_RESOURCE_WEATHER = "https://www.gismeteo.ua/ua/weather-kyiv-4944/";

    @Override
    public List<WeatherData> getWeather() {
        List<WeatherData> weatherData = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(DEFAULT_URL_RESOURCE_WEATHER).get();
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
                    .map(el -> {
                        String time = el.select("th").text();
                        String title = el.select(".cltext").text();
                        String temperature = el.select(".temp").select(".c").text();
                        String image = el.select("img").attr("src");
                        return new Weather(time, title, temperature, image);
                    }).collect(Collectors.toList());
            return new WeatherData(tab.select("dl dt").text(), tab.select("dl dd").text(), minMax.get(0).text(),
                    minMax.get(1).text(), weatherList);
        }).collect(Collectors.toList());
    }

}
