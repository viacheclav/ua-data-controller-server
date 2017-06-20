package com.uadatacollector.uadatacollector.adminService.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * Created by slavik on 2017-06-20.
 */
@Document(collection = "userStatisticData")
public class UserStatisticData {

    @Id
    private String id;
    private String ip;
    private String ipAddressByHeader;
    private String country;
    private String city;
    private LocalDateTime localDateTime;
    private LocalDateTime utcDateTime;
    private String countryCode;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIpAddressByHeader() {
        return ipAddressByHeader;
    }

    public void setIpAddressByHeader(String ipAddressByHeader) {
        this.ipAddressByHeader = ipAddressByHeader;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public LocalDateTime getUtcDateTime() {
        return utcDateTime;
    }

    public void setUtcDateTime(LocalDateTime utcDateTime) {
        this.utcDateTime = utcDateTime;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "UserStatisticData{" +
                "id='" + id + '\'' +
                ", ip='" + ip + '\'' +
                ", ipAddressByHeader='" + ipAddressByHeader + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", localDateTime=" + localDateTime +
                ", utcDateTime=" + utcDateTime +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}
