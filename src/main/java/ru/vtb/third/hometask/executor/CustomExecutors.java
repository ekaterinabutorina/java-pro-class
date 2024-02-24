package ru.vtb.third.hometask.executor;

import ru.vtb.third.hometask.service.CustomExecutorService;
import ru.vtb.third.hometask.service.LinkedTasksExecutorService;

public class CustomExecutors {

    public static CustomExecutorService customNewFixedThreadPool(int capacity) {
        return new LinkedTasksExecutorService(capacity);
    }
}
