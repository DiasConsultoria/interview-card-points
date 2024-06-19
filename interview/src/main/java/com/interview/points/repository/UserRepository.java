package com.interview.points.repository;

import com.interview.points.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT u FROM User u WHERE u.cpf = :cpf")
    User findByCpf(@Param("cpf") String cpf);

    @Query(value = "select u from User u where u.username = :username")
    User findUserByLogin(@Param("username") String username);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.points = :updatedPoints WHERE u.id = :id")
    void issuePointsQuery(@Param("id") Integer id, @Param("updatedPoints") BigDecimal updatedPoints);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.points = :updatedPoints WHERE u.id = :id")
    void redeemPointsQuery(@Param("id") Integer id, @Param("updatedPoints") BigDecimal updatedPoints);

}
