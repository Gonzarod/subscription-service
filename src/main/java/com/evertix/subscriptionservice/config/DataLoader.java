package com.evertix.subscriptionservice.config;


import com.evertix.subscriptionservice.entities.Plan;
import com.evertix.subscriptionservice.entities.Subscription;
import com.evertix.subscriptionservice.repository.PlanRepository;
import com.evertix.subscriptionservice.repository.SubscriptionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Component
public class DataLoader {

    private PlanRepository planRepository;
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    public DataLoader(SubscriptionRepository subscriptionRepository,PlanRepository planRepository) {
        this.subscriptionRepository=subscriptionRepository;
        this.planRepository=planRepository;
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


        List<Subscription> subscriptions = new ArrayList<Subscription>();
        subscriptions.add(new Subscription((long) 1,plan1,true));
        subscriptions.add(new Subscription((long) 3,plan2,true));
        subscriptions.add(new Subscription((long) 2,plan3,true));

        
        //Subscription
        this.subscriptionRepository.saveAll(subscriptions);



    }
}