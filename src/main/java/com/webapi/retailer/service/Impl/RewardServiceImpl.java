package com.webapi.retailer.service.Impl;

import com.webapi.retailer.service.RewardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service implement class that handles business logic of reward-related.
 */
@Slf4j
@Service
public class RewardServiceImpl implements RewardService {

    /*
     * Calculates the rewards earned by customers based on the provided purchaseAmount.
     * @param purchaseAmount The purchase amount in double format
     * @return an int The reward points
     */
    public int calculateRewardsForTransaction(double purchaseAmount) {
        int rewards = 0;
        log.info("Start calculating reward points");
        try {
            if (purchaseAmount > 100) {
                rewards += (int) ((purchaseAmount - 100) * 2);
                purchaseAmount = 100;
            }
            if (purchaseAmount > 50) {
                rewards += (int) ((purchaseAmount - 50) * 1);
            }
        } catch (Exception ex) {
            log.error("Error while calculating the reward points");
        }
        return rewards;
    }
}
