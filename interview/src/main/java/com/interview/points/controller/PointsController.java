package com.interview.points.controller;

import com.interview.points.record.IssueRecord;
import com.interview.points.service.points.PointsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/points")
public class PointsController {

    private final PointsService pointsService;

    @Operation(summary = "Retrieve points from a user", description = "Service responsible for retrieve points from a user", tags = "Points")
    @GetMapping
    public ResponseEntity<?> getPoints(@RequestParam Integer id) {
        return pointsService.getPoints(id);
    }

    @Operation(summary = "Issue points to a user", description = "Service responsible to issue points to a user", tags = "Points")
    @PostMapping(value = "/issue")
    public ResponseEntity<IssueRecord> issuePoints(@RequestParam Integer id, @RequestParam BigDecimal points) {
        return pointsService.issuePointsService(id, points);
    }

    @Operation(summary = "Redeem points from a user", description = "Service responsible to redeem points from a user", tags = "Points")
    @PostMapping(value = "/redeem")
    public ResponseEntity<?> redeemPoints(@RequestParam Integer id, @RequestParam BigDecimal points) {
        return pointsService.redeemPointsService(id, points);
    }

}
