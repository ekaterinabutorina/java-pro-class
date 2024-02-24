package ru.vtb.third.hometask.service;

import ru.vtb.third.hometask.executor.Execution;

import java.util.LinkedList;

public class LinkedTasksExecutorService implements CustomExecutorService {

    public static int capacity;
    public static int currentCapacity;
    public static LinkedList<Runnable> linkedTasks;

    private Execution execution;
    private boolean isShutDown;

    public LinkedTasksExecutorService(int capacity) {
        this.capacity = capacity;
        currentCapacity = 0;
        linkedTasks = new LinkedList<>();
        execution = new Execution();
        isShutDown = false;
    }

    @Override
    public void execute(Runnable r) {
        if (isShutDown) {
            throw new IllegalStateException("Can't add a task because of shutdown");
        }
        linkedTasks.add(r);
        execution.executeMethod();
    }

    @Override
    public void shutdown() {
        isShutDown = true;
        System.out.println("Started shutdown");
    }
}
