package com.evertix.subscriptionservice.repository;


import com.evertix.subscriptionservice.entities.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan,Long> {
    //Page<Plan> findAllByRole(String role, Pageable pageable);

}
