package org.example.citizenplan.service;

import org.example.citizenplan.dto.SearchResult;
import org.example.citizenplan.model.CitizenPlan;

import java.util.List;

public interface CitizenPlanService {

    public List<String> getPlanName();

    public List<String> getPlanStatus();

    public List<CitizenPlan> getCitizenPlan(SearchResult searchResult);

    boolean exportToExcel();

    boolean exportToPdf();



}
