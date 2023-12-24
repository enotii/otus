package ru.otus;

import ru.otus.reflection.TestExecutor;
import ru.otus.test.Tests;

public class Main {
    public static void main(String[] args) throws RuntimeException, ClassNotFoundException {
        TestExecutor testExecutor = new TestExecutor();


        testExecutor.execute(Tests.class);
    }
}
