package ru.otus.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Хранилище.
 */
@Getter
@NoArgsConstructor
public class MoneyStorage {

    private final Set<StorageCell> storageCells = new TreeSet<>();

    public List<Banknote> getAllBanknotes() {
        List<Banknote> banknotes = new ArrayList<>();
        storageCells.forEach(storageCell -> banknotes.addAll(storageCell.getAllBanknotes()));
        return banknotes;
    }

    public Banknote getBanknoteMaxNominalAsPossibleByWithdrawalAmount(Long withdrawalAmount) {
        StorageCell storageCell = null;
        for (StorageCell cell : storageCells) {
            if (cell.getStorageBanknotesNominal().getNominalValueSize() <= withdrawalAmount) {
                storageCell = cell;
                break;
            }
        }

        Banknote banknote = null;
        if(storageCell != null) {
            banknote = storageCell.getBanknote();

            if(storageCell.getAllBanknotes().size() == 0) {
                storageCells.remove(storageCell);
            }
        }

        return banknote;
    }
}
