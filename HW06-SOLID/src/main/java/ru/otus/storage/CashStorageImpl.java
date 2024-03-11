package ru.otus.storage;

import ru.otus.enums.CashNominalEnum;
import ru.otus.enums.ErrorMessageEnum;
import ru.otus.exception.CashTransactionException;
import ru.otus.model.Cash;

import java.util.*;

import static ru.otus.enums.CashNominalEnum.getCashNominalEnum;

public class CashStorageImpl implements CashStorage {
    private final Map<CashNominalEnum, Integer> cashStorage;

    public CashStorageImpl() {
        this.cashStorage = new HashMap<>();
    }


    @Override
    public Integer getTotalBalance() {
        int total = 0;
        for (Map.Entry<CashNominalEnum, Integer> entry : cashStorage.entrySet()) {
            CashNominalEnum nominalEnum = entry.getKey();
            Integer amount = entry.getValue();
            total += (nominalEnum.getNominal() * amount);
        }
        return total;
    }

    @Override
    public void putCash(Cash cash) {
        if(cash.getAmount() < 0) {
            throw new CashTransactionException(ErrorMessageEnum.AMOUNT_IS_NEGATIVE.getMessage());
        }
        CashNominalEnum cashNominalEnum = getCashNominalEnum(cash.getNominal());
        if (cashStorage.containsKey(cashNominalEnum)) {
            cashStorage.put(cashNominalEnum,cashStorage.get(cashNominalEnum) + cash.getAmount());
        }
        else {
            cashStorage.put(cashNominalEnum, cash.getAmount());
        }
    }
    @Override
    public List<Cash> takeCash(Integer sum) {
        List<Cash> result = new ArrayList<>();
        List<CashNominalEnum> sortedList = new ArrayList<>();
        int stashSum = sum;

        for (Map.Entry<CashNominalEnum, Integer> entry : cashStorage.entrySet()) {
            sortedList.add(entry.getKey());
        }
        Collections.sort(sortedList,  Collections.reverseOrder((Comparator.comparing(CashNominalEnum::getNominal))));

        for(CashNominalEnum nominal : sortedList){
            while (sum > nominal.getNominal()){
                int countBanknoutes = sum / nominal.getNominal();
                sum =- nominal.getNominal() * countBanknoutes;
                result.add(new Cash(nominal, countBanknoutes));
            }
        }
        if(stashSum != sum){
            throw new CashTransactionException(ErrorMessageEnum.INVALID_SUM.getMessage());
        }

        result.forEach(this::removeBanknotes);
        return result;
    }

    private void removeBanknotes(Cash removeCash) {
        CashNominalEnum cashNominalEnum = getCashNominalEnum(removeCash.getNominal());
        cashStorage.put(cashNominalEnum , cashStorage.get(cashNominalEnum) - removeCash.getAmount());
    }
}
