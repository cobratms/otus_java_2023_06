package test;

import annotations.After;
import annotations.Before;
import annotations.Test;

public class SomeTests {

    @Before
    void init() {
        System.out.println("Start test");
    }

    @After
    void afterTest() {
        System.out.println("End test.");
    }

    @Test
    void compareInteger() {
        Integer first = null;
        Integer second = 2;

        int isEqual = first.compareTo(second);
    }

    @Test
    void compareString() {
        String expectedData = "data";
        String factData = "data";

        factData.equals(expectedData);
    }
}