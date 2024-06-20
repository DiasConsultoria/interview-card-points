package com.interview.points.service.points;

import com.interview.points.record.IssueRecord;
import com.interview.points.record.PointsRecord;
import com.interview.points.record.RedeemRecord;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

public interface PointsService {

    /**
     *
     * @param id -> User primary key
     * @param points -> Amount of points to issue
     */
    ResponseEntity<IssueRecord> issuePointsService(Integer id, BigDecimal points);

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
    ResponseEntity<RedeemRecord> redeemPointsService(Integer id, BigDecimal points);

}
