package com.interview.points.controller;


import com.interview.points.entity.User;
import com.interview.points.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/users")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Login", description = "Service responsible for logging in", tags = "User")
    @GetMapping(value = "/login")
    public ResponseEntity<User> login(@RequestParam String username, @RequestParam String password) {
        return userService.login(username, password);
    }

    @Operation(summary = "Retrieve all users", description = "Service responsible for retrieve all users", tags = "User")
    @GetMapping(value = "/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Retrieve user by id", description = "Service responsible for retrieve user by id", tags = "User")
    @GetMapping(value = "/getById")
    public ResponseEntity<User> getById(@RequestParam Integer id) {
        return userService.getUserById(id);
    }

    @Operation(summary = "Retrieve user by cpf", description = "Service responsible for retrieve user by cpf", tags = "User")
    @GetMapping(value = "/getByCpf")
    public ResponseEntity<User> getByCpf(@RequestParam String cpf) {
        return userService.getUserByCpf(cpf);
    }


    @Operation(summary = "Create a new user", description = "Service responsible for create a new user", tags = "User")
    @PostMapping(value = "/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @Operation(summary = "Update user", description = "Service responsible for update a user", tags = "User")
    @PostMapping(value = "/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @Operation(summary = "Delete user", description = "Service responsible for delete a user", tags = "User")
    @PostMapping(value = "/delete")
    public ResponseEntity<User> deleteUser(@RequestParam Integer id) {
        return userService.deleteUser(id);
    }

}
