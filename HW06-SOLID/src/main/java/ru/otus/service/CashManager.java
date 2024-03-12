package ru.otus.service;

import ru.otus.model.Cash;

import java.util.List;

public interface CashManager {
    void putCash(List<Cash> cashList);

    List<Cash> takeCash(Integer sum);

    Integer getTotalBalance();
}
