package ru.otus.enums;

public enum ErrorMessageEnum {
    OPERATION_EXCEPTION("Ошибка при выполнении операции: ");

    private final String message;

    ErrorMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
