package com.uadatacollector.uadatacollector.service.entity;

import java.util.List;

/**
 * Created by slavik on 2017-06-04.
 */
public class WeatherData {
    private final String dayName;
    private final String data;
    private final String min;
    private final String max;
    private final List<Weather> weathers;

    public WeatherData(String dayName, String data, String min, String max, List<Weather> weathers) {
        this.dayName = dayName;
        this.data = data;
        this.min = min;
        this.max = max;
        this.weathers = weathers;
    }

    public String getDayName() {
        return dayName;
    }

    public String getData() {
        return data;
    }

    public String getMin() {
        return min;
    }

    public String getMax() {
        return max;
    }

    public List<Weather> getWeathers() {
        return weathers;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "dayName='" + dayName + '\'' +
                ", data='" + data + '\'' +
                ", min='" + min + '\'' +
                ", max='" + max + '\'' +
                ", weathers=" + weathers +
                '}';
    }
}