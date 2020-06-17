package com.keeppeng.regex;

public class threadDemo {
    public static void main(String[] args) {
//        Thread t2 = new Thread();
//        t2.setDaemon(true);//设置守护线程后，主线程会在非守护线程全部执行完毕后结束
//        t2.start();
        testOne();
        //testTwo();
    }

    public static void testTwo() {
        Thread thread = new MyThread();
        thread.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("end........");
    }

    public static void testOne() {
        System.out.println("main start...");
        Thread t = new Thread() {
            public void run() {
                System.out.println("thread run...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread end.");
            }
        };
        t.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main end...");
    }
}
class MyThread extends Thread{

    public void run(){
        int i =0;
        while(! isInterrupted()){
            System.out.println(i++);
        }
    }
}