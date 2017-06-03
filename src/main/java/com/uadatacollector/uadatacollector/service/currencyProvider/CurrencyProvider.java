package com.uadatacollector.uadatacollector.service.currencyProvider;

import com.uadatacollector.uadatacollector.service.entity.BankRate;

import java.util.List;

/**
 * Created by slavik on 2017-06-03.
 */
public interface CurrencyProvider {

    List<BankRate> getRates(List<String> currencies);
}
