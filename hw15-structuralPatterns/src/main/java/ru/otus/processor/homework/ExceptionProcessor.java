package ru.otus.processor.homework;

import ru.otus.model.Message;
import ru.otus.processor.Processor;

import java.time.OffsetTime;

public class ExceptionProcessor implements Processor {

    private final DateTimeProvider dateTimeProvider;

    public ExceptionProcessor(DateTimeProvider dateTimeProvider) {
        this.dateTimeProvider = dateTime Provider;
    }

    @Override
    public Message process(Message message) {

        if (dateTimeProvider.getDate().getDayOfMonth() % 2 == 0) {
            throw new IllegalStateException();
        }

        return message;
    }
}
