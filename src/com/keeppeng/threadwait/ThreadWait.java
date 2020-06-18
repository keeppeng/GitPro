package com.keeppeng.threadwait;

import java.util.LinkedList;
import java.util.Queue;

public class ThreadWait {
    public static void main(String[] args) {
        Thread t1 = new AddThread();
        Thread t2 = new GetThread();
        t1.start();
        t2.start();
    }
}

//单例模式创建一个单一实例对象
class QueueTask {
    private static QueueTask instance = null;

    public static synchronized QueueTask getInstance() {
        if (instance == null) {
            return new QueueTask();
        } else {
            return instance;
        }
    }

    private QueueTask() {
    }

    Queue<String> queueTask = new LinkedList<String>();

    public synchronized void addTask(String s) throws InterruptedException {
        Thread.sleep(10);
        queueTask.add(s);
        this.notifyAll();
        System.out.println("add success ...");
        //Thread.sleep(1000);
    }

    public synchronized String getTask() throws InterruptedException {
        while (queueTask.isEmpty()) {
            System.out.println("Queue empty...");
            //Thread.sleep(1000);
            this.wait();
        }
        //
        System.out.println("Going to output ...");
        return queueTask.remove();
    }
}

//创建两个线程一个add一个remove
class AddThread extends Thread {
    @Override
    public void run() {
        QueueTask queueTask = QueueTask.getInstance();
        int i = 0;
        for (int j = 0; j < 900; j++) {
            try {
                queueTask.addTask(i + "");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class GetThread extends Thread {
    @Override
    public void run() {
        QueueTask queueTask = QueueTask.getInstance();
        for (int j = 0; j < 900; j++) {
            try {
                queueTask.getTask();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
