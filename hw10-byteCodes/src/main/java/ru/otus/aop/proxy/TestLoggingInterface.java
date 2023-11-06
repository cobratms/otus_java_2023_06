package ru.otus.aop.proxy;

public interface TestLoggingInterface {

    void calculation(int param);

    @Log
    void calculation(int param, int param1);

    void calculation(int param, int param1, int param2);
}
