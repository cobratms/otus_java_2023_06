package homework;

import annotations.After;
import annotations.Before;
import annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestStarter {

    public static void start(Class<?> someTestsClass) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        int testPassedCount = 0;
        int testNotPassedCount = 0;
        int testCount = 0;
        List<String> testMethodsName = TestStarter.getAllTestsMethods(someTestsClass);

        for (String methodName: testMethodsName) {
            testCount++;
            if(TestStarter.startTestByName(methodName, someTestsClass)) {
                testPassedCount++;
            } else {
                testNotPassedCount++;
            }
        }

        System.out.println("Successful tests: " + testPassedCount
                + "\nFailed  tests: " + testNotPassedCount
                + "\nAll tests: " + testCount);
    }

    private static List<String> getAllTestsMethods(Class<?> someTestsClass) {
        List<String> testMethods = new ArrayList<>();
        for (Method method : someTestsClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method.getName());
            }
        }
        return testMethods;
    }

    private static boolean startTestByName(String methodName, Class<?> someTestsClass) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        boolean isSuccessful = true;
        Constructor<?> constructor = someTestsClass.getConstructor();
        Object object = constructor.newInstance();

        TestStarter.invokeMethodByAnnotationName(someTestsClass, object, Before.class);
        try {
            TestStarter.invokeMethodByName(someTestsClass, object, methodName);
        } catch (Exception ignore) {
            isSuccessful = false;
        }
        TestStarter.invokeMethodByAnnotationName(someTestsClass, object, After.class);
        return isSuccessful;
    }

    private static void invokeMethodByAnnotationName(Class<?> clazz, Object object, Class<? extends Annotation> beforeClass) throws InvocationTargetException, IllegalAccessException {
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(beforeClass)) {
                TestStarter.invokeMethod(method, object);
            }
        }
    }

    private static void invokeMethodByName(Class<?> clazz, Object object, String methodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = clazz.getDeclaredMethod(methodName);
        TestStarter.invokeMethod(method, object);
    }

    private static void invokeMethod(Method method, Object object) throws InvocationTargetException, IllegalAccessException {
        method.setAccessible(true);
        method.invoke(object);
    }
}
