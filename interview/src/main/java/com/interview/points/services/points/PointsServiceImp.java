package com.interview.points.services.points;

import com.interview.points.models.TierModel;
import com.interview.points.models.UserModel;
import com.interview.points.repositorys.TierRepository;
import com.interview.points.repositorys.UserRepository;
import com.interview.points.services.tier.TierService;
import com.interview.points.services.user.UserService;
import com.interview.points.services.user.UserServiceImp;
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
    public ResponseEntity<?> addPoints(Integer id, BigDecimal points) {

        var lock = redissonClient.getLock("PointsServiceImp");

        try {
            boolean isLocked =  lock.tryLock();

            try {

                if (isLocked) {
                    // Simulate some work
                    // Thread.sleep(10000);
                    logger.info("Retrieving user");
                    Optional<UserModel> user = userRepository.findById(id);

                    try {
                        if (user.isPresent()) {
                            logger.info("Retrieving multiplier");
                            Optional<TierModel> tier = tierRepository.findById(user.get().getTier());

                            BigDecimal multipliedPoints = points.multiply(tier.get().getMultiplier());

                            BigDecimal updatedPoints = user.get().getPoints().add(multipliedPoints);

                            userRepository.updatePoints(id, updatedPoints);

                            return ResponseEntity.status(HttpStatus.CREATED).build();
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
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();

    }

    @Override
    public ResponseEntity<?> getPoints(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<?> removePoints(Integer id,BigDecimal points) {
        return null;
    }
}
