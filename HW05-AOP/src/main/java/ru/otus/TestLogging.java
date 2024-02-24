package ru.otus;

public interface TestLogging {
    void additionOneParameterWithoutLog(int a);

    void additionOneParameterWithLog(int a);

    void additionTwoParametersWithoutLog(int a, String b);

    void additionTwoParametersWithLog(int a, String b);

    void additionThreeParametersWithoutLog(int a, String b, int c);

    void additionThreeParametersWithLog(int a, String b, int c);

}
