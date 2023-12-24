package ru.otus.reflection;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestExecutor {
    public void execute(Class<?> testedClass) throws ClassNotFoundException {

        Class<?> clazz = Class.forName(testedClass.getName());
        int failedTestsCounter = 0;

        List<Method> beforeMethods = getAnnotatedMethod(clazz.getDeclaredMethods(), Before.class, clazz);
        List<Method> testMethods = getAnnotatedMethod(clazz.getDeclaredMethods(), Test.class, clazz);
        List<Method> afterMethods = getAnnotatedMethod(clazz.getDeclaredMethods(), After.class, clazz);

        for (Method methods : testMethods) {
            Object objectClass = createObjectClass(clazz);
            try {
                runTests(beforeMethods, objectClass);
                methods.invoke(objectClass);
                System.out.println(methods.getName());
                System.out.println("----SUCCESS----");
            } catch (Exception e) {
                System.out.println(methods.getName());
                System.out.println("----FAILED----");
                failedTestsCounter++;
            }
            finally {
                runTests(afterMethods, objectClass);
            }
        }
        printStatisticForTest(testMethods.size(), failedTestsCounter);
    }

    private <T> T instantiate(Class<T> type) {
        try {
            return type.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<Method> getAnnotatedMethod(Method[] declaredMethods, Class<?> annotation, Class<?> clazz) {
        List<Method> methods = new ArrayList<>();
        for (Method m : declaredMethods) {
            try {
                if (clazz.getMethod(m.getName()).isAnnotationPresent((Class<? extends Annotation>) annotation)) {
                    methods.add(m);
                }
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        return methods;
    }

    private <T> T createObjectClass(Class<T> typeClass) {
        try {
            return instantiate(typeClass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void runTests(List<Method> method, Object testedClass) {
        for (Method m : method) {
            try {
                m.invoke(testedClass);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void printStatisticForTest(int allTestsCounter, int failedTestsCounter) {
        System.out.println("\t\t\t\t\t\t*****STATISTIC*****\n");
        System.out.println("\t\t\t\t\t\tTOTAL TESTS: (" + allTestsCounter + ")");
        System.out.print("Number of SUCCESSFUL tests:\t(" + (allTestsCounter - failedTestsCounter) + ")");
        System.out.println("\t\tNumber of FAILED tests:\t(" + failedTestsCounter + ")");
        System.out.println("--------------------------------------------------------------------");
    }
}
