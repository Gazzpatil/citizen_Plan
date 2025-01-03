package org.example.citizenplan.dto;


import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Data
public class SearchResult {

    private String planName;

    private String planStatus;

    private String gender;

    private String planStartDate;

    private String planEndDate;

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(String planStatus) {
        this.planStatus = planStatus;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPlanStartDate() {
        return planStartDate;
    }

    public void setPlanStartDate(String planStartDate) {
        this.planStartDate = planStartDate;
    }

    public String getPlanEndDate() {
        return planEndDate;
    }

    public void setPlanEndDate(String planEndDate) {
        this.planEndDate = planEndDate;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "planName='" + planName + '\'' +
                ", planStatus='" + planStatus + '\'' +
                ", gender='" + gender + '\'' +
                ", planStartDate='" + planStartDate + '\'' +
                ", planEndDate='" + planEndDate + '\'' +
                '}';
    }
}
