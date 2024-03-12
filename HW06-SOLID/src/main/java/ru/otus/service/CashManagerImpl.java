package ru.otus.service;

import ru.otus.enums.ErrorMessageEnum;
import ru.otus.exception.CashTransactionException;
import ru.otus.model.Cash;
import ru.otus.storage.CashStorage;

import java.util.List;


public class CashManagerImpl implements CashManager {
    CashStorage cashStorage;

    public CashManagerImpl(CashStorage cashStorage) {
        this.cashStorage = cashStorage;
    }


    @Override
    public void putCash(List<Cash> cashList) {
        cashList.forEach(cash -> cashStorage.putCash(cash));
    }

    @Override
    public List<Cash> takeCash(Integer sum) {
        if(sum < 0){
            throw new CashTransactionException(ErrorMessageEnum.TAKE_CASH_ERROR.getMessage());
        }
        else if (cashStorage.getTotalBalance() < sum) {
            throw new CashTransactionException(ErrorMessageEnum.NO_MONEY.getMessage());
        }
        return  cashStorage.takeCash(sum);
    }

    public Integer getTotalBalance() {
        return this.cashStorage.getTotalBalance();
    }
}
