package com.interview.points.repositorys;


import com.interview.points.models.TierModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TierRepository extends JpaRepository<TierModel, Integer> {
}
