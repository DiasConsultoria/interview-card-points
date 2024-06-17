package com.interview.points.services.user;

import com.interview.points.models.UserModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<List<UserModel>> getAllUsers();

    ResponseEntity<UserModel> getUserById(Integer id);

    ResponseEntity<UserModel> getUserByCpf(String cpf);

    ResponseEntity<UserModel> createUser(UserModel user);

    ResponseEntity<UserModel> updateUser(UserModel user);

    ResponseEntity<UserModel> deleteUser(Integer id);

    ResponseEntity<UserModel> login(String username, String password);
}
