package ru.first.hometask.runner;

import ru.first.hometask.annotation.AfterSuite;
import ru.first.hometask.annotation.BeforeSuite;
import ru.first.hometask.annotation.CsvSource;
import ru.first.hometask.annotation.Test;
import ru.first.hometask.task.Box;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TestRunner {

    public static void runTests(Class cls) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = cls.getDeclaredMethods();
        Box box = new Box(3, "task-box");
        Method firstMethod = null;
        Method lastMethod = null;
        List<Method> testMethods = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                if (firstMethod != null) {
                    throw new RuntimeException("Ошибка! методов с аннотацией @BeforeSuite больше одного");
                }
                firstMethod = method;
            }

            if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
            }

            if (method.isAnnotationPresent(AfterSuite.class)) {
                if (lastMethod != null) {
                    throw new RuntimeException("Ошибка! методов с аннотацией @AfterSuite больше одного");
                }
                lastMethod = method;
            }
        }

        firstMethod.invoke(box);

        if (!testMethods.isEmpty()) {
            List<Method> sortedTestMethods = testMethods.stream()
                    .sorted(Comparator.comparing(testMethod -> testMethod.getAnnotation(Test.class).priority()))
                    .collect(Collectors.toList());

            sortedTestMethods.forEach(method -> {
                try {
                    if (!method.isAnnotationPresent(CsvSource.class)) {
                        method.invoke(box);
                        return;
                    }
                    List<String> annotationParams = List.of(method.getAnnotation(CsvSource.class).param().replace(" ", "").split(","));

                    Class[] methodParamTypes = method.getParameterTypes();
                    Object[] params = new Object[annotationParams.size()];

                    if (methodParamTypes.length != annotationParams.size()) {
                        throw new RuntimeException("Ошибка! В аннотации @CsvSource \"" + method.getAnnotation(CsvSource.class).param()
                                + "\" и методе \"" + method.getName() + "\" не совпадает кол-во параметров");
                    }

                    for (int i = 0; i < annotationParams.size(); i++) {
                        if (methodParamTypes[i] == String.class) {
                            params[i] = annotationParams.get(i);
                        } else if (methodParamTypes[i] == boolean.class) {
                            params[i] = annotationParams.get(i).equalsIgnoreCase("true");
                        } else if (methodParamTypes[i] == int.class) {
                            params[i] = Integer.valueOf(annotationParams.get(i));
                        }
                    }
                    method.invoke(box, params);

                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            });
        }


        lastMethod.invoke(box);
    }
}
