package com.webapi.retailer.service.Impl;

import com.webapi.retailer.service.RewardService;
import org.springframework.stereotype.Service;

@Service
public class RewardServiceImpl implements RewardService {
    public int calculateRewardsForTransaction(double purchaseAmount) {
        int rewards = 0;
        if (purchaseAmount > 100) {
            rewards += (int) ((purchaseAmount - 100) * 2);
            purchaseAmount = 100;
        }
        if (purchaseAmount > 50) {
            rewards += (int) ((purchaseAmount - 50) * 1);
        }
        return rewards;
    }
}
