package ru.otus.model;

import ru.otus.enums.CashNominalEnum;

import java.util.Objects;

public class CashImpl implements Cash {
    private final CashNominalEnum cashNominalEnum;
    private int amount;

    public CashImpl(CashNominalEnum cashNominalEnum, int amount) {
        this.cashNominalEnum = cashNominalEnum;
        this.amount = amount;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int getTotal() {
        return cashNominalEnum.getNominal() * amount;
    }

    @Override
    public int getNominal() {
        return cashNominalEnum.getNominal();
    }

    @Override
    public CashNominalEnum getMoney() {
        return cashNominalEnum;
    }

    @Override
    public Cash createInstance(CashNominalEnum cashNominalEnum, int quantity) {
        return new CashImpl(cashNominalEnum, quantity);
    }

    @Override
    public String toString() {
        return "Cash{" +
                "nominal=" + cashNominalEnum.getNominal() +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CashImpl cashImpl = (CashImpl) o;
        return cashNominalEnum == cashImpl.cashNominalEnum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cashNominalEnum);
    }

}
