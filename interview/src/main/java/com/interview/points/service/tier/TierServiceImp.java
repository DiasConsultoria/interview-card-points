package com.interview.points.service.tier;

import com.interview.points.entity.Tier;
import com.interview.points.repository.TierRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TierServiceImp implements TierService {

    private static final Logger logger = LogManager.getLogger(TierServiceImp.class);

    private final TierRepository tierRepository;

    @Override
    public ResponseEntity<List<Tier>> getTiers() {

        try {
            logger.info("Getting all tiers");
            List<Tier> tiers = tierRepository.findAll();
            return ResponseEntity.ok(tiers);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<Tier> getTier(Integer id) {
        return tierRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public ResponseEntity<Tier> addTier(Tier tier) {
        try {
            logger.info("Creating new tier");
            tierRepository.save(tier);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
