package com.interview.points.controller;

import com.interview.points.entity.Tier;
import com.interview.points.service.tier.TierService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/tier")
public class TierController {

    private final TierService tierService;

    @Operation(summary = "Retrieve all tiers", description = "Service responsible for retrieve all tiers", tags = "Tier")
    @GetMapping(value = "/getAll")
    public ResponseEntity<List<Tier>> getAll() {
        return tierService.getTiers();
    }

    @Operation(summary = "Create a new tier", description = "Service responsible for create a new tier", tags = "Tier")
    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody Tier tier) {
        return tierService.addTier(tier);
    }

}
