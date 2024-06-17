package com.interview.points.repositorys;

import com.interview.points.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

    @Query(value = "SELECT u FROM UserModel u WHERE u.cpf = :cpf")
    UserModel findByCpf(@Param("cpf") String cpf);

    @Query(value = "select u from UserModel u where u.username = :username")
    UserModel findUserByLogin(@Param("username") String username);

    @Modifying
    @Transactional
    @Query("UPDATE UserModel u SET u.points = :updatedPoints WHERE u.id = :id")
    void updatePoints(@Param("id") Integer id, @Param("updatedPoints") BigDecimal updatedPoints);

}
