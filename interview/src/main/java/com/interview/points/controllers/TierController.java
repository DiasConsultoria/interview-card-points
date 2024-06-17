package com.interview.points.controllers;

import com.interview.points.models.TierModel;
import com.interview.points.models.UserModel;
import com.interview.points.services.points.PointsService;
import com.interview.points.services.tier.TierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/tier")
public class TierController {

    private final TierService tierService;

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<TierModel>> getAll() {
        return tierService.getTiers();
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody TierModel tier) {
        return tierService.addTier(tier);
    }

}
