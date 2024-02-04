package ru.otus;

import ru.otus.ioc.Ioc;

public class Main {
    public static void main(String[] args) {
        TestLogging testLogging = Ioc.createInstance();
        testLogging.additionOneParameterWithoutLog(1);
        testLogging.additionOneParameterWithLog(1);
        testLogging.additionTwoParametersWithoutLog(2, "abc");
        testLogging.additionTwoParametersWithLog(2, "abc");
        testLogging.additionThreeParametersWithoutLog(3, "abc", 4);
        testLogging.additionThreeParametersWithLog(3, "abc", 4);
    }
}
