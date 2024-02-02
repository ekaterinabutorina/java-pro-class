package ru.vtb.second.hometask.employee;

public class Employee {

    private String name;

    private Integer age;
    private Position position;

    public enum Position {
        ENGINEER,
        MANAGER,
        ACCOUNTANT,
        INTERN,
    }

    public Employee(String name, Integer age, Position position) {
        this.name = name;
        this.age = age;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Employee() {
    }
}
