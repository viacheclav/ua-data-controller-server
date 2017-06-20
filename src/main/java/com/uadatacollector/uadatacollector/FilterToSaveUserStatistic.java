package com.uadatacollector.uadatacollector;

import com.uadatacollector.uadatacollector.adminService.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by slavik on 2017-06-20.
 */
public class FilterToSaveUserStatistic implements Filter {


    private AdminService adminService;

    public FilterToSaveUserStatistic(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        adminService.saveUserStatisticData(request);
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {}

    @Override
    public void init(FilterConfig arg0) throws ServletException {}

}