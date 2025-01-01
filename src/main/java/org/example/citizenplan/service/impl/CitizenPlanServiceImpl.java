package org.example.citizenplan.service.impl;

import org.example.citizenplan.dto.SearchResult;
import org.example.citizenplan.model.CitizenPlan;
import org.example.citizenplan.repo.CitizenPlanRepo;
import org.example.citizenplan.service.CitizenPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CitizenPlanServiceImpl implements CitizenPlanService {

    @Autowired
    private CitizenPlanRepo citizenPlanRepo;

    @Override
    public List<String> getPlanName() {
        return List.of();
    }

    @Override
    public List<String> getPlanStatus() {
        return List.of();
    }

    @Override
    public List<CitizenPlan> getCitizenPlan(SearchResult searchResult) {
        return List.of();
    }

    @Override
    public boolean exportToExcel() {
        return false;
    }

    @Override
    public boolean exportToPdf() {
        return false;
    }
}
