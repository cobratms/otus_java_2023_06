package ru.otus.entities;

import java.util.List;

/**
 * Ячейка, в которой хранятся банкноты с одним номиналом.
 */
public interface StorageCell extends Comparable<StorageCell>{

    /**
     * Добавить банкноту.
     */
    void addBanknote(Banknote banknote);

    /**
     * Получить банкноту.
     */
    Banknote getBanknote();

    /**
     * Получить все банкноты.
     */
    List<Banknote> getAllBanknotes();

    /**
     * Получить номинал хранящихся банкнот.
     */
    Nominal getStorageBanknotesNominal();
}
