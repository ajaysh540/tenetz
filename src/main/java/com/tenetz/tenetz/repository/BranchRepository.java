package com.tenetz.tenetz.repository;

import com.tenetz.tenetz.dto.BranchDetails;
import com.tenetz.tenetz.dto.BranchDetailsRequest;
import com.tenetz.tenetz.dto.PropertyDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends MongoRepository<BranchDetailsRequest, String> {
    List<BranchDetailsRequest> findByOwnerIdAndPropertyId(String ownerId, String propertyId);
}
