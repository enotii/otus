package ru.otus.enums;

public enum CashNominalEnum {

    TEN(10),
    FIFTY(50),
    ONEHUNDRED(100),
    FIVEHUNDRED(500),
    ONETHOUSAND(1000),
    FIVETHOUSAND(5000);

    private final int nominal;

    CashNominalEnum(int nominal) {
        this.nominal = nominal;
    }

    public int getNominal() {
        return nominal;
    }

    public static CashNominalEnum getCashNominalEnum(Integer nominal) {
        for (CashNominalEnum v : values()) {
            if (v.getNominal() == nominal) {
                return v;
            }
        }
        return null;
    }

}
