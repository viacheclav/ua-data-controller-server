package com.uadatacollector.uadatacollector;


import com.mongodb.MongoClient;
import com.uadatacollector.uadatacollector.adminService.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.servlet.Filter;


/**
 * Created by slavik on 2017-05-20.
 */
@Configuration
@ComponentScan("com")
@EnableAsync
public class AppConfig {

//    @Bean
//    public MongoClient mongo() throws Exception {
//        return new MongoClient("localhost", 27017);
//    }

    @Autowired
    private  AutowireCapableBeanFactory beanFactory;

    @Bean
    public FilterRegistrationBean myFilter(AdminService adminService) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        FilterToSaveUserStatistic myFilter = new FilterToSaveUserStatistic(adminService);
        beanFactory.autowireBean(myFilter);
        registration.setFilter(myFilter);
        registration.addUrlPatterns("/","/api/*");
        return registration;
    }
}

