package ru.otus.storage;

import java.util.List;

public interface CashStorage<Cash> {

    Integer getTotalBalance();

    void putCash(ru.otus.model.Cash cash);

    List<ru.otus.model.Cash> takeCash(Integer sum);
}
