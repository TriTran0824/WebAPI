package com.webapi.retailer.application;

import com.webapi.retailer.entity.Transaction;
import com.webapi.retailer.exception.WebAPIException;
import com.webapi.retailer.service.Impl.RewardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static com.webapi.retailer.constant.Constant.FILE_PATH;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class RewardServiceTest {

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calculateRewardsWithValidTransactionsTest() {
        RewardServiceImpl rewardServiceImpl = new RewardServiceImpl();

        // Transaction > $100
        Transaction transaction1 = new Transaction(1L, 110.0, LocalDate.of(2023, 7, 1));
        Transaction transaction11 = new Transaction(1L, 120.0, LocalDate.of(2023, 7, 1));
        Transaction transaction12 = new Transaction(1L, 250.0, LocalDate.of(2023, 8, 1));
        Transaction transaction13 = new Transaction(1L, 150.0, LocalDate.of(2023, 8, 10));
        Transaction transaction14 = new Transaction(1L, 250.0, LocalDate.of(2023, 9, 12));
        Transaction transaction15 = new Transaction(1L, 150.0, LocalDate.of(2023, 9, 12));

        // $50 < Transaction <= $100
        Transaction transaction2 = new Transaction(2L, 100.0, LocalDate.of(2023, 7, 1));
        Transaction transaction21 = new Transaction(2L, 70.0, LocalDate.of(2023, 7, 15));
        Transaction transaction22 = new Transaction(2L, 60.0, LocalDate.of(2023, 8, 1));
        Transaction transaction23 = new Transaction(2L, 85.0, LocalDate.of(2023, 8, 1));
        Transaction transaction24 = new Transaction(2L, 90.0, LocalDate.of(2023, 9, 22));
        Transaction transaction25 = new Transaction(2L, 95.0, LocalDate.of(2023, 9, 22));

        // $50 < Transaction <= $100 and Transaction > $100
        Transaction transaction3 = new Transaction(3L, 120.0, LocalDate.of(2023, 7, 1));
        Transaction transaction31 = new Transaction(3L, 70.0, LocalDate.of(2023, 8, 15));
        Transaction transaction32 = new Transaction(3L, 60.0, LocalDate.of(2023, 8, 1));
        Transaction transaction33 = new Transaction(3L, 120.0, LocalDate.of(2023, 8, 1));
        Transaction transaction34 = new Transaction(3L, 90.0, LocalDate.of(2023, 9, 22));
        Transaction transaction35 = new Transaction(3L, 150.0, LocalDate.of(2023, 9, 22));

        // Transaction <= $50
        Transaction transaction4 = new Transaction(4L, 50.0, LocalDate.of(2023, 7, 1));
        Transaction transaction41 = new Transaction(4L, 40.0, LocalDate.of(2023, 8, 15));
        Transaction transaction42 = new Transaction(4L, 45.0, LocalDate.of(2023, 8, 1));
        Transaction transaction43 = new Transaction(4L, 25.0, LocalDate.of(2023, 8, 1));
        Transaction transaction44 = new Transaction(4L, 35.0, LocalDate.of(2023, 9, 22));
        Transaction transaction45 = new Transaction(4L, 48.0, LocalDate.of(2023, 9, 22));

        List<Transaction> transactions = new ArrayList<>();

        // Transaction > $100
        transactions.add(transaction1);
        transactions.add(transaction11);
        transactions.add(transaction12);
        transactions.add(transaction13);
        transactions.add(transaction14);
        transactions.add(transaction15);

        // $50 < Transaction <= $100
        transactions.add(transaction2);
        transactions.add(transaction21);
        transactions.add(transaction22);
        transactions.add(transaction23);
        transactions.add(transaction24);
        transactions.add(transaction25);

        // $50 < Transaction <= $100 and Transaction > $100
        transactions.add(transaction3);
        transactions.add(transaction31);
        transactions.add(transaction32);
        transactions.add(transaction33);
        transactions.add(transaction34);
        transactions.add(transaction35);

        // Transaction <= $50
        transactions.add(transaction4);
        transactions.add(transaction41);
        transactions.add(transaction42);
        transactions.add(transaction43);
        transactions.add(transaction44);
        transactions.add(transaction45);

        LinkedHashMap<Long, Integer> expectedRewards = new LinkedHashMap<>();

        // Total rewards for customer 1
        expectedRewards.put(1L, 1360);

        // Total rewards for customer 2
        expectedRewards.put(2L, 200);

        // Total rewards for customer 3
        expectedRewards.put(3L, 400);

        // Total rewards for customer 4
        expectedRewards.put(4L, 0);

        LinkedHashMap<Long, Integer> actualRewards = rewardServiceImpl.calculateTotalRewards(FILE_PATH);

        assertEquals(expectedRewards, actualRewards);
    }

    @Test
    void calculateRewardsWithExceptionTest() {
        RewardServiceImpl rewardServiceImpl = new RewardServiceImpl();
        LinkedHashMap<Long, Integer> actualRewards = rewardServiceImpl.calculateTotalRewards(FILE_PATH);
        WebAPIException exception = assertThrows(WebAPIException.class, () -> {
            rewardServiceImpl.calculateTotalRewards("testExceptionFileName");
        });

        assertEquals("transactions are empty.", exception.getMessage());
    }
}
