package com.interview.points.service.points;

import com.interview.points.entity.Tier;
import com.interview.points.entity.User;
import com.interview.points.record.PointsRecord;
import com.interview.points.repository.TierRepository;
import com.interview.points.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.redisson.api.RedissonClient;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PointsServiceImp implements PointsService{

    private static final Logger logger = LogManager.getLogger(PointsServiceImp.class);

    private final UserRepository userRepository;
    private final TierRepository tierRepository;
    private final RedissonClient redissonClient;


    @Override
    public ResponseEntity<String> issuePointsService(Integer id, BigDecimal points) {

        var lock = redissonClient.getLock("PointsServiceImp_" + id);

        try {
            boolean isLocked =  lock.tryLock();

            try {

                if (isLocked) {
                    // Simulate some work
                    // Thread.sleep(10000);
                    logger.info("Retrieving user");
                    Optional<User> user = userRepository.findById(id);

                    try {
                        if (user.isPresent()) {
                            logger.info("Retrieving multiplier");
                            Optional<Tier> tier = tierRepository.findById(user.get().getTier());

                            BigDecimal multipliedPoints = points.multiply(tier.get().getMultiplier());

                            BigDecimal updatedPoints = user.get().getPoints().add(multipliedPoints);

                            userRepository.issuePointsQuery(id, updatedPoints);

                            return ResponseEntity.ok("Points issued successfully");
                        }
                    } catch (DataAccessException e){
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    } finally {
                        // Ensure the lock is released even if an exception occurs
                        lock.unlock();
                    }
                }

            } catch (DataAccessException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @Override
    public ResponseEntity<PointsRecord> getPoints(Integer id) {

        try {
            logger.info("Retrieving user");
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
                logger.info("Retrieving points");
                PointsRecord pointsRecord = new PointsRecord(user.get().getUsername(), user.get().getCpf(), user.get().getTier(), user.get().getPoints());
                return ResponseEntity.ok(pointsRecord);
            }
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    public ResponseEntity<String> redeemPointsService(Integer id, BigDecimal points) {

        var lock = redissonClient.getLock("PointsServiceImp_" + id);

        try {
            boolean isLocked =  lock.tryLock();

            try {

                if (isLocked) {
                    // Simulate some work
                    // Thread.sleep(10000);
                    logger.info("Retrieving user");
                    Optional<User> user = userRepository.findById(id);

                    try {
                        if (user.isPresent()) {

                            if (user.get().getPoints().compareTo(BigDecimal.ZERO) == 0) {
                                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                            }

                            BigDecimal updatedPoints = user.get().getPoints().subtract(points);
                            userRepository.redeemPointsQuery(id, updatedPoints);
                            return ResponseEntity.ok(String.format("Successfully redeeming %s points", points.toString()));
                        }
                    } catch (DataAccessException e){
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    } finally {
                        // Ensure the lock is released even if an exception occurs
                        lock.unlock();
                    }
                }

            } catch (DataAccessException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
