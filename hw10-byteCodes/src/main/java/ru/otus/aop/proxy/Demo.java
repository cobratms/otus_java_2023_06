package ru.otus.aop.proxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;

public class Demo {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        action(Log.class);
    }

    private static void action(Class<? extends Annotation> annotation) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        TestLoggingInterface testLog = (TestLoggingInterface) Ioc.createMyClassForAnnotation(TestLogging.class, annotation);

        testLog.calculation(1);
        testLog.calculation(1, 2);
        testLog.calculation(1, 2, 3);
    }
}
