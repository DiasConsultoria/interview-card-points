package com.interview.points.services.points;

import com.interview.points.records.PointsRecord;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

public interface PointsService {

    /**
     *
     * @param id -> User primary key
     * @param points -> Amount of points to issue
     */
    ResponseEntity<String> issuePointsService(Integer id, BigDecimal points);

    /**
     *
     * @param id -> User primary key
     * @return -> A record containing username, cpf, tier and points
     */
    ResponseEntity<PointsRecord> getPoints(Integer id);

    /**
     * @param id -> User primary key
     * @param points -> Amount of points to redeem
     */
    ResponseEntity<String> redeemPointsService(Integer id, BigDecimal points);

}
