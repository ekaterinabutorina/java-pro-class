package ru.first.hometask.task;

import ru.first.hometask.annotation.AfterSuite;
import ru.first.hometask.annotation.BeforeSuite;
import ru.first.hometask.annotation.CsvSource;
import ru.first.hometask.annotation.Test;

public class Box {

    private int size;
    private String name;

    public Box(int size, String name) {
        this.size = size;
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Test(priority = 4)
    public void forthTest() {
        System.out.println("This is the forth pririty test.");
    }

    @Test(priority = 3)
    @CsvSource(param = "10,20")
    public void thirdTest(int a, int b) {
        System.out.println("This is the third pririty test with params a = " + a + " b = " + b);
    }

    @Test(priority = 2)
    public void secondTest() {
        System.out.println("This is the second pririty test.");
    }

    @Test(priority = 10)
    @CsvSource(param = "10,Java ,20, true")
    public void firstTest(int a, String b, int c, boolean d) {
        System.out.println("This is the first pririty test with params a = " + a + " b = " + b + " c = " + c + " d = " + d);
    }

    @BeforeSuite
    @CsvSource(param = "10, Java, 20, true")
    public static void printBefore() {
        System.out.println("This is the before suite method");
    }

    @AfterSuite
    public static void printAfter() {
        System.out.println("This is the after suite method");
    }

}
