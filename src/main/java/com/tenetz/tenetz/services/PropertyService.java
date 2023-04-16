package com.tenetz.tenetz.services;

import com.tenetz.tenetz.dto.OwnerDetails;
import com.tenetz.tenetz.dto.PropertyDetail;
import com.tenetz.tenetz.dto.PropertyDetailRequest;
import com.tenetz.tenetz.repository.OwnerRepository;
import com.tenetz.tenetz.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PropertyService {
    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    PropertyRepository propertyRepository;
    public Map<String, String> createNewAccount(PropertyDetailRequest propertyDetailRequest){
        OwnerDetails newOwner = new OwnerDetails();
        newOwner.setOwnerName(propertyDetailRequest.getOwnerName());
        newOwner.setOwnerId(propertyDetailRequest.getOwnerId());
        newOwner.setCreatedOn(LocalDate.now());
        newOwner.setContactNumber(propertyDetailRequest.getOwnerContactNum());
        newOwner.setLastLogin(LocalDate.now());
        System.out.println(ownerRepository.save(newOwner));

        PropertyDetail propertyDetail = new PropertyDetail();
        propertyDetail.setOwnerId(propertyDetailRequest.getOwnerId());
        propertyDetail.setPropertyName(propertyDetailRequest.getPropertyName());
        propertyDetail.setPropertyType(propertyDetailRequest.getPropertyType());
        propertyDetail.setPropertyContactNum(propertyDetailRequest.getPropertyContactNum());

        PropertyDetail propertyDetail1 = propertyRepository.save(propertyDetail);
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("propertyId", propertyDetail1.getPropertyId());
        return responseMap;
    }
    public List<PropertyDetail> getAllProperties(String ownerId){
        return propertyRepository.findByOwnerId(ownerId);

    }
}
