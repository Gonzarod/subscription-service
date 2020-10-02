package com.evertix.subscriptionservice.service.impl;

import com.evertix.subscriptionservice.entities.Subscription;
import com.evertix.subscriptionservice.repository.PlanRepository;
import com.evertix.subscriptionservice.repository.SubscriptionRepository;
import com.evertix.subscriptionservice.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImp implements SubscriptionService {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    PlanRepository planRepository;

    @Override
    public Page<Subscription> getAllSubscriptions(Pageable pageable) {
        return subscriptionRepository.findAll(pageable);
    }
/*
    @Override
    public Page<Subscription> getUsersSubscriptions(Long userId, Pageable pageable) {
        return subscriptionRepository.findAllByUserId(userId,pageable);
    }

    @Override
    public Subscription subscribeToPlan(Long userId, Long planId) {
        return userRepository.findById(userId).map(user -> {
            return planRepository.findById(planId).map(plan -> {
             Subscription subscription = new Subscription();
             subscription.setActive(true);
             subscription.setUser(user);
             subscription.setPlan(plan);
             return subscriptionRepository.save(subscription);
            }).orElseThrow(()->new ResourceNotFoundException("Plan with id: "+planId+" not found"));
        }).orElseThrow(()->new ResourceNotFoundException("User with id: "+userId+" not found"));
    }

    @Override
    public Subscription unsubscribeToPlan(Long userId, Long planId, Long subscriptionId) {
        return userRepository.findById(userId).map(user -> {
            return planRepository.findById(planId).map(plan -> {
                return subscriptionRepository.findById(subscriptionId).map(subscription -> {
                    subscription.setActive(false);
                    return subscriptionRepository.save(subscription);
                }).orElseThrow(()->new ResourceNotFoundException("Subscription with id: "+subscriptionId+" not found"));
            }).orElseThrow(()->new ResourceNotFoundException("Plan with id: "+planId+" not found"));
        }).orElseThrow(()->new ResourceNotFoundException("User with id: "+userId+" not found"));
    }

    @Override
    public ResponseEntity<?> deleteSubscription(Long userId, Long planId, Long subscriptionId) {
        return userRepository.findById(userId).map(user -> {
            return planRepository.findById(planId).map(plan -> {
                return subscriptionRepository.findById(subscriptionId).map(subscription -> {
                    subscriptionRepository.delete(subscription);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()->new ResourceNotFoundException("Subscription with id: "+subscriptionId+" not found"));
            }).orElseThrow(()->new ResourceNotFoundException("Plan with id: "+planId+" not found"));
        }).orElseThrow(()->new ResourceNotFoundException("User with id: "+userId+" not found"));
    }

 */
}
