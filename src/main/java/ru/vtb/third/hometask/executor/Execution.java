package ru.vtb.third.hometask.executor;

import ru.vtb.third.hometask.service.LinkedTasksExecutorService;

public class Execution implements Runnable {

    public void executeMethod() {
        if (LinkedTasksExecutorService.currentCapacity < LinkedTasksExecutorService.capacity) {
            LinkedTasksExecutorService.currentCapacity++;
            Thread t = new Thread(new Execution());
            t.start();
        }
    }

    @Override
    public void run() {
        while (true) {
            if (LinkedTasksExecutorService.linkedTasks.size() != 0) {
                LinkedTasksExecutorService.linkedTasks.poll().run();
            }
        }
    }
}
