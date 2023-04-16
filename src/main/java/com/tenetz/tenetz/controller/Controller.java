package com.tenetz.tenetz.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tenetz.tenetz.dto.BranchDetailsRequest;
import com.tenetz.tenetz.dto.PropertyDetail;
import com.tenetz.tenetz.dto.PropertyDetailRequest;
import com.tenetz.tenetz.dto.RoomDetailRequest;
import com.tenetz.tenetz.services.BranchService;
import com.tenetz.tenetz.services.PropertyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
public class Controller {
    @Autowired
    PropertyService propertyService;
    @Autowired
    BranchService branchService;

    @GetMapping("/health")
    public String health() {
        System.out.println("Test");
        return "{status:healthy}";
    }

    @PostMapping("/property")
    public Map<String, String> addNewProperty(@RequestBody PropertyDetailRequest propertyDetailRequest) {
        return propertyService.createNewAccount(propertyDetailRequest);
    }

    @PostMapping("/branch")
    public ResponseEntity<BranchDetailsRequest> addNewBranch(@RequestBody BranchDetailsRequest branchDetailsRequest) throws JsonProcessingException {
        if (StringUtils.isBlank(branchDetailsRequest.getOwnerId()) || StringUtils.isBlank(branchDetailsRequest.getPropertyId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "OwnerId and PropertyId are required");
        }
        return ResponseEntity.created(URI.create("/branch")).body(branchService.addNewBranch(branchDetailsRequest));
    }

    @PostMapping("/edit/branch")
    public ResponseEntity<BranchDetailsRequest> editBranch(@RequestBody BranchDetailsRequest branchDetailsRequest) {
        if (StringUtils.isBlank(branchDetailsRequest.getOwnerId()) || StringUtils.isBlank(branchDetailsRequest.getPropertyId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "OwnerId and PropertyId are required");
        }
        return ResponseEntity.created(URI.create("/branch")).body(branchService.editBranch(branchDetailsRequest));
    }

    @PostMapping("/addRoom/{branchId}")
    public ResponseEntity<BranchDetailsRequest> addRoom(@RequestBody List<RoomDetailRequest> roomDetailRequest, @PathVariable String branchId) {
        if (StringUtils.isBlank(branchId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Branch ID is required");
        }
        return ResponseEntity.created(URI.create("/addRoom")).body(branchService.addRoomsToBranch(roomDetailRequest, branchId));
    }

    @PostMapping("/editRoom/{branchId}")
    public ResponseEntity<BranchDetailsRequest> editRoom(@RequestBody List<RoomDetailRequest> roomDetailRequest, @PathVariable String branchId) {
        if (StringUtils.isBlank(branchId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Branch ID is required");
        }
        return ResponseEntity.created(URI.create("/addRoom")).body(branchService.addRoomsToBranch(roomDetailRequest, branchId));
    }

    @GetMapping("/property/{ownerId}")
    public List<PropertyDetail> getAllPropertiesByOwnerId(@PathVariable String ownerId) {
        return propertyService.getAllProperties(ownerId);
    }

    @GetMapping("/branch")
    public List<BranchDetailsRequest> getAllBranchesByOwnerId(@RequestParam String ownerId, @RequestParam String propertyId) {
        return branchService.getAllBranches(ownerId, propertyId);
    }

    @DeleteMapping("/branch/{branchId}")
    public void deleteBranchById(@PathVariable String branchId) {
        branchService.deleteBranch(branchId);
    }

    @DeleteMapping("/room/{roomId}")
    public void deleteByRoomId(@PathVariable String roomId){
        branchService.deleteRoom(roomId);
    }
}
