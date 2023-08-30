package com.webapi.retailer.application;

import com.webapi.retailer.controller.RewardController;
import com.webapi.retailer.exception.WebAPIException;
import com.webapi.retailer.service.RewardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RewardControllerTest {
    @Mock
    private RewardService rewardService;

    @InjectMocks
    private RewardController rewardController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getRewardsSuccessTest() {
        LinkedHashMap<Long, Integer> rewardsData = new LinkedHashMap<>();
        rewardsData.put(1L, 100);
        when(this.rewardService.calculateTotalRewards(anyString())).thenReturn(rewardsData);

        ResponseEntity<LinkedHashMap<Long, Integer>> response = this.rewardController.getRewards(anyString());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(rewardsData, response.getBody());
    }

    @Test
    public void getRewardsExceptionTest() {
        when(rewardService.calculateTotalRewards(anyString())).thenThrow(new WebAPIException("Exception throws"));

        ResponseEntity<LinkedHashMap<Long, Integer>> response = rewardController.getRewards(anyString());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}

