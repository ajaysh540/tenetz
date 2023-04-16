package com.tenetz.tenetz.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "OwnerDetails")
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDetails {
    @Id
    @JsonAlias("owner_id")
    private String ownerId;
    @JsonAlias("contact_number")
    private String contactNumber;
    @JsonAlias("owner_name")
    private String ownerName;
    @JsonAlias("created_on")
    private LocalDate createdOn = LocalDate.now();
    @JsonAlias("last_login")
    private LocalDate lastLogin;

}
