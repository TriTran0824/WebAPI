package com.webapi.retailer.controller;

import com.webapi.retailer.entity.Transaction;
import com.webapi.retailer.exception.WebAPIException;
import com.webapi.retailer.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/webapi/v1/reward")
public class RewardController {
    private final RewardService mRewardService;

    @Autowired
    public RewardController (RewardService rewardService) {
        this.mRewardService = rewardService;
    }
    @PostMapping("points")
    public LinkedHashMap<Long, LinkedHashMap<String, Integer>> calculateRewards(@RequestBody List<Transaction> transactions) {
        LinkedHashMap<Long, LinkedHashMap<String, Integer>> rewardsByCustomer = new LinkedHashMap<>();

        if (transactions.isEmpty()) {
            throw new WebAPIException("record of transaction is empty");
        }

        // reward points for each Month
        transactions.stream().forEach(transaction -> {
            Long customerId = transaction.getCustomerId();
            LinkedHashMap<String, Integer> rewardsByMonth = rewardsByCustomer.getOrDefault(customerId, new LinkedHashMap<>());

            String monthYear = transaction.getTransactionDate().getMonth().toString() + "-" +
                    transaction.getTransactionDate().getYear();

            int rewards = mRewardService.calculateRewardsForTransaction(transaction.getPurchaseAmount());
            rewardsByMonth.put(monthYear, rewardsByMonth.getOrDefault(monthYear, 0) + rewards);

            rewardsByCustomer.put(customerId, rewardsByMonth);
        });

        // total reward points for each Customer
        rewardsByCustomer.entrySet().stream().forEach(reward -> {
            int totalRewards = reward.getValue().values().stream().mapToInt(Integer::intValue).sum();
            reward.getValue().put("Total Reward Points", totalRewards);
        });
        return rewardsByCustomer;
    }
}
