package ru.otus.test;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

public class Tests {
    @Before
        String hello1 = "Hello World";

    @Test
    public void test1() {
        "Hello World".equals(hello1);
        // System.out.println(hello);
    }

    @After
    public void after1() {
        String hello1 = null;
    }

    @Before
        String hello2 = "Hello World";


    @Test
    public void test2() throws Exception {
        if (!"sdfsda".equals(hello2))
            throw new Exception("press F");
    }

    @After
    public void after2() {
        String hello2 = null;
    }

    @Before
    String hello3 = "Hello";


    @Test
    public void test3() throws Exception {
        System.out.println(hello3);
    }

    @After
    public void after3() {
        String hello3 = null;
    }

}
