package com.uadatacollector.uadatacollector.service.entity;

/**
 * Created by slavik on 2017-06-03.
 */
public class BankRate {
    private final String title;
    private final double buy;
    private final double sell;
    private final String currency;

    private BankRate(Builder builder) {
        title = builder.title;
        buy = builder.buy;
        sell = builder.sell;
        currency = builder.currency;
    }

    public static Builder newBuilder() {
        return new Builder();
    }



    public String getTitle() {
        return title;
    }

    public double getBuy() {
        return buy;
    }

    public double getSell() {
        return sell;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "BankRate{" +
                "title='" + title + '\'' +
                ", buy=" + buy +
                ", sell=" + sell +
                ", currency='" + currency + '\'' +
                '}';
    }

    public static final class Builder {
        private String title;
        private double buy;
        private double sell;
        private String currency;

        private Builder() {
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder buy(double val) {
            buy = val;
            return this;
        }

        public Builder sell(double val) {
            sell = val;
            return this;
        }

        public Builder currency(String val) {
            currency = val;
            return this;
        }

        public BankRate build() {
            return new BankRate(this);
        }
    }
}
