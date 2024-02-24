package ru.otus.model;

import ru.otus.enums.CashNominalEnum;

public interface Cash {
    int getAmount();

    int getNominal();

    void setAmount(int quantity);

    int getTotal();

    Cash createInstance(CashNominalEnum cashNominalEnum, int cash);

    CashNominalEnum getMoney();
}
