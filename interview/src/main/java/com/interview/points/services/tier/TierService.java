package com.interview.points.services.tier;

import com.interview.points.models.TierModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TierService {

    ResponseEntity<List<TierModel>> getTiers();

    ResponseEntity<TierModel> getTier(Integer id);

    ResponseEntity<TierModel> addTier(TierModel tier);
}
