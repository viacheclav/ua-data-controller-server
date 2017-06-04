package com.uadatacollector.uadatacollector.service.entity;

/**
 * Created by slavik on 2017-06-04.
 */
public class Weather {
    private final String time;
    private final String title;
    private final String temperature;
    private final String image;

    public Weather(String time, String title, String temperature, String image) {
        this.time = time;
        this.title = title;
        this.temperature = temperature;
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", temperature='" + temperature + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}