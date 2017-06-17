package com.uadatacollector.uadatacollector.service.weatherProvider;

import com.uadatacollector.uadatacollector.service.entity.Weather;
import com.uadatacollector.uadatacollector.service.entity.WeatherData;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by slavik on 2017-06-17.
 */
@Component
public class WeatherProviderSinoptik implements WeatherProvider {

    private static final String DEFAULT_URL_RESOURCE_WEATHER = "https://ua.sinoptik.ua/погода-киев";
    private static final String DEFAULT_URL_RESOURCE_WEATHER_KRAKOW = "https://ua.sinoptik.ua/погода-краков";

    @Override
    public List<WeatherData> getWeather(String city) {
        String baseUrl = "krakow".equals(city) ? DEFAULT_URL_RESOURCE_WEATHER_KRAKOW : DEFAULT_URL_RESOURCE_WEATHER;
        List<WeatherData> weatherData = new ArrayList<>();
        try {
            List<String> urls = IntStream.range(0, 3).boxed()
                    .map(i -> baseUrl + "/" + LocalDate.now().plusDays(i) + "?ajax=GetForecast")
                    .collect(Collectors.toList());
            List<Document> forecasts = new ArrayList<>();
            for (String url : urls) {
                Document forecast = Jsoup.connect(url).get();
                forecasts.add(forecast);
            }
            Document doc = Jsoup.connect(baseUrl).get();
            weatherData = getWeather(doc, forecasts);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherData;
    }

    public static List<WeatherData> getWeather(Document doc, List<Document> forecasts) {
        return IntStream.rangeClosed(1, forecasts.size()).boxed().map(dayNumber -> {
            Elements header = doc.select("#bd" + dayNumber);
            Elements data = forecasts.get(dayNumber - 1).select("div");

            Elements exactTime = data.select(".gray.time");
            Elements temperature = data.select(".temperature");
            Elements icons = data.select(".img.weatherIcoS");
            Elements probability = data.select("tr").get(8).select("td");

            int weatherSize = temperature.select("td").size();
            Elements timeOfTheDay = data.select("thead td");

            List<String> strings = timeOfTheDay.stream()
                    .map(e -> timeOfTheDay.size() < weatherSize ? Arrays.asList(e.text(), e.text()) : Collections.singletonList(e.text()))
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());

            List<String> items = IntStream.rangeClosed(1, weatherSize).boxed().map(i -> ".p" + i).collect(Collectors.toList());
            Map<String, String> timeToExactTime = IntStream.range(0, items.size()).boxed()
                    .collect(Collectors.toMap(items::get, strings::get));

            List<Weather> weatherList = items.stream().map(e ->
                    Weather.newBuilder()
                            .time(timeToExactTime.get(e))
                            .exactTime(exactTime.select(e).text().replace(" ", ""))
                            .image(icons.select(e + " .weatherImg").attr("src"))
                            .title(icons.select(e + " .weatherIco").attr("title"))
                            .temperature(temperature.select(e).text())
                            .probability(probability.select(e).text())
                            .build()
            ).collect(Collectors.toList());

            return WeatherData.newBuilder()
                    .dayName(header.select(".day-link").text())
                    .date(header.select(".date").text() + " " + header.select(".month").text())
                    .min(header.select(".min span").text())
                    .max(header.select(".max span").text())
                    .weathers(weatherList)
                    .build();
        }).collect(Collectors.toList());
    }
}
