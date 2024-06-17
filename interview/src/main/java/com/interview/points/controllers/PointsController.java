package com.interview.points.controllers;

import com.interview.points.services.points.PointsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/points")
public class PointsController {

    private final PointsService pointsService;

    @PostMapping(value = "/addPoints")
    public ResponseEntity<?> addPoints(@RequestParam Integer id, @RequestParam BigDecimal points) {
        return pointsService.addPoints(id, points);
    }

}
