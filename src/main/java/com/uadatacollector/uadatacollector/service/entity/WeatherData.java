package com.uadatacollector.uadatacollector.service.entity;

import java.util.List;

/**
 * Created by slavik on 2017-06-04.
 */
public class WeatherData {
    private final String dayName;
    private final String date;
    private final String min;
    private final String max;
    private final List<Weather> weathers;

    private WeatherData(Builder builder) {
        dayName = builder.dayName;
        date = builder.date;
        min = builder.min;
        max = builder.max;
        weathers = builder.weathers;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getDayName() {
        return dayName;
    }

    public String getDate() {
        return date;
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
                ", date='" + date + '\'' +
                ", min='" + min + '\'' +
                ", max='" + max + '\'' +
                ", weathers=" + weathers +
                '}';
    }


    public static final class Builder {
        private String dayName;
        private String date;
        private String min;
        private String max;
        private List<Weather> weathers;

        private Builder() {
        }

        public Builder dayName(String val) {
            dayName = val;
            return this;
        }

        public Builder date(String val) {
            date = val;
            return this;
        }

        public Builder min(String val) {
            min = val;
            return this;
        }

        public Builder max(String val) {
            max = val;
            return this;
        }

        public Builder weathers(List<Weather> val) {
            weathers = val;
            return this;
        }

        public WeatherData build() {
            return new WeatherData(this);
        }
    }
}