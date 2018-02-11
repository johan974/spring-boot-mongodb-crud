package com.djamware.angular.repositories;

import org.springframework.data.repository.CrudRepository;
import com.djamware.angular.security.MongoUserDetails;

public interface MongoSecurityRepository extends CrudRepository<MongoUserDetails, String> {
    MongoUserDetails findByUsername(String username);

    @Override
    void delete(MongoUserDetails deleted);
}
