package ru.otus.service;

import ru.otus.model.Cash;
import ru.otus.enums.CashNominalEnum;
import ru.otus.model.CashStorage;

import java.util.Set;

public interface ATMService {
    void putMoneyOnBanknotes(CashNominalEnum nominal, int billCount);

    Set<Cash> takeCash(int cash);

    Set<Cash> takeAllCash();

    CashStorage<Cash> getStorage();
}
