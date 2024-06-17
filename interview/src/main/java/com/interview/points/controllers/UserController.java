package com.interview.points.controllers;


import com.interview.points.models.UserModel;
import com.interview.points.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/login")
    public ResponseEntity<UserModel> login(@RequestParam String username, @RequestParam String password) {
        return userService.login(username, password);
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/getById")
    public ResponseEntity<UserModel> getById(@RequestParam Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping(value = "/getByCpf")
    public ResponseEntity<UserModel> getByCpf(@RequestParam String cpf) {
        return userService.getUserByCpf(cpf);
    }


    @PostMapping(value = "/create")
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel user) {
        return userService.createUser(user);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<UserModel> updateUser(@RequestBody UserModel user) {
        return userService.updateUser(user);
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<UserModel> deleteUser(@RequestParam Integer id) {
        return userService.deleteUser(id);
    }

}
