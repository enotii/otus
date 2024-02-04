package ru.otus.ioc;

import ru.otus.TestLogging;
import ru.otus.TestLoggingImpl;
import ru.otus.annotaion.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Ioc {
    public static TestLogging createInstance() {
        InvocationHandler handler = new DemoInvocationHandler(new TestLoggingImpl(), Log.class);
        return (TestLogging) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{TestLogging.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final TestLoggingImpl testInter;
        private final Set<String> methods;

        DemoInvocationHandler(TestLoggingImpl someInter, Class<? extends Annotation> annotation) {
            this.testInter = someInter;
            this.methods = getAnnotatedMethod(someInter.getClass(), annotation);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (methods.contains(method.getName() + Arrays.toString(method.getParameters()))) {
                System.out.println("\nexecuted method: " + method.getName() + ", param:" + Arrays.toString(args));
            }
            return method.invoke(testInter, args);
        }

        private Set<String> getAnnotatedMethod(Class<?> clazz, Class<? extends Annotation> annotation) {
            Set<String> methods = new HashSet<>();
            for (Method m : clazz.getDeclaredMethods()) {
                if (m.getAnnotation(annotation) != null) {
                    methods.add(m.getName() + Arrays.toString(m.getParameters()));
                }
            }
            return methods;
        }
    }
}
