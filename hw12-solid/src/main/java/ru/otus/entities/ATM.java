package ru.otus.entities;

import java.util.List;
import java.util.Set;

/**
 * Базовое поведение банкомата.
 */
public interface ATM {

    /**
     * Получение остатка денег в банкомате.
     */
    Long getBalance();

    /**
     * Выдача наличных.
     */
    List<Banknote> getCash(Long value);

    /**
     * Внесение наличных.
     */
    List<Banknote> insertCash(List<Banknote> banknotes);
}
