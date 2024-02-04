package ru.otus;

import ru.otus.annotaion.Log;

public class TestLoggingImpl implements TestLogging {
    @Override
    public void additionOneParameterWithoutLog(int a) {
    }

    @Log
    @Override
    public void additionOneParameterWithLog(int a) {
    }

    @Override
    public void additionTwoParametersWithoutLog(int a, String b) {

    }

    @Log
    @Override
    public void additionTwoParametersWithLog(int a, String b) {

    }

    @Override
    public void additionThreeParametersWithoutLog(int a, String b, int c) {

    }

    @Log
    @Override
    public void additionThreeParametersWithLog(int a, String b, int c) {

    }

}
