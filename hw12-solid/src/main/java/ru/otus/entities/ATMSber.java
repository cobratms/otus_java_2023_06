package ru.otus.entities;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Банкомат Sber.
 */
@Getter
public class ATMSber implements ATM {

    private final MoneyStorage moneyStorage = new MoneyStorage();

    @Override
    public List<Banknote> getCash(Long withdrawalAmount) {
        List<Banknote> cash = new ArrayList<>();
        while (withdrawalAmount != 0) {
            Banknote banknote = this.getMoneyStorage().getBanknoteMaxNominalAsPossibleByWithdrawalAmount(withdrawalAmount);
            if(banknote != null) {
                cash.add(banknote);
                withdrawalAmount -= banknote.nominal().getNominalValueSize();
            } else {
                this.insertCash(cash);
                return null;
            }
        }
        return cash;
    }

    @Override
    public List<Banknote> insertCash(List<Banknote> cash) {
        try {
            for (Banknote banknote : cash) {
                Optional<StorageCell> storageCell
                        = this.moneyStorage.getStorageCells()
                        .stream()
                        .filter(sc -> sc.getStorageBanknotesNominal() == banknote.nominal())
                        .findAny();
                if (storageCell.isPresent()) {
                    storageCell.get().addBanknote(banknote);
                } else {
                    StorageCell cell = new StorageCellSber();
                    cell.addBanknote(banknote);
                    this.moneyStorage.getStorageCells().add(cell);
                }
            }
        } catch (Exception e) {
            return cash;
        }

        return Collections.emptyList();
    }

    @Override
    public Long getBalance() {
        long summ = 0;
        for (Banknote banknote : this.moneyStorage.getAllBanknotes()) {
            summ += banknote.nominal().getNominalValueSize();
        }
        return summ;
    }
}
