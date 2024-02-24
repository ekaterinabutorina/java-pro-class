package ru.vtb.third.hometask.task;

public class CustomTask implements Runnable {

    @Override
    public void run() {
        System.out.println("Started a thread = " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished a thread = " + Thread.currentThread().getName());
    }
}