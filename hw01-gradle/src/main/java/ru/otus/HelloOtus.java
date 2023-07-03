package ru.otus;

import com.google.common.collect.Lists;

import java.util.List;

public class HelloOtus {

    public static void main(String[] args) {
        List<String> fruits = Lists.newArrayList("orange", "banana", "kiwi",
                "mandarin", "date", "quince");

        for (String fruit: fruits) {
            System.out.println(fruit);
        }
    }
}
