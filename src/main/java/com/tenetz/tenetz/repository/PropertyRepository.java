package com.tenetz.tenetz.repository;

import com.tenetz.tenetz.dto.PropertyDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends MongoRepository<PropertyDetail, String> {
    List<PropertyDetail> findByOwnerId(String ownerId);
}
