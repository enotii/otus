package ru.otus;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.enums.CashNominalEnum;
import ru.otus.enums.ErrorMessageEnum;
import ru.otus.model.Cash;
import ru.otus.service.CashManager;
import ru.otus.service.CashManagerImpl;
import ru.otus.storage.CashStorageImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CashManagerTest {
    private CashManager cashManager;


    @BeforeEach
    public void setUp() {
        this.cashManager = new CashManagerImpl(new CashStorageImpl());
    }

    @AfterEach
    void setDown() {
        this.cashManager = new CashManagerImpl(new CashStorageImpl());
    }

    @Test
    public void testPutCash() {
        cashManager.putCash(
                List.of(new Cash(CashNominalEnum.FIFTY, 2),
                        new Cash(CashNominalEnum.TEN, 2),
                        new Cash(CashNominalEnum.FIVEHUNDRED, 2),
                        new Cash(CashNominalEnum.ONETHOUSAND, 2)
                ));
        assertEquals(3120, cashManager.getTotalBalance());
    }

    @Test
    public void testPutCashWrongValue() {
        String e = null;
        try {
            cashManager.putCash(
                    List.of(new Cash(CashNominalEnum.FIFTY, 2),
                            new Cash(CashNominalEnum.TEN, 2),
                            new Cash(CashNominalEnum.FIVEHUNDRED, 2),
                            new Cash(CashNominalEnum.ONETHOUSAND, -2)
                    ));
        } catch (Exception ex) {
            e = ex.getMessage().toString();
        }
        assertEquals(e, ErrorMessageEnum.AMOUNT_IS_NEGATIVE.getMessage());
        assertEquals(1120, cashManager.getTotalBalance());

    }

    @Test
    public void testTakeCashWrongValue() {
        int wrongValue = -250;
        String e = null;
        try {
            cashManager.takeCash(wrongValue);
        } catch (Exception ex) {
            e = ex.getMessage().toString();
        }
        assertEquals(e, ErrorMessageEnum.TAKE_CASH_ERROR.getMessage());
    }

    @Test
    public void testTakeCashLargeValue() {
        int largeValue = 503350;
        String e = null;

        try {
            cashManager.takeCash(largeValue);
        } catch (Exception ex) {
            e = ex.getMessage().toString();
        }
        assertEquals(e, ErrorMessageEnum.NO_MONEY.getMessage());
    }


    @Test
    public void testTakeCash() {

        cashManager.putCash(
                List.of(new Cash(CashNominalEnum.FIFTY, 2),
                        new Cash(CashNominalEnum.TEN, 2),
                        new Cash(CashNominalEnum.FIVEHUNDRED, 2)
                ));
        cashManager.takeCash(50);
        assertEquals(1070, cashManager.getTotalBalance());
    }

    @Test
    public void testTakeCashFailed() {
        String e = null;
        cashManager.putCash(
                List.of(new Cash(CashNominalEnum.FIFTY, 2),
                        new Cash(CashNominalEnum.TEN, 2),
                        new Cash(CashNominalEnum.FIVEHUNDRED, 2)
                ));
        try {
        cashManager.takeCash(56);
        } catch (Exception ex) {
            e = ex.getMessage().toString();
        }
        assertEquals(e, ErrorMessageEnum.INVALID_SUM.getMessage());
    }
}
