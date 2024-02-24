package ru.otus.model;

import java.util.*;

public class CashStorageImpl implements CashStorage<Cash> {
    private final Set<Cash> set = new TreeSet<>(Comparator.comparingInt(Cash::getNominal).reversed());

    public CashStorageImpl() {
    }

    @Override
    public Set<Cash> getSet() {
        return set;
    }
}
