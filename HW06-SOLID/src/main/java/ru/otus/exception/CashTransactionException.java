package ru.otus.exception;

import ru.otus.enums.ErrorMessageEnum;

public class CashTransactionException extends RuntimeException {

    public CashTransactionException(String message) {
        super(ErrorMessageEnum.OPERATION_EXCEPTION.getMessage() + message);
    }

}
