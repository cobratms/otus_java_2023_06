package ru.otus.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Тип и размер номинала.
 */
@AllArgsConstructor
@Getter
public enum Nominal {
    FIVE_THOUSAND(5000),
    TWO_THOUSAND(2000),
    ONE_THOUSAND(1000),
    FIVE_HUNDRED(500),
    ONE_HUNDRED(100),
    FIFTY(50);

    private final int nominalValueSize;
}
