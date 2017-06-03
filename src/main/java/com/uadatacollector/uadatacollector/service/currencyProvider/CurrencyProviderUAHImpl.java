package com.uadatacollector.uadatacollector.service.currencyProvider;

import com.uadatacollector.uadatacollector.service.entity.BankRate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by slavik on 2017-06-03.
 */
@Component
public class CurrencyProviderUAHImpl implements CurrencyProvider {

    private static final String DEFAULT_URL_RESOURCE_UAH = "http://finance.i.ua";

    @Override
    public List<BankRate> getRates(List<String> currencies) {
        List<BankRate> bankRates = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(DEFAULT_URL_RESOURCE_UAH).get();
            bankRates = getRates(doc, currencies);
        } catch (IOException e){
            System.out.println("e = " + e);
        }
        return bankRates;
    }

    private List<BankRate> getRates(Document doc, List<String> currencies) {
        List<String> currenciesLowerCase = currencies.stream().map(String::toLowerCase).collect(Collectors.toList());
        return currenciesLowerCase.stream().map(currency ->
                doc.select(".bank_rates_" + currency + " tr").stream().map(element ->
                        BankRate.newBuilder().title(element.select(".td-title a").text())
                                .buy(Double.parseDouble(element.select(".buy_rate span span").text()))
                                .sell(Double.parseDouble(element.select(".sell_rate span span").text()))
                                .currency(currency)
                                .build()).collect(Collectors.toList())
        ).flatMap(Collection::stream).collect(Collectors.toList());
    }

}
