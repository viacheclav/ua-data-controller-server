package com.uadatacollector.uadatacollector.service.entity;

/**
 * Created by slavik on 2017-06-04.
 */
public class Weather {
    private final String time;
    private final String title;
    private final String temperature;
    private final String image;
    private final String exactTime;
    private final String probability;

    private Weather(Builder builder) {
        time = builder.time;
        title = builder.title;
        temperature = builder.temperature;
        image = builder.image;
        exactTime = builder.exactTime;
        probability = builder.probability;
    }

    public static Builder newBuilder() {
        return new Builder();
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

    public String getExactTime() {
        return exactTime;
    }

    public String getProbability() {
        return probability;
    }


    public static final class Builder {
        private String time;
        private String title;
        private String temperature;
        private String image;
        private String exactTime;
        private String probability;

        private Builder() {
        }

        public Builder time(String val) {
            time = val;
            return this;
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder temperature(String val) {
            temperature = val;
            return this;
        }

        public Builder image(String val) {
            image = val;
            return this;
        }

        public Builder exactTime(String val) {
            exactTime = val;
            return this;
        }

        public Builder probability(String val) {
            probability = val;
            return this;
        }

        public Weather build() {
            return new Weather(this);
        }
    }

    @Override
    public String toString() {
        return "Weather{" +
                "time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", temperature='" + temperature + '\'' +
                ", image='" + image + '\'' +
                ", exactTime='" + exactTime + '\'' +
                ", probability='" + probability + '\'' +
                '}';
    }
}