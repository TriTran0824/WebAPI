package com.webapi.retailer.controller;

import com.webapi.retailer.service.RewardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

/**
 * Controller class that handles reward-related API endpoints.
 */
@Slf4j
@RestController
@RequestMapping("/web-api/v1/reward")
public class RewardController {

    private final RewardService mRewardService;

    @Autowired
    public RewardController (RewardService rewardService) {
        this.mRewardService = rewardService;
    }

    /*
     * Calculates the rewards earned by customers based on the provided transactions.
     * @param filename the file name where transactions are stored.
     * @return A LinkedHashMap containing the total reward points earned by each customer
     */
    @GetMapping("rewardPoints")
    public ResponseEntity<LinkedHashMap<Long, Integer>> getRewards(@RequestParam(name = "filename") String filename) {
        try {
            LinkedHashMap<Long, Integer> rewardsData = this.mRewardService.calculateTotalRewards(filename);
            return ResponseEntity.ok(rewardsData);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
