package ru.otus.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Utils {
    public static <T> T instantiate(Class<T> type) {
        try {
            return type.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Class<?>[] toClasses(Object[] args) {
        return Arrays.stream(args).map(Object::getClass).toArray(Class<?>[]::new);
    }

    public static List<Method> getAnnotatedMethod(Method[] declaredMethods, Class<?> annotation, Class<?> clazz) {
        List<Method> methodsVsAnnotation = new ArrayList<>();
        for (Method m : declaredMethods) {
            try {
                if (clazz.getMethod(m.getName()).isAnnotationPresent((Class<? extends Annotation>) annotation)) {
                    methodsVsAnnotation.add(m);
                }
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        return methodsVsAnnotation;
    }

    public static <T> T getObjectClass(Class<T> typeClass) {
        try {
            return instantiate(typeClass);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void getAnnotatedMethods(List<Method> method, Object testedClass) {
        System.out.println();
        for (Method m : method) {
            try {
                m.invoke(testedClass);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void getStatisticForTest(int allTestsCounter, int failedTestsCounter) {
        System.out.println("\t\t\t\t\t\t*****STATISTIC*****\n");
        System.out.println("\t\t\t\t\t\tTOTAL TESTS: (" + allTestsCounter + ")");
        System.out.print("Number of SUCCESSFUL tests:\t(" + (allTestsCounter - failedTestsCounter) + ")");
        System.out.println("\t\tNumber of FAILED tests:\t(" + failedTestsCounter + ")");
        System.out.println("--------------------------------------------------------------------");
    }
}
