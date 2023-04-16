package com.tenetz.tenetz.services;

import com.tenetz.tenetz.dto.BranchDetailsRequest;
import com.tenetz.tenetz.dto.RoomDetailRequest;
import com.tenetz.tenetz.repository.BranchRepository;
import com.tenetz.tenetz.repository.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchService {

    @Autowired
    BranchRepository branchRepository;
    @Autowired
    RoomsRepository roomsRepository;

    public BranchDetailsRequest addNewBranch(BranchDetailsRequest branchDetailsRequest) {
        List<RoomDetailRequest> roomDetailRequestList = branchDetailsRequest.getRoomDetails();
        branchDetailsRequest.setRoomDetails(roomsRepository.saveAll(roomDetailRequestList));
        BranchDetailsRequest detailsRequest = branchRepository.save(branchDetailsRequest);
        branchDetailsRequest.setRoomDetails(roomsRepository.saveAll(roomDetailRequestList.stream().peek(roomDetailRequest -> roomDetailRequest.setBranchId(detailsRequest.getBranchId())).collect(Collectors.toList())));
        return detailsRequest;
    }

    public List<BranchDetailsRequest> getAllBranches(String ownerId, String propertyId) {
        return branchRepository.findByOwnerIdAndPropertyId(ownerId, propertyId);
    }

    public BranchDetailsRequest editBranch(BranchDetailsRequest branchDetailsRequest) {
        BranchDetailsRequest existingDetails = branchRepository.findById(branchDetailsRequest.getBranchId()).get();
        branchDetailsRequest.setRoomDetails(existingDetails.getRoomDetails());
        return branchRepository.save(branchDetailsRequest);
    }

    public BranchDetailsRequest addRoomsToBranch(List<RoomDetailRequest> roomDetailRequest, String branchId) {
        List<RoomDetailRequest> newRoomDetails = roomsRepository.saveAll(roomDetailRequest.stream().peek(roomDetail -> roomDetail.setBranchId(branchId)).collect(Collectors.toList()));
        BranchDetailsRequest detailsRequest = branchRepository.findById(branchId).get();
        List<String> newRoomIds = detailsRequest.getRoomDetails().stream().map(RoomDetailRequest::getId).toList();
        detailsRequest.getRoomDetails().addAll(newRoomDetails.stream().filter(roomDetailRequest1 -> !newRoomIds.contains(roomDetailRequest1.getId())).toList());
        System.out.println(detailsRequest);
        System.out.println(newRoomDetails);
        branchRepository.save(detailsRequest);
        return detailsRequest;
    }

    public void deleteBranch(String branchId) {
        branchRepository.deleteById(branchId);
        roomsRepository.deleteByBranchId(branchId);
    }


    public void deleteRoom(String roomId) {
        RoomDetailRequest roomDetailRequest = roomsRepository.findById(roomId).get();
        BranchDetailsRequest branchDetails = branchRepository.findById(roomDetailRequest.getBranchId()).get();
        branchDetails.setRoomDetails(branchDetails.getRoomDetails().stream().filter(roomDetail -> !roomDetail.getId().equals(roomId)).toList());
        roomsRepository.deleteById(roomId);
        branchRepository.save(branchDetails);
    }
}
