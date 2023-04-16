package com.tenetz.tenetz.repository;

import com.tenetz.tenetz.dto.OwnerDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends MongoRepository<OwnerDetails, String> {
}
