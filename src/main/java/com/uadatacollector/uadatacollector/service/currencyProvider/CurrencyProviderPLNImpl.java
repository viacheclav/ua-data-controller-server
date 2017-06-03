package com.uadatacollector.uadatacollector.service.currencyProvider;

import com.uadatacollector.uadatacollector.service.entity.BankRate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by slavik on 2017-06-03.
 */
@Component
public class CurrencyProviderPLNImpl implements CurrencyProvider {

    private static final String DEFAULT_URL_RESOURCE_PLN = "http://www.kantor-exchange.pl/";

    @Override
    public List<BankRate> getRates(List<String> currencies) {
        List<BankRate> bankRates = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(DEFAULT_URL_RESOURCE_PLN).get();
            bankRates = getRates(doc, currencies);
        } catch (IOException e){
            System.out.println("e = " + e);
        }
        return bankRates;
    }

    private List<BankRate> getRates(Document doc, List<String> currencies) {
        return doc.select(".table-striped tr").stream()
                .filter(el -> !el.select(".currency span").isEmpty())
                .filter(el -> currencies.contains(el.select(".currency span").get(1).text().toLowerCase()))
                .map(element -> {
                    String currency = element.select(".currency span").get(1).text().toLowerCase();
                    List<Double> prices = element.select(".price").stream().map(el ->
                            Double.parseDouble(el.select("span").stream().map(Element::text).reduce("", String::concat).replace(",", "."))
                    ).collect(Collectors.toList());

                    Collections.sort(prices);

                    return BankRate.newBuilder()
                            .title("")
                            .currency(currency)
                            .buy(prices.get(0) / 100.0)
                            .sell(prices.get(1) / 100.0)
                            .build();
                }).collect(Collectors.toList());
    }

}