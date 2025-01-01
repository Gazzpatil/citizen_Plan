package org.example.citizenplan.dto;


import lombok.Data;

@Data
public class SearchResult {

    private String planName;

    private String planStatus;

    private String gender;

    private String planStartDate;

    private String planEndDate;

}
