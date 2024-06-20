package com.interview.points.service.points;

import com.interview.points.entity.Tier;
import com.interview.points.entity.User;
import com.interview.points.provider.LockProvider;
import com.interview.points.provider.RedisLockProvider;
import com.interview.points.record.IssueRecord;
import com.interview.points.record.PointsRecord;
import com.interview.points.record.RedeemRecord;
import com.interview.points.repository.TierRepository;
import com.interview.points.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.redisson.api.RLock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PointsServiceImpTest {

    @InjectMocks
    private PointsServiceImp underTest;

    @Mock
    private UserRepository userRepository;
    @Mock
    private TierRepository tierRepository;
    @Mock
    private LockProvider lockProvider;
    @Mock
    private RLock lock;

    private BigDecimal points;
    private User user;
    private Tier tier;

    @BeforeEach
    void setUp() {
        tier = new Tier(1,new BigDecimal(1));
        points = new BigDecimal("1000");
        user = new User( 1, "Bruno", "Bruno1234", "99999999999", "emailbruno@gmail.com", 1, new BigDecimal(2000));
    }

    @Test
    void issuePointsService()  {
        when(userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user));

        when(tierRepository.findById(user.getTier()))
                .thenReturn(Optional.of(tier));

        when(lockProvider.getLock("PointsServiceImp_", user.getId())).thenReturn(lock);

        when(lock.tryLock()).thenReturn(true);

        ResponseEntity<IssueRecord> resp = underTest.issuePointsService(user.getId(), points);
        assertNotNull(resp.getBody());
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        verify(userRepository, times(1)).findById(user.getId());
        verify(tierRepository, times(1)).findById(user.getTier());
        verify(lock, times(1)).unlock();
    }

    @Test
    void getPoints() {
        when(userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user));

        ResponseEntity<PointsRecord> resp = underTest.getPoints(user.getId());
        assertNotNull(resp.getBody());
        assertEquals(resp.getBody().username(), user.getUsername());
        assertEquals(resp.getBody().cpf(), user.getCpf());
        assertEquals(resp.getBody().tier(), user.getTier());
        assertEquals(resp.getBody().points(), user.getPoints());
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        verify(userRepository, times(1)).findById(user.getId());
    }

    @Test
    void redeemPointsService() {
        when(userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user));

        when(lockProvider.getLock("PointsServiceImp_", user.getId())).thenReturn(lock);

        when(lock.tryLock()).thenReturn(true);

        ResponseEntity<RedeemRecord> resp = underTest.redeemPointsService(user.getId(), points);
        assertNotNull(resp.getBody());
        assertEquals(HttpStatus.OK, resp.getStatusCode());
        verify(userRepository, times(1)).findById(user.getId());
        verify(lock, times(1)).unlock();
    }
}