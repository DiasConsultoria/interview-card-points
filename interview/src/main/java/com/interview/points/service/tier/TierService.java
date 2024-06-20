package com.interview.points.service.tier;

import com.interview.points.entity.Tier;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Service interface for managing Tier entities.
 */
public interface TierService {

    /**
     * Retrieves a list of all tiers.
     *
     * @return a ResponseEntity containing a list of Tier entities.
     */
    ResponseEntity<List<Tier>> getTiers();

    /**
     * Retrieves a specific tier by its ID.
     *
     * @param id the ID of the tier to retrieve.
     * @return a ResponseEntity containing the Tier entity.
     */
    ResponseEntity<Tier> getTier(Integer id);

    /**
     * Adds a new tier.
     *
     * @param tier the Tier entity to add.
     * @return a ResponseEntity containing the added Tier entity.
     */
    ResponseEntity<Tier> addTier(Tier tier);
}
