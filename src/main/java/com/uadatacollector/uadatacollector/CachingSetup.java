package com.uadatacollector.uadatacollector;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.stereotype.Component;

import javax.cache.CacheManager;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.Duration;
import javax.cache.expiry.TouchedExpiryPolicy;

import static java.util.concurrent.TimeUnit.MINUTES;

/**
 * Created by slavik on 2017-05-27.
 */
@Component
public class CachingSetup implements JCacheManagerCustomizer {
    @Override
    public void customize(CacheManager cacheManager) {
        cacheManager.createCache("weather", new MutableConfiguration<>()
                .setExpiryPolicyFactory(TouchedExpiryPolicy.factoryOf(new Duration(MINUTES, 60)))
                .setStoreByValue(false)
                .setStatisticsEnabled(true));

        cacheManager.createCache("currency", new MutableConfiguration<>()
                .setExpiryPolicyFactory(TouchedExpiryPolicy.factoryOf(new Duration(MINUTES, 60)))
                .setStoreByValue(false)
                .setStatisticsEnabled(true));
    }
}
