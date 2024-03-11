package ru.otus.service;

import ru.otus.model.Cash;
import ru.otus.storage.CashStorage;

import java.util.List;

public interface CashManager {
    void putCash(List<Cash> cashList);

    List<Cash> takeCash(Integer sum);

    CashStorage getStorage();
}
