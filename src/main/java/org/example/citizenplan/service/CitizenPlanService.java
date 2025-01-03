package org.example.citizenplan.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.citizenplan.dto.SearchResult;
import org.example.citizenplan.model.CitizenPlan;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface CitizenPlanService {

    public List<String> getPlanName();

    public List<String> getPlanStatus();

    public List<CitizenPlan> getCitizenPlan(SearchResult searchResult);


    /**
     * We are have to add  Apache Poi ooxml to generate Excel pdf 
     * @return
     */
    boolean exportToExcel(HttpServletResponse response) throws IOException;

    boolean exportToPdf(HttpServletResponse response) throws IOException;



}
