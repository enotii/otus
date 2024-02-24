package ru.otus.controller;

import ru.otus.exception.CashTransactionException;
import ru.otus.model.Cash;
import ru.otus.model.CashStorage;

import java.util.*;


public class CashManagerImpl implements CashManager {
    private final CashStorage<Cash> cashStorage;

    public CashManagerImpl(CashStorage<Cash> cashStorage) {
        this.cashStorage = cashStorage;
    }

    @Override
    public void putCash(Cash cash) {
        if (cash.getAmount() < 0) {
            throw new CashTransactionException("putCash");
        }
        for (Cash c : cashStorage.getSet()) {
            if (c.equals(cash)) {
                int amount = c.getAmount();
                amount = amount + cash.getAmount();
                c.setAmount(amount);
                return;
            }
        }
        cashStorage.getSet().add(cash);
    }

    @Override
    public Set<Cash> takeCash(int amount) {
        if (amount > 0 && amount <= getAmountCash(cashStorage.getSet())) {
            Set<Cash> set = new TreeSet<>(Comparator.comparingInt(Cash::getNominal).reversed());
            for (Cash cash : cashStorage.getSet()) {
                Cash banknote = takeBanknotes(cash, amount);
                set.add(banknote);
                amount = amount - banknote.getTotal();
                if (amount == 0) {
                    removeBanknotes(set);
                    return set;
                }
            }
        }
        throw new CashTransactionException("takeCash");
    }

    @Override
    public Set<Cash> takeAllCash() {
        return takeCash(cashStorage.getSet().stream().mapToInt(Cash::getTotal).sum());
    }

    private void removeBanknotes(Set<Cash> removeSet) {
        for (Cash c : cashStorage.getSet()) {
            for (Cash rc : removeSet) {
                if (c.equals(rc)) {
                    c.setAmount(c.getAmount() - rc.getAmount());
                    break;
                }
            }
        }
    }

    private Cash takeBanknotes(Cash c, int amount) {
        if (c.getTotal() > amount) {
            return c.createInstance(c.getMoney(), amount / c.getNominal());
        } else
        {
            return c.createInstance(c.getMoney(), c.getAmount());
        }
    }

    private int getAmountCash(Set<Cash> cashSet) {
        return cashSet.stream().mapToInt(a -> a.getAmount() * a.getNominal()).sum();
    }

    @Override
    public CashStorage<Cash> getCashStorage() {
        return cashStorage;
    }
}
