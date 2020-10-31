package com.evertix.subscriptionservice.controller;

import com.evertix.subscriptionservice.entities.Subscription;
import com.evertix.subscriptionservice.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Subscription", description = "API")
@RestController
@RequestMapping("/api/subs")
@CrossOrigin(origins = "*")
public class SubscriptionController {

    @Autowired
    SubscriptionService subscriptionService;

    @GetMapping("/")
    @Operation(summary = "Get All Subscriptions", description = "Get All Subscription", tags = {"Subscription"})
    public List<Subscription> getAllSubscriptions() {
        return this.subscriptionService.getAllSubscriptions();
    }

    @GetMapping("/page")
    @Operation(summary = "Get All Subscriptions page", description = "Get All Subscription Details page", tags = {"Subscription"},
            parameters = {
                    @Parameter(in = ParameterIn.QUERY
                            , description = "Page you want to retrieve (0..N)"
                            , name = "page"
                            , content = @Content(schema = @Schema(type = "integer", defaultValue = "0"))),
                    @Parameter(in = ParameterIn.QUERY
                            , description = "Number of records per page."
                            , name = "size"
                            , content = @Content(schema = @Schema(type = "integer", defaultValue = "20"))),
                    @Parameter(in = ParameterIn.QUERY
                            , description = "Sorting criteria in the format: property(,asc|desc). "
                            + "Default sort order is ascending. " + "Multiple sort criteria are supported."
                            , name = "sort"
                            , content = @Content(array = @ArraySchema(schema = @Schema(type = "string"))))
            })
    public Page<Subscription> getAllSubscriptions(@PageableDefault @Parameter(hidden = true) Pageable pageable) {
        return this.subscriptionService.getAllSubscriptionsPage(pageable);
    }



}
