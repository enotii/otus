package ru.otus.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.controller.CashManager;
import ru.otus.controller.CashManagerImpl;
import ru.otus.enums.CashNominalEnum;
import ru.otus.exception.CashTransactionException;
import ru.otus.model.CashStorageImpl;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ATMServiceTest {
    private CashManager cashManager;
    private ATMService atmService;

    public ATMServiceTest() {
    }

    @BeforeEach
    void setUp() {
        this.cashManager = new CashManagerImpl(new CashStorageImpl());
        this.atmService = new ATMServiceImpl(cashManager);
    }

    @AfterEach
    void setDown() {
        this.cashManager = null;
        this.atmService = null;
    }

    @Test
    public void testPutMoneyOnBanknotes() {
        CashNominalEnum nominal = CashNominalEnum.FIVEHUNDRED;
        int wrongBillCount = -3;

        try {
            atmService.putMoneyOnBanknotes(nominal, wrongBillCount);
        } catch (Exception ex) {
            assertTrue(ex instanceof CashTransactionException);
        }
    }

}
