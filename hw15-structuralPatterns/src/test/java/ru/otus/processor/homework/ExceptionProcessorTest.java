package ru.otus.processor.homework;

import org.junit.jupiter.api.Test;
import ru.otus.model.Message;
import ru.otus.model.ObjectForMessage;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.*;

class ExceptionProcessorTest {

    @Test
    void processWithException() {
        var f13 = new ObjectForMessage();
        var message = new Message.Builder(1)
                .field1("1")
                .field2("2")
                .field3("3")
                .field4("4")
                .field5("5")
                .field6("6")
                .field7("7")
                .field8("8")
                .field9("9")
                .field10("10")
                .field11("11")
                .field12("12")
                .field13(f13)
                .build();

        var processor = new ExceptionProcessor(TestDateTimeProvider::getEvenSecondDate);
        assertThatThrownBy(() -> processor.process(message))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void processWithoutException() {
        var f13 = new ObjectForMessage();
        var message = new Message.Builder(1)
                .field1("1")
                .field2("2")
                .field3("3")
                .field4("4")
                .field5("5")
                .field6("6")
                .field7("7")
                .field8("8")
                .field9("9")
                .field10("10")
                .field11("11")
                .field12("12")
                .field13(f13)
                .build();

        var processor = new ExceptionProcessor(TestDateTimeProvider::getOddSecondDate);
        assertThatCode(() -> processor.process(message))
                .doesNotThrowAnyException();
    }

    private static class TestDateTimeProvider {
        public static LocalDateTime getEvenSecondDate() {
            return LocalDateTime.of(2023, 11, 10, 0, 0);
        }

        public static LocalDateTime getOddSecondDate() {
            return LocalDateTime.of(2023, 11, 11, 0, 0);
        }
    }
}