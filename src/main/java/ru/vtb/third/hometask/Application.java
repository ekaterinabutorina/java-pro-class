package ru.vtb.third.hometask;

import ru.vtb.third.hometask.executor.CustomExecutors;
import ru.vtb.third.hometask.service.CustomExecutorService;
import ru.vtb.third.hometask.task.CustomTask;

public class Application {

    public static void main(String[] args) {

        CustomExecutorService executor = CustomExecutors.customNewFixedThreadPool(10);

        for (int i = 0; i < 20; i++) {
            System.out.println("Iteration = " + i);
            executor.execute(new CustomTask());
        }

        executor.shutdown();
    }
}
