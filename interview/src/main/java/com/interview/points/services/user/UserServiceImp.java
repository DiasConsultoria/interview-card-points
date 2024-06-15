package com.interview.points.services.user;

import com.interview.points.models.UserModel;
import com.interview.points.repositorys.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.redisson.api.RedissonClient;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService{

    private static final Logger logger = LogManager.getLogger(UserServiceImp.class);

    private final UserRepository userRepository;
    private final RedissonClient redissonClient;


    @Override
    public ResponseEntity<List<UserModel>> getAllUsers() {
        try {
            logger.info("Retrieving user");
            List<UserModel> users = userRepository.findAll();
            return ResponseEntity.ok(users);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    public ResponseEntity<UserModel> getUserById(Integer id) {
        logger.info("Retrieving user by Id");
        return userRepository
                .findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }


    @Override
    public ResponseEntity<UserModel> getUserByCpf(String cpf) {
        try {
            logger.info("Retrieving user by cpf");
            UserModel user = userRepository.findByCpf(cpf);
            return ResponseEntity.ok(user);
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<UserModel> createUser(UserModel user) {
        try {
            logger.info("Creating new user");
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<UserModel> updateUser(UserModel user) {
        try {
            logger.info("Updating user");
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<UserModel> deleteUser(int id) {

        var lock = redissonClient.getLock("UserServiceImp");
        try {
            boolean isLocked = lock.tryLock();

            try {

                if (isLocked) {
                    // Simulate some work
                    // Thread.sleep(1000);
                    logger.info("Retrieving user by Id to delete");
                    Optional<UserModel> user = userRepository.findById(id);

                    try {
                        if (user.isPresent()) {
                            logger.info("Deleting user");
                            userRepository.delete(user.get());
                            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                        }
                    } catch (DataAccessException e){
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    } finally {
                        // Ensure the lock is released even if an exception occurs
                        lock.unlock();
                    }
                }

            } catch (DataAccessException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.LOCKED).build();
    }
}
