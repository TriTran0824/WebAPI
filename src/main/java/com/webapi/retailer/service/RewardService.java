package com.webapi.retailer.service;

/**
 * Service interface that handles business logic of reward-related.
 */
public interface RewardService {

    /*
     * Calculates the rewards earned by customers based on the provided purchaseAmount.
     * @param purchaseAmount The purchase amount in double format
     * @return an int The reward points
     */
    int calculateRewardsForTransaction(double purchaseAmount);
}
