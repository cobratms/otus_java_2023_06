package ru.otus;

import ru.otus.handler.ComplexProcessor;
import ru.otus.handler.Handler;
import ru.otus.listener.ListenerPrinterConsole;
import ru.otus.processor.homework.ChangeProcessor;
import ru.otus.processor.homework.ExceptionProcessor;
import ru.otus.model.Message;
import ru.otus.processor.Processor;

import java.time.LocalDateTime;
import java.util.List;

public class HomeWork {

    public static void main(String[] args) {
        List<Processor> processors =
                List.of(
                        new ChangeProcessor(),
                        new ExceptionProcessor(LocalDateTime::now));

        Handler complexProcessor = new ComplexProcessor(processors, ex -> {
            System.err.println("ex was thrown: " + ex);
        });
        var listenerPrinter = new ListenerPrinterConsole();
        complexProcessor.addListener(listenerPrinter);

        var message =
                new Message.Builder(1L)
                        .field1("1")
                        .field2("2")
                        .field3("3")
                        .field6("4")
                        .field10("5")
                        .field11("6")
                        .field12("7")
                        .build();
        System.out.println("oldMessage:" + message);
        var result = complexProcessor.handle(message);
        System.out.println("result:" + result);

        complexProcessor.removeListener(listenerPrinter);
    }
}
