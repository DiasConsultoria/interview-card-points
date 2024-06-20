package com.interview.points.service.user;

import com.interview.points.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Service interface for managing User entities.
 */
public interface UserService {

    /**
     * Retrieves a list of all users.
     *
     * @return a ResponseEntity containing a list of User entities.
     */
    ResponseEntity<List<User>> getAllUsers();

    /**
     * Retrieves a specific user by their ID.
     *
     * @param id the ID of the user to retrieve.
     * @return a ResponseEntity containing the User entity.
     */
    ResponseEntity<User> getUserById(Integer id);

    /**
     * Retrieves a specific user by their CPF (Cadastro de Pessoas Físicas).
     *
     * @param cpf the CPF of the user to retrieve.
     * @return a ResponseEntity containing the User entity.
     */
    ResponseEntity<User> getUserByCpf(String cpf);

    /**
     * Creates a new user.
     *
     * @param user the User entity to create.
     * @return a ResponseEntity containing the created User entity.
     */
    ResponseEntity<User> createUser(User user);

    /**
     * Updates an existing user.
     *
     * @param user the User entity to update.
     * @return a ResponseEntity containing the updated User entity.
     */
    ResponseEntity<User> updateUser(User user);

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to delete.
     * @return a ResponseEntity containing the deleted User entity.
     */
    ResponseEntity<User> deleteUser(Integer id);

    /**
     * Authenticates a user using their username and password.
     *
     * @param username the username of the user.
     * @param password the password of the user.
     * @return a ResponseEntity containing the authenticated User entity.
     */
    ResponseEntity<User> login(String username, String password);
}
