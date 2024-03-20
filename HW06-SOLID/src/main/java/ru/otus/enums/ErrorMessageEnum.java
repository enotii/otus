package ru.otus.enums;

public enum ErrorMessageEnum {
    AMOUNT_IS_NEGATIVE("error during operation put Cash because amount is negative"),
    TAKE_CASH_ERROR("error during operation take Cash because sum is negative"),
    NO_MONEY("there is not that much money in the ATM"),

    INVALID_SUM("cannot issue this sum");

    private final String message;

    ErrorMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
