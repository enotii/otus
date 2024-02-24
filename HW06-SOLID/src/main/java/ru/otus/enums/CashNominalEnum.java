package ru.otus.enums;

public enum CashNominalEnum {

    TEN(10),
    FIFTY(50),
    ONEHUNDRED(100),
    FIVEHUNDRED(500),
    THOUSAND(1000),
    FIVETHOUSAND(5000);

    private final int nominal;

    CashNominalEnum(int nominal) {
        this.nominal = nominal;
    }

    public int getNominal() {
        return nominal;
    }
}
