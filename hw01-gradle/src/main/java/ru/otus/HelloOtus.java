package ru.otus;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.util.List;

@Slf4j
public class HelloOtus {

    public static void main(String[] args) {
        List<String> fruits = Lists.newArrayList("orange", "banana", "kiwi",
                "mandarin", "date", "quince");

        for (String fruit: fruits) {
            log.info(fruit);
        }
    }
}
