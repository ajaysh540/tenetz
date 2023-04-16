package com.tenetz.tenetz.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PropertyDetailRequest {
    private String ownerId;
    private String ownerContactNum;
    private String propertyId;
    private String propertyName;
    private String propertyContactNum;
    private String ownerName;
    private String propertyType;
}
