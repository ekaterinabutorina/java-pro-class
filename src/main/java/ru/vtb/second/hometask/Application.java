package ru.vtb.second.hometask;

import ru.vtb.second.hometask.employee.Employee;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {


        List<Integer> numbers = List.of(5, 2, 10, 9, 4, 3, 10, 1, 13);

        // Найдите в списке целых чисел 3-е наибольшее число (пример: 5 2 10 9 4 3 10 1 13 => 10)
        System.out.println("The third biggest number is = " +
                numbers.stream()
                        .sorted(Comparator.reverseOrder())
                        .collect(Collectors.toList())
                        .get(2)
        );

        // Найдите в списке целых чисел 3-е наибольшее «уникальное» число (пример: 5 2 10 9 4 3 10 1 13 => 9, в отличие от прошлой задачи здесь разные 10 считает за одно число)
        System.out.println("The third biggest unique number is = " +
                numbers.stream()
                        .sorted(Comparator.reverseOrder())
                        .distinct()
                        .collect(Collectors.toList())
                        .get(2)
        );


        List<Employee> employees = List.of(
                new Employee("Ivan", 23, Employee.Position.ACCOUNTANT),
                new Employee("Maria", 33, Employee.Position.MANAGER),
                new Employee("Bogdan", 45, Employee.Position.ENGINEER), //
                new Employee("Anna", 20, Employee.Position.INTERN),
                new Employee("Sergey", 37, Employee.Position.ACCOUNTANT),
                new Employee("Igor", 34, Employee.Position.ENGINEER), //
                new Employee("Elena", 44, Employee.Position.ACCOUNTANT),
                new Employee("Marina", 77, Employee.Position.ENGINEER), //
                new Employee("Petr", 19, Employee.Position.ENGINEER),
                new Employee("Inna", 26, Employee.Position.ENGINEER)
        );

        // Имеется список объектов типа Сотрудник (имя, возраст, должность), необходимо получить список имен 3 самых старших сотрудников с должностью «Инженер», в порядке убывания возраста
        System.out.println("3 the most old engineers are = " +
                employees.stream()
                        .filter(employee -> employee.getPosition() == Employee.Position.ENGINEER)
                        .sorted(Comparator.comparing(Employee::getAge).reversed())
                        .map(Employee::getName)
                        .collect(Collectors.toList()).subList(0, 3)
        );

        //  Имеется список объектов типа Сотрудник (имя, возраст, должность), посчитайте средний возраст сотрудников с должностью «Инженер»
        System.out.println("The average engineer age is = " +
                employees.stream()
                        .filter(employee -> employee.getPosition() == Employee.Position.ENGINEER)
                        .mapToInt(Employee::getAge)
                        .average().getAsDouble()
        );

        //  Найдите в списке слов самое длинное
        List<String> words = List.of(
                "qqqqq",
                "wwwwwwwwwwwwwwwwwww",
                "ppppppppppp",
                "llllllllllllllllllllllllllll",
                "eeeeeeee",
                "OOOOOOOOOOOOOOOOOOOOOOOO",
                "tt");

        System.out.println("The longest word is = " +
                words.stream()
                        .sorted(Comparator.comparing(String::length).reversed())
                        .collect(Collectors.toList()).get(0)
        );
    }
}
