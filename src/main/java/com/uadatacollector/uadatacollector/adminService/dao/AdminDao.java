package com.uadatacollector.uadatacollector.adminService.dao;

import com.uadatacollector.uadatacollector.adminService.entity.UserStatisticData;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by slavik on 2017-06-20.
 */
public interface AdminDao extends MongoRepository<UserStatisticData, Long> {
}
