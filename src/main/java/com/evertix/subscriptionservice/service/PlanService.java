package com.evertix.subscriptionservice.service;

import com.evertix.subscriptionservice.entities.Plan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlanService {
    List<Plan> getAllPlans();
    Page<Plan> getAllPlansPage(Pageable pageable);
    //Plan getPlanById(Long planId);

    //Plan createPlan(Plan plan);
    //Plan updatePlan(Long planId, Plan planDetails);
    //ResponseEntity<?> deletePlan(Long planId);

}
