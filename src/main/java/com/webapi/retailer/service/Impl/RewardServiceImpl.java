package com.webapi.retailer.service.Impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webapi.retailer.entity.Transaction;
import com.webapi.retailer.exception.WebAPIException;
import com.webapi.retailer.service.RewardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Service implemented class that handles business logic of reward-related.
 */
@Slf4j
@Service
public class RewardServiceImpl implements RewardService {

    /*
     * Calculates the rewards earned by customers based on the provided transactions.
     * @param the file name where transactions are stored.
     * @return A LinkedHashMap containing the total reward points earned by each customer
     */
    public LinkedHashMap<Long, Integer> calculateTotalRewards(String fileName) {
        List<Transaction> transactions = readTransactionsFromFile(fileName);
        // Create a LinkedHashMap to store the total rewards by customer
        LinkedHashMap<Long, Integer> totalRewardsByCustomer = new LinkedHashMap<>();
        if (transactions.isEmpty()) {
            throw new WebAPIException("transactions are empty.");
        }
        // Calculate total rewards for each customer
        transactions.stream().forEach(transaction -> {
            Long customerId = transaction.getCustomerId();
            log.info("Customer ID: " + customerId);
            int rewards = calculateRewardsForTransaction(transaction.getPurchaseAmount());
            totalRewardsByCustomer.put(customerId, totalRewardsByCustomer.getOrDefault(customerId, 0) + rewards);
        });

        totalRewardsByCustomer.entrySet().stream().forEach(entry -> {
            log.info("Customer ID: " + entry.getKey() + ". Total reward points: " + entry.getValue());
        });
        return totalRewardsByCustomer;
    }

    private List<Transaction> readTransactionsFromFile(String filePath) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            String jsonContent = Files.readString(Path.of(filePath));
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonContent);
            for (JsonNode transactionNode : jsonNode) {
                Transaction transaction = parseTransaction(transactionNode);
                transactions.add(transaction);
            }
        } catch (IOException ex) {
            log.error("Error while reading transaction from file. File name: " + filePath + ". " + ex.getMessage());
        }
        return transactions;
    }

    private Transaction parseTransaction(JsonNode transactionNode) {
        Transaction transaction = new Transaction();
        try {
            transaction.setCustomerId(transactionNode.get("customerId").asLong());
            transaction.setPurchaseAmount(transactionNode.get("purchaseAmount").asInt());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate transactionDate = LocalDate.parse(transactionNode.get("transactionDate").asText(), formatter);
            transaction.setTransactionDate(transactionDate);
        } catch (Exception ex) {
            log.error("Error while parsing transaction. " + ex.getMessage());
        }
        return transaction;
    }
    private int calculateRewardsForTransaction(double purchaseAmount) {
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
            log.error("Error while calculating the reward points. " + ex.getMessage());
        }
        log.info("End calculating reward points");
        return rewards;
    }
}
