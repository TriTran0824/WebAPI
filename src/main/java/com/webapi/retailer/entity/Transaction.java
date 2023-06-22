package com.webapi.retailer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private Long customerId;
    private double purchaseAmount;
    private LocalDate transactionDate;
}
