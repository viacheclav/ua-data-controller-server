package com.uadatacollector.uadatacollector.adminService;


/**
 * Created by slavik on 2017-06-20.
 */
public interface AdminService {

    void saveUserStatisticData(String ipAddress, String ipAddressByHeader, String requestURI);
}
