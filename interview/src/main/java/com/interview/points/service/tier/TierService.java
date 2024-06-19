package com.interview.points.service.tier;

import com.interview.points.entity.Tier;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TierService {

    /**
     *
     */
    ResponseEntity<List<Tier>> getTiers();

    /**
     *
     * @param id ->
     * @return ->
     */
    ResponseEntity<Tier> getTier(Integer id);

    /**
     *
     * @param tier ->
     * @return ->
     */
    ResponseEntity<Tier> addTier(Tier tier);
}
