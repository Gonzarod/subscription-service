package com.evertix.subscriptionservice.service.impl;

import com.evertix.subscriptionservice.entities.Subscription;
import com.evertix.subscriptionservice.model.User;
import com.evertix.subscriptionservice.repository.PlanRepository;
import com.evertix.subscriptionservice.repository.SubscriptionRepository;
import com.evertix.subscriptionservice.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImp implements SubscriptionService {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Autowired
    PlanRepository planRepository;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll().stream().map(subscription -> {
            //User student=restTemplate.getForObject("https://user-service/api/users/"+review.getStudentId(),User.class);
            //User teacher=restTemplate.getForObject("https://user-service/api/users/"+ review.getTeacherId(),User.class);
            User student=restTemplate.getForObject("http://tutofast-user-service.eastus.azurecontainer.io:8085/api/users/"+subscription.getUserId(),User.class);

            subscription.setUsermodel(student);
            return subscription;
        }).collect(Collectors.toList());
    }

    @Override
    public Page<Subscription> getAllSubscriptionsPage(Pageable pageable) {

        Page<Subscription> page=subscriptionRepository.findAll(pageable);
        List<Subscription> result=page.getContent().stream().map(subscription -> {
            //User student=restTemplate.getForObject("https://user-service/api/users/"+ subscription.getTeacherId()+"/",User.class);
            User student=restTemplate.getForObject("http://tutofast-user-service.eastus.azurecontainer.io:8085/api/users/"+subscription.getUserId(),User.class);
            subscription.setUsermodel(student);
            return subscription;
        }).collect(Collectors.toList());
        return new PageImpl<>(result,pageable, page.getTotalElements());

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
