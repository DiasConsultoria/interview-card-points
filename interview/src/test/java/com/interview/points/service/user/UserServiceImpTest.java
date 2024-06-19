package com.interview.points.service.user;

import com.interview.points.entity.User;
import com.interview.points.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceImpTest {

    @InjectMocks
    private UserServiceImp underTest;

    @Mock
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User( 1, "Bruno", "Bruno1234", "99999999999", "emailbruno@gmail.com", 1, new BigDecimal(0));
    }

    @Test
    void shouldRetrieveAllUsersTest() {
        when(userRepository.findAll())
                .thenReturn(Collections.singletonList(user));

        ResponseEntity<List<User>> resp = underTest.getAllUsers();

        assertEquals(Collections.singletonList(user), resp.getBody());
        verify(userRepository).findAll();
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void shouldRetrieveUserByIDTest() {
        when(userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user));

        ResponseEntity<User> response = underTest.getUserById(user.getId());

        assertEquals(user, response.getBody());
        verify(userRepository).findById(user.getId());
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void shouldRetrieveUserByCpfTest() {
        when(userRepository.findByCpf(user.getCpf()))
                .thenReturn(user);

        ResponseEntity<User> response = underTest.getUserByCpf(user.getCpf());
        assertEquals(user, response.getBody());
        verify(userRepository).findByCpf(user.getCpf());
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void shouldCreateUserTest() {
        ResponseEntity<User> response = underTest.createUser(user);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(userRepository).save(user);
    }

    @Test
    void shouldUpdateUserTest() {
        ResponseEntity<User> response = underTest.updateUser(user);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(userRepository).save(user);
    }

    @Test
    void shouldDeleteUserTest() {
        when(userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user));

        ResponseEntity<User> responseGetUser = underTest.getUserById(user.getId());

        ResponseEntity<User> responseDeleteUser = underTest.deleteUser(Objects.requireNonNull(responseGetUser.getBody()).getId());
        assertEquals(HttpStatus.NO_CONTENT, responseDeleteUser.getStatusCode());
        verify(userRepository).delete(user);
    }

    @Test
    void shouldLoginTest() {
        when(userRepository.findUserByLogin(user.getUsername())).thenReturn(user);

        ResponseEntity<User> response = underTest.login(user.getUsername(), user.getPassword());
        assertEquals(user, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userRepository).findUserByLogin(user.getUsername());
        verifyNoMoreInteractions(userRepository);
    }
}