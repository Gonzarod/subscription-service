package com.evertix.subscriptionservice.service;

import com.evertix.subscriptionservice.entities.Subscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubscriptionService {
    List<Subscription> getAllSubscriptions();
    Page<Subscription> getAllSubscriptionsPage(Pageable pageable);
    //Page<Subscription> getUsersSubscriptions(Long userId, Pageable pageable);
    //Subscription subscribeToPlan(Long userId,Long planId); // User (Teacher, Student)
    //Subscription unsubscribeToPlan(Long userId, Long planId, Long subscriptionId); // User (Teacher, Student)
    //ResponseEntity<?> deleteSubscription(Long userId, Long planId, Long subscriptionId);
}
