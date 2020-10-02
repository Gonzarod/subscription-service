package com.evertix.subscriptionservice.resource;

import com.evertix.subscriptionservice.entities.Plan;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionResource {

    private Long id;

    private Boolean active;

    private Long userID;

    private Plan plan;

}
