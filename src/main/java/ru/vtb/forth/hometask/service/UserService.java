package ru.vtb.forth.hometask.service;

import ru.vtb.forth.hometask.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUsersTable() {
        userRepository.createUsersTable();
    }

    public void deleteUser(Long userId) {
        userRepository.deleteUser(userId);
        System.out.println("User id = " + userId + " was deleted.");
    }

    public void addUser(Long userId, String userName) {
        userRepository.createUser(userId, userName);
        System.out.println("User id = " + userId + " was created.");
    }

    public List<String> getAllUsers() {
        var users = userRepository.findAll();
        if (!CollectionUtils.isEmpty(users)) {
            System.out.println("Got " + users.size() + " users");
        } else {
            System.out.println("No users found");
        }
        return users;
    }

    public String getUser(Long userId) {
        var userName = userRepository.getById(userId);
        System.out.println("Got user id = " + userId);
        return userName;
    }
}
