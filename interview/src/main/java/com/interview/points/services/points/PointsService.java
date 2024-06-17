package com.interview.points.services.points;

import com.interview.points.models.UserModel;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

public interface PointsService {

    ResponseEntity<?> addPoints(Integer id, BigDecimal points);

    ResponseEntity<?> getPoints(Integer id);

    ResponseEntity<?> removePoints(Integer id, BigDecimal points);

}
