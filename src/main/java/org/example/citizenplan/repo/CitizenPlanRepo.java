package org.example.citizenplan.repo;

import org.example.citizenplan.model.CitizenPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CitizenPlanRepo extends JpaRepository<CitizenPlan, Integer> {

    @Query(value = "select distinct (plan_name) from citizenplan.citizen_plans_info" , nativeQuery = true)
    public List<String> getPlanName();


    @Query(value="select distinct (plan_status) from citizenplan.citizen_plans_info ", nativeQuery = true)
    public List<String> getPlanStatus();


}
