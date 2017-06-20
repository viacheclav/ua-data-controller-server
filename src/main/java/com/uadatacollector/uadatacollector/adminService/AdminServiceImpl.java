package com.uadatacollector.uadatacollector.adminService;

import com.uadatacollector.uadatacollector.adminService.dao.AdminDao;
import com.uadatacollector.uadatacollector.adminService.entity.UserStatisticData;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * Created by slavik on 2017-06-20.
 */
@Service
public class AdminServiceImpl implements AdminService {

    private final AdminDao adminDao;

    public AdminServiceImpl(AdminDao adminDao) {
        this.adminDao = adminDao;
    }

    @Async
    @Override
    public void saveUserStatisticData(HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        String ipAddressByHeader = request.getHeader("X-FORWARDED-FOR");
        GeoIpResult geoIpResult = getCountryByIp(ipAddress);

        Instant instant = Instant.now();
        LocalDateTime utcDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);

        String timeZone = geoIpResult.getTime_zone();
        ZoneId zoneId = ZoneId.of(timeZone);
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();

        UserStatisticData userStatisticData = new UserStatisticData();
        userStatisticData.setIp(ipAddress);
        userStatisticData.setIpAddressByHeader(ipAddressByHeader);
        userStatisticData.setCountry(geoIpResult.getCountry_name());
        userStatisticData.setCity(geoIpResult.getCity());
        userStatisticData.setLocalDateTime(localDateTime);
        userStatisticData.setUtcDateTime(utcDateTime);
        userStatisticData.setCountryCode(geoIpResult.getCountry_code());

        adminDao.save(userStatisticData);
    }


    private GeoIpResult getCountryByIp(String ip) {
        String url = "http://freegeoip.net/json/" + ip;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, GeoIpResult.class);
    }

    private static class GeoIpResult {
        private String ip;
        private String country_name;
        private String city;
        private String time_zone;
        private String country_code;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getCountry_name() {
            return country_name;
        }

        public void setCountry_name(String country_name) {
            this.country_name = country_name;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getTime_zone() {
            return time_zone;
        }

        public void setTime_zone(String time_zone) {
            this.time_zone = time_zone;
        }

        public String getCountry_code() {
            return country_code;
        }

        public void setCountry_code(String country_code) {
            this.country_code = country_code;
        }
    }

}
