package com.webapi.retailer.service;

import org.springframework.stereotype.Service;

@Service
public interface RewardService {
    int calculateRewardsForTransaction(double purchaseAmount);
}
