package com.tenetz.tenetz.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "PropertyDetail")
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class PropertyDetail {
    @JsonAlias("property_id")
    @Id
    private String propertyId;
    @JsonAlias("owner_id")
    private String ownerId;
    @JsonAlias("property_name")
    private String propertyName;
    @JsonAlias("property_type")
    private String propertyType;
    @JsonAlias("property_contact_num")
    private String propertyContactNum;
    @JsonAlias("gst_number")
    private String gstNumber;
}
