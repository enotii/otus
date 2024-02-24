package ru.otus.controller;

import ru.otus.model.Cash;
import ru.otus.model.CashStorage;

import java.util.Set;

public interface CashManager {
    void putCash(Cash obj);

    Set<Cash> takeCash(int cash);

    Set<Cash> takeAllCash();

    CashStorage<Cash> getCashStorage();
}
