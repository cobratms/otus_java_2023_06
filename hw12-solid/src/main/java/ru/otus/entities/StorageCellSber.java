package ru.otus.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayDeque;
import java.util.List;

/**
 * Ячейка банкомата Sber, в которой хранятся банкноты с одним номиналом.
 */
@AllArgsConstructor
@Getter
public class StorageCellSber implements StorageCell {

    private final ArrayDeque<Banknote> banknotes = new ArrayDeque<>();

    public void addBanknote(Banknote banknote) {
        this.banknotes.push(banknote);
    }

    public Banknote getBanknote() {
        return this.banknotes.poll();
    }

    public List<Banknote> getAllBanknotes() {
        return banknotes.clone().stream().toList();
    }

    @Override
    public Nominal getStorageBanknotesNominal() {
        if (this.banknotes.size() == 0) {
            return null;
        }

        return this.banknotes.stream().findAny().get().nominal();
    }

    @Override
    public int compareTo(StorageCell storageCell) {

        if(this == storageCell) {
            return 0;
        }

        if (this.banknotes.stream().findAny().isPresent()) {
            return storageCell
                    .getAllBanknotes()
                    .stream()
                    .findAny()
                    .get()
                    .nominal()
                    .getNominalValueSize()
                    -
                    this.banknotes
                            .stream()
                            .findAny()
                            .get()
                            .nominal().getNominalValueSize();
        }

        return -1;
    }
}
