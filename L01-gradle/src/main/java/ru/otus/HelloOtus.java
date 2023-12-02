/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ru.otus;

import com.google.common.base.Splitter;


/**
 * To start the application:
 * ./gradlew build
 * java -jar ./L01-gradle/build/libs/gradleHelloWorld-0.1.jar
 * <p>
 * To unzip the jar:
 * unzip -l L01-gradle.jar
 * unzip -l gradleHelloWorld-0.1.jar
 */
public class HelloOtus {
    public static void main(String... args) {
        String str = "здарова . Otus.!";
        Iterable<String> result = Splitter.on('.')
                .trimResults()
                .omitEmptyStrings()
                .split(str);
        System.out.println(result);
    }

}

