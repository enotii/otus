package ru.otus.service;

import ru.otus.controller.CashManager;
import ru.otus.enums.CashNominalEnum;
import ru.otus.exception.CashTransactionException;
import ru.otus.model.Cash;
import ru.otus.model.CashImpl;
import ru.otus.model.CashStorage;

import java.util.Set;

public class ATMServiceImpl implements ATMService {
    private final CashManager cashManager;

    public ATMServiceImpl(CashManager cashManager) {
        this.cashManager = cashManager;
    }


    public void putMoneyOnBanknotes(CashNominalEnum nominal, int amount) {
        for (CashNominalEnum banknote : CashNominalEnum.values()) {
            if (amount > 0 & banknote.getNominal() == nominal.getNominal()) {
                cashManager.putCash(new CashImpl(banknote, amount));
                return;
            }
        }
        throw new CashTransactionException("putMoneyOnBanknotes");
    }

    public Set<Cash> takeCash(int cash) {
        return cashManager.takeCash(cash);
    }

    public Set<Cash> takeAllCash() {
        return cashManager.takeAllCash();
    }


    public CashStorage<Cash> getStorage() {
        return cashManager.getCashStorage();
    }
}
