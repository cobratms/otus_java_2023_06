package ru.otus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.otus.entities.ATMSber;
import ru.otus.entities.Banknote;
import ru.otus.entities.Nominal;

import java.util.ArrayList;
import java.util.List;


public class ATMTest {

    private ATMSber atmSber = new ATMSber();

    @Test
    public void getBalanceTest(){
        Assertions.assertEquals(this.atmSber.getBalance(), 0L);
        Assertions.assertNotEquals(this.atmSber.getBalance(), 5550L);
    }

    @Test
    public void depositCashTest() {
        List<Banknote> banknotes = new ArrayList<>();
        banknotes.add(new Banknote(Nominal.FIVE_HUNDRED));
        banknotes.add(new Banknote(Nominal.FIVE_THOUSAND));
        banknotes.add(new Banknote(Nominal.FIFTY));
        banknotes.add(new Banknote(Nominal.FIFTY));

        this.atmSber.insertCash(banknotes);

        Assertions.assertEquals(this.atmSber.getBalance(), 5600L);
    }

    @Test
    public void cannotGetMoneyTest() {
        List<Banknote> banknotes = new ArrayList<>();
        banknotes.add(new Banknote(Nominal.FIVE_HUNDRED));
        banknotes.add(new Banknote(Nominal.FIVE_THOUSAND));
        banknotes.add(new Banknote(Nominal.FIVE_HUNDRED));
        banknotes.add(new Banknote(Nominal.FIVE_THOUSAND));
        banknotes.add(new Banknote(Nominal.FIFTY));
        this.atmSber.insertCash(banknotes);

        List<Banknote> money = this.atmSber.getCash(7300L);
        Assertions.assertNull(money);
    }

    @Test
    public void successGetMoneyTest() {
        List<Banknote> banknotes = new ArrayList<>();
        banknotes.add(new Banknote(Nominal.FIVE_HUNDRED));
        banknotes.add(new Banknote(Nominal.FIVE_THOUSAND));
        banknotes.add(new Banknote(Nominal.FIVE_HUNDRED));
        banknotes.add(new Banknote(Nominal.FIVE_THOUSAND));
        banknotes.add(new Banknote(Nominal.FIFTY));
        this.atmSber.insertCash(banknotes);

        List<Banknote> money = this.atmSber.getCash(5050L);
        Assertions.assertNotNull(money);
    }

    @Test
    public void successGetMoneyTest1000() {
        List<Banknote> banknotes = new ArrayList<>();
        banknotes.add(new Banknote(Nominal.FIVE_HUNDRED));
        banknotes.add(new Banknote(Nominal.FIVE_HUNDRED));
        this.atmSber.insertCash(banknotes);

        List<Banknote> money = this.atmSber.getCash(1000L);
        Assertions.assertNotNull(money);
    }
}
