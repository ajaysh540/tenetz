package com.tenetz.tenetz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "RoomDetail")
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomDetailRequest {

    @Id
    private String id;
    private String branchId;

    private String sharingType;
    private List<String> services;
    private List<String> amenities;
    private String rentAmount;
    private String securityAmount;

}
