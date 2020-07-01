package com.keeppeng.threadwait;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executorServices = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 6; i++) {
            executorServices.submit(new Task(i+""));
        }
        //关闭线程池
        executorServices.shutdown();
    }
}

class Task implements Runnable {

    private final String name;

    Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Running " + name);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Ending " + name);
    }
}