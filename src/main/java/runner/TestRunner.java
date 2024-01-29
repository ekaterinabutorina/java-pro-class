package runner;

import annotation.AfterSuite;
import annotation.BeforeSuite;
import annotation.CsvSource;
import annotation.Test;
import task.Box;

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
        int countBeforeSuite = 0;
        int countAfterSuite = 0;
        Method firstMethod = null;
        Method lastMethod = null;
        List<Method> testMethods = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                countBeforeSuite++;
                if (countBeforeSuite > 1) {
                    System.out.println("Ошибка! методов с аннотацией @BeforeSuite больше одного");
                    return;
                }
                firstMethod = method;
            }

            if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
            }

            if (method.isAnnotationPresent(AfterSuite.class)) {
                countAfterSuite++;
                if (countAfterSuite > 1) {
                    System.out.println("Ошибка! методов с аннотацией @AfterSuite больше одного");
                    return;
                }
                lastMethod = method;
            }
        }

        if (countBeforeSuite < 1 || countAfterSuite < 1) {
            System.out.println("Ошибка! метода с аннотацией @BeforeSuite или @AfterSuite нет.");
            return;
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
                    List<String> annotationParams = List.of(method.getAnnotation(CsvSource.class).param().split(","));

                    Class[] methodParamTypes = method.getParameterTypes();
                    Object[] params = new Object[annotationParams.size()];

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
