package com.interview.points.repositorys;

import com.interview.points.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

    @Query(value = "SELECT u FROM UserModel u WHERE u.cpf = :cpf")
    UserModel findByCpf(@Param("cpf") String cpf);
}
