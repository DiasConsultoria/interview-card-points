package com.interview.points.service.tier;

import com.interview.points.entity.Tier;
import com.interview.points.repository.TierRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TierServiceImpTest {

    @InjectMocks
    private TierServiceImp underTest;

    @Mock
    private TierRepository tierRepository;

    private Tier tier;

    @BeforeEach
    void setUp() {
        tier = new Tier(1,new BigDecimal(1));
    }

    @Test
    void shouldRetrieveAllTiers() {
        when(tierRepository.findAll())
                .thenReturn(Collections.singletonList(tier));

        ResponseEntity<List<Tier>> resp = underTest.getTiers();
        assertNotNull(resp.getBody());
        assertEquals(Collections.singletonList(tier), resp.getBody());
        verify(tierRepository).findAll();
        verifyNoMoreInteractions(tierRepository);
    }

    @Test
    void shouldRetrieveTierById() {
        when(tierRepository.findById(1))
                .thenReturn(Optional.ofNullable(tier));

        ResponseEntity<Tier> resp = underTest.getTier(1);
        assertNotNull(resp.getBody());
        assertEquals(tier, resp.getBody());
        verify(tierRepository).findById(1);
        verifyNoMoreInteractions(tierRepository);
    }

    @Test
    void shouldCreateTier() {
        ResponseEntity<Tier> resp = underTest.addTier(tier);
        assertEquals(HttpStatus.CREATED, resp.getStatusCode());
        verify(tierRepository).save(tier);
        verifyNoMoreInteractions(tierRepository);
    }
}