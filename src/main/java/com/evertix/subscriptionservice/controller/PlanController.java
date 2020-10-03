package com.evertix.subscriptionservice.controller;

import com.evertix.subscriptionservice.entities.Plan;
import com.evertix.subscriptionservice.resource.PlanResource;
import com.evertix.subscriptionservice.resource.PlanSaveResource;
import com.evertix.subscriptionservice.service.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Plan", description = "API")
@RestController
@RequestMapping("/api")
public class PlanController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PlanService planService;

    @GetMapping("/plans")
    @Operation(summary = "Get All Plans", description = "Get Plans", tags = {"Plan"},
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
    public Page<PlanResource> getAllPlans(@PageableDefault @Parameter(hidden = true) Pageable pageable){
        Page<Plan> planPage = planService.getAllPlans(pageable);
        List<PlanResource> resources = planPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, planPage.getTotalElements());
    }

    private Plan convertToEntity(PlanSaveResource resource){return mapper.map(resource, Plan.class);}
    private PlanResource convertToResource(Plan entity){return mapper.map(entity, PlanResource.class);}
}
