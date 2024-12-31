package org.example.citizenplan.repo;

import org.example.citizenplan.model.CitizenPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitizenPlanRepo extends JpaRepository<CitizenPlan, Integer> {
}
