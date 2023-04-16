package com.tenetz.tenetz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Set;

@Data
@Document(collection = "BranchDetail")
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class BranchDetails {
    @Id
    private String branchId;
    @NonNull
    private String ownerId;
    @NonNull
    private String propertyId;
    private String branchName;
    private String addressLine1;
    private String addressLine2;
    private String landmark;
    private String pincode;
    private String city;
    private String state;
    private String managerName;
    private String managerContactNum;
    private String category;
    private String noticePeriod;
    private String lockInPeriod;
    private String mealIncluded;
    private String mealAmount;
    private boolean visitorsAllowed;
    private String timings;
    private String electricityType;
    private String electricityCharges;
    @DocumentReference
    private Set<String> roomDetails;
    private List<String> images;
}
