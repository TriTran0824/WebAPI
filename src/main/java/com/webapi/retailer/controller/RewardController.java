package com.webapi.retailer.controller;

import com.webapi.retailer.entity.Transaction;
import com.webapi.retailer.exception.WebAPIException;
import com.webapi.retailer.service.RewardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;

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
     * @param transactions The transactions data in List format
     * @return A LinkedHashMap containing the rewards earned by each customer per month and total
     */
    @PostMapping("rewardPoints")
    public LinkedHashMap<Long, LinkedHashMap<String, Integer>> calculateRewards(@RequestBody List<Transaction> transactions) {

        // Create a LinkedHashMap to store the rewards by customer
        LinkedHashMap<Long, LinkedHashMap<String, Integer>> rewardsByCustomer = new LinkedHashMap<>();

        if (transactions.isEmpty()) {
            throw new WebAPIException("record of transaction is empty");
        }

        // Reward points for each Month for each Customer
        transactions.stream().forEach(transaction -> {
            Long customerId = transaction.getCustomerId();
            LinkedHashMap<String, Integer> rewardsByMonth = rewardsByCustomer.getOrDefault(customerId, new LinkedHashMap<>());

            // Get the month-year string for the transaction date
            String monthYear = transaction.getTransactionDate().getMonth().toString() + "-" +
                    transaction.getTransactionDate().getYear();
            // Get monthly reward
            int rewards = this.mRewardService.calculateRewardsForTransaction(transaction.getPurchaseAmount());
            log.info("Reward points for Customer ID: " + customerId + ". " + monthYear + " - Reward points: " + rewards);

            rewardsByMonth.put(monthYear, rewardsByMonth.getOrDefault(monthYear, 0) + rewards);

            rewardsByCustomer.put(customerId, rewardsByMonth);
        });

        // Total reward points for each Customer
        rewardsByCustomer.entrySet().stream().forEach(reward -> {
            int totalRewards = reward.getValue().values().stream().mapToInt(Integer::intValue).sum();
            reward.getValue().put("Total Reward Points", totalRewards);

            log.info("Customer ID: " + reward.getKey() + ". Total reward points:  " + totalRewards);
        });
        return rewardsByCustomer;
    }
}
