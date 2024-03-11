package ru.otus.model;

import ru.otus.enums.CashNominalEnum;

public class Cash {
    private final CashNominalEnum cashNominalEnum;
    private int amount;

    public Cash(CashNominalEnum cashNominalEnum, int amount) {
        this.cashNominalEnum = cashNominalEnum;
        this.amount = amount;
    }


    public int getAmount() {
        return amount;
    }


    public void setAmount(int amount) {
        this.amount = amount;
    }


    public int getTotal() {
        return cashNominalEnum.getNominal() * amount;
    }


    public int getNominal() {
        return cashNominalEnum.getNominal();
    }


}
