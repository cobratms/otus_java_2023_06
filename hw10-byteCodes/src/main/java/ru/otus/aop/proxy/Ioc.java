package ru.otus.aop.proxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("java:S106")
class Ioc {

    private Ioc() {}

    static Object createMyClassForAnnotation(Class<?> clazz, Class<? extends Annotation> annotation) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {

        List<Method> methods = Arrays.stream(clazz.getMethods())
                .filter(m -> Arrays.stream(m.getAnnotations())
                        .anyMatch(an -> an.annotationType()
                                .equals(annotation)))
                .toList();

        InvocationHandler handler = new DemoInvocationHandler(clazz.getConstructor().newInstance(), methods);
        return Proxy.newProxyInstance(
                Ioc.class.getClassLoader(),
                clazz.getInterfaces(),
                handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final Object myClass;
        private final List<Method> methods;

        DemoInvocationHandler(Object myClass, List<Method> methods) {
            this.myClass = myClass;
            this.methods = methods;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (methods.contains(myClass.getClass().getMethod(method.getName(), method.getParameterTypes()))) {
                System.out.println("executed method: " + method.getName() + ", param: " + Arrays.toString(args));
            }

            return method.invoke(myClass, args);
        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" + "myClass=" + myClass + '}';
        }
    }
}
