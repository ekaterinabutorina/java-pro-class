package ru.vtb.third.hometask.service;

public interface CustomExecutorService {

    void execute(Runnable r);

    void shutdown();
}
