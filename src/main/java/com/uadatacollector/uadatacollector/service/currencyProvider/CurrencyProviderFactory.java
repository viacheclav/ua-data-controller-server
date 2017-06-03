package com.uadatacollector.uadatacollector.service.currencyProvider;

/**
 * Created by slavik on 2017-06-03.
 */
public class CurrencyProviderFactory {

    public static CurrencyProvider getInstance(String currency){
        switch (currency) {
            case "UAH": return new CurrencyProviderUAHImpl();
            case "PLN": return new CurrencyProviderPLNImpl();
            default: throw new IllegalArgumentException("there is no provider for such type");
        }
    }
}
