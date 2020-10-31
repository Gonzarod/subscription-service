package com.evertix.subscriptionservice.config;


import com.evertix.subscriptionservice.entities.Plan;
import com.evertix.subscriptionservice.entities.Subscription;
import com.evertix.subscriptionservice.model.User;
import com.evertix.subscriptionservice.repository.PlanRepository;
import com.evertix.subscriptionservice.repository.SubscriptionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Component
public class DataLoader {

    private PlanRepository planRepository;
    private SubscriptionRepository subscriptionRepository;
    private RestTemplate restTemplate;

    @Autowired
    public DataLoader(SubscriptionRepository subscriptionRepository,PlanRepository planRepository, RestTemplate restTemplate) {
        this.subscriptionRepository=subscriptionRepository;
        this.planRepository=planRepository;
        this.restTemplate=restTemplate;
        LoadData();
    }

    private void LoadData() {
        //Plan
        Plan plan1=new Plan("Basic Plan", (short) 15, new BigDecimal("100.00"));
        Plan plan2=new Plan("Platinum Plan", (short) 20, new BigDecimal("150.0"));
        Plan plan3=new Plan("Golden Plan", (short) 30, new BigDecimal("200.0"));

        List<Plan> plans = new ArrayList<Plan>();
        plans.add(plan1);
        plans.add(plan2);
        plans.add(plan3);

        this.planRepository.saveAll(plans);

        User userStudent = restTemplate.getForObject("https://tutofast-user-service.herokuapp.com/api/users/username/jesus.student",User.class);
        List<Subscription> subscriptions = new ArrayList<Subscription>();
        subscriptions.add(new Subscription(userStudent.getId(), plan1,false));
        subscriptions.add(new Subscription(userStudent.getId(),plan2,true));

        //Subscription
        this.subscriptionRepository.saveAll(subscriptions);



    }
}