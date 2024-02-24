package ru.otus.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.enums.CashNominalEnum;
import ru.otus.enums.ErrorMessageEnum;
import ru.otus.model.Cash;
import ru.otus.model.CashImpl;
import ru.otus.model.CashStorageImpl;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CashManagerTest {
    private CashManager cashManager;

    @BeforeEach
    public void setUp() {
        this.cashManager = new CashManagerImpl(new CashStorageImpl());
    }

    @AfterEach
    void setDown() {
        this.cashManager = null;
    }

    @Test
    public void testPutCashWrongValue() {
        Cash wrongValue = new CashImpl(CashNominalEnum.FIFTY, -11);
        String e = null;
        try {
            cashManager.putCash(wrongValue);
        } catch (Exception ex) {
            e = ex.getMessage().toString();
        }
        assertEquals(e, ErrorMessageEnum.OPERATION_EXCEPTION.getMessage() + "putCash");
    }

    @Test
    public void testPutCashValidateAmount() {
        Set<Cash> expResult = new TreeSet<>(Comparator.comparingInt(Cash::getNominal).reversed());

        expResult.add(new CashImpl(CashNominalEnum.FIFTY, 17));
        expResult.add(new CashImpl(CashNominalEnum.ONEHUNDRED, 13));

        cashManager.putCash(new CashImpl(CashNominalEnum.FIFTY, 4));
        cashManager.putCash(new CashImpl(CashNominalEnum.FIFTY, 5));
        cashManager.putCash(new CashImpl(CashNominalEnum.FIFTY, 8));
        cashManager.putCash(new CashImpl(CashNominalEnum.ONEHUNDRED, 3));
        cashManager.putCash(new CashImpl(CashNominalEnum.ONEHUNDRED, 4));
        cashManager.putCash(new CashImpl(CashNominalEnum.ONEHUNDRED, 6));

        var expResultInt = expResult.stream().mapToInt(Cash::getAmount).toArray();
        var resultInt = cashManager.takeAllCash().stream().mapToInt(Cash::getAmount).toArray();

        assertArrayEquals(expResultInt, resultInt);
    }

    @Test
    public void testTakeCashWrongValue() {
        int wrongValue = -250;
        String e = null;
        cashManager.putCash(new CashImpl(CashNominalEnum.FIFTY, 11));


        try {
            cashManager.takeCash(wrongValue);
        } catch (Exception ex) {
            e = ex.getMessage().toString();
        }
        assertEquals(e, ErrorMessageEnum.OPERATION_EXCEPTION.getMessage() + "takeCash");
    }

    @Test
    public void testTakeCashLargeValue() {
        int largeValue = 503350;
        String e = null;
        Cash firstCash = new CashImpl(CashNominalEnum.FIFTY, 11);

        cashManager.putCash(firstCash);

        try {
            cashManager.takeCash(largeValue);
        } catch (Exception ex) {
            e = ex.getMessage().toString();
        }
        assertEquals(e, ErrorMessageEnum.OPERATION_EXCEPTION.getMessage() + "takeCash");
    }

    @Test
    public void testTakeCashNoDenomination() {
        int wrongValue = 3350;
        String e = null;
        cashManager.putCash(new CashImpl(CashNominalEnum.FIVETHOUSAND, 11));
        cashManager.putCash(new CashImpl(CashNominalEnum.ONEHUNDRED, 11));

        try {
            cashManager.takeCash(wrongValue);
        } catch (Exception ex) {
            e = ex.getMessage().toString();
        }
        assertEquals(e, ErrorMessageEnum.OPERATION_EXCEPTION.getMessage() + "takeCash");
    }

    @Test
    public void testTakeCash() {
        int expIntResult = 3300;
        Set<Cash> expResult = new TreeSet<>(Comparator.comparingInt(Cash::getNominal).reversed());

        cashManager.putCash(new CashImpl(CashNominalEnum.FIFTY, 11));
        cashManager.putCash(new CashImpl(CashNominalEnum.FIVEHUNDRED, 7));
        cashManager.putCash(new CashImpl(CashNominalEnum.ONEHUNDRED, 3));

        Set<Cash> resultCashSet = cashManager.takeCash(expIntResult);
        int resultCash = resultCashSet.stream().mapToInt(Cash::getTotal).sum();
        assertEquals(expIntResult, resultCash);

        expResult.add(new CashImpl(CashNominalEnum.FIVEHUNDRED, 6));
        expResult.add(new CashImpl(CashNominalEnum.ONEHUNDRED, 3));

        assertEquals(expResult.toString(), resultCashSet.toString());
    }
}
