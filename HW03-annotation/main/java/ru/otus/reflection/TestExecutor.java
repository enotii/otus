package ru.otus.reflection;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

import java.lang.reflect.Method;
import java.util.List;

import static ru.otus.reflection.Utils.*;

public class TestExecutor {
    public static void execute(Class<?> testedClass) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(testedClass.getName());
        int failedTestsCounter = 0;

        List<Method> beforeMethods = getAnnotatedMethod(clazz.getDeclaredMethods(), Before.class, clazz);
        List<Method> testMethods = getAnnotatedMethod(clazz.getDeclaredMethods(), Test.class, clazz);
        List<Method> afterMethods = getAnnotatedMethod(clazz.getDeclaredMethods(), After.class, clazz);

        for (Method methods : testMethods) {
            Object objectClass = getObjectClass(clazz);
            getAnnotatedMethods(beforeMethods, objectClass);
            try {
                methods.invoke(objectClass);
                System.out.println(methods.getName());
                System.out.println("----SUCCESS----");
            } catch (Exception e) {
                System.out.println(methods.getName());
                System.out.println("----FAILED----");
                failedTestsCounter++;
            }
            getAnnotatedMethods(afterMethods, objectClass);
        }
       getStatisticForTest(testMethods.size(), failedTestsCounter);
    }


}
