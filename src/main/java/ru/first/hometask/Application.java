package ru.first.hometask;

import ru.first.hometask.runner.TestRunner;
import ru.first.hometask.task.Box;

import java.lang.reflect.InvocationTargetException;

public class Application {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        TestRunner.runTests(Box.class);
    }

}
