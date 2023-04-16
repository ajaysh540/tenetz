package com.tenetz.tenetz.repository;

import com.tenetz.tenetz.dto.RoomDetailRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomsRepository extends MongoRepository<RoomDetailRequest, String> {
    List<RoomDetailRequest> findByBranchId(String branchId);

    void deleteByBranchId(String branchId);
}
