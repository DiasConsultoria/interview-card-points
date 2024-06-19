package com.interview.points.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.points.entity.Tier;
import com.interview.points.entity.User;
import com.interview.points.record.PointsRecord;
import com.interview.points.service.points.PointsService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.math.BigDecimal;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(PointsController.class)
@ExtendWith(MockitoExtension.class)
class PointsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PointsService pointsService;

    @Autowired
    private ObjectMapper objectMapper;

    private PointsRecord pointsRecord;
    private BigDecimal points;
    private User user;
    private Tier tier;

    @BeforeEach
    void setUp() {
        pointsRecord = new PointsRecord("Bruno", "99999999999", 1, new BigDecimal(2000));
        tier = new Tier(1,new BigDecimal(1));
        points = new BigDecimal("1000");
        user = new User( 1, "Bruno", "Bruno1234", "99999999999", "emailbruno@gmail.com", 1, new BigDecimal(2000));
    }

    @Test
    void shouldRetrievePointsById() throws Exception {

        ResponseEntity<PointsRecord> pointsRecordResponseEntity = ResponseEntity.ok(pointsRecord);

        when(pointsService.getPoints(1)).thenReturn(pointsRecordResponseEntity);

        ResultActions resp = mockMvc.perform(get("/api/v1/points?id=" + user.getId())
                .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pointsRecord)));

        resp.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", CoreMatchers.is(pointsRecord.username())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cpf", CoreMatchers.is(pointsRecord.cpf())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tier", CoreMatchers.is(pointsRecord.tier())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.points", CoreMatchers.is(2000)));
    }

    @Test
    void issuePoints() throws Exception {

        ResponseEntity<String> issueResp = ResponseEntity.ok("Points issued successfully");

        given(pointsService.issuePointsService(1, new BigDecimal(1000))).willReturn(issueResp);

        ResultActions resp = mockMvc.perform(post("/api/v1/points/issue?id=1&points=1000")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        resp.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void redeemPoints() throws Exception {

        ResponseEntity<String> issueResp = ResponseEntity.ok(String.format("Successfully redeeming %s points", points.toString()));

        given(pointsService.redeemPointsService(1, new BigDecimal(1000))).willReturn(issueResp);

        ResultActions resp = mockMvc.perform(post("/api/v1/points/redeem?id=1&points=1000")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        resp.andExpect(MockMvcResultMatchers.status().isOk());
    }
}