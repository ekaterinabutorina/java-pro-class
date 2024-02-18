package ru.vtb.forth.hometask;

import ru.vtb.forth.hometask.service.UserService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext("ru.vtb.forth.hometask");
        var bean = context.getBean(UserService.class);
        bean.createUsersTable();
        bean.addUser(1L, "Ivan");
        bean.addUser(2L, "Petr");
        bean.addUser(3L, "Oleg");
        bean.addUser(4L, "Marina");
        bean.getAllUsers();
        bean.getUser(1L);
        bean.deleteUser(1L);
        bean.getAllUsers();
    }
}
