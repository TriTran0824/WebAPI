package com.webapi.retailer.service;

import java.util.LinkedHashMap;

/**
 * Service interface that handles business logic of reward-related.
 */
public interface RewardService {

    /*
     * Calculates the rewards earned by customers based on the provided transactions.
     * @param the file name where transactions are stored.
     * @return A LinkedHashMap containing the total reward points earned by each customer
     */
    LinkedHashMap<Long, Integer> calculateTotalRewards(String fileName);
}
