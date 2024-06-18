package com.interview.points.services.user;

import com.interview.points.entitys.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<List<User>> getAllUsers();

    ResponseEntity<User> getUserById(Integer id);

    ResponseEntity<User> getUserByCpf(String cpf);

    ResponseEntity<User> createUser(User user);

    ResponseEntity<User> updateUser(User user);

    ResponseEntity<User> deleteUser(Integer id);

    ResponseEntity<User> login(String username, String password);
}
