package com.keeppeng.regex.com.keeppeng.thread;

public class DeathLock {
    public static void main(String[] args) {
        Thread t1 = new Thread01();
        Thread t2 = new Thread02();
        t1.start();
        t2.start();
    }
}

class Count {
    public static final Object lock01 = new Object();
    public static final Object lock02 = new Object();
    static int i = 0;

    public void add(int n) {
        synchronized (Count.lock01) {
            i += n;
            synchronized (Count.lock02) {
                System.out.println("i = " + i);
            }
        }
    }

    public void dec(int n) {
        synchronized (Count.lock01) {
            i -= n;
            synchronized (Count.lock02) {
                System.out.println("i = " + i);
            }
        }
    }

    public int getI() {
        return this.i;
    }
}

class Thread01 extends Thread {

    public void run() {
        try {
            synchronized (Count.lock01) {
                System.out.println("get Lock 1");
                //Thread.sleep(800);
                synchronized (Count.lock02) {
                    System.out.println("get Lock 2");
                    Thread.sleep(100);
                }
                System.out.println("release 2");
            }
            System.out.println("release 1");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Thread02 extends Thread {

    @Override
    public void run() {
        try {
            synchronized (Count.lock01) {
                System.out.println("get Lock 1");
                //Thread.sleep(800);
                synchronized (Count.lock02) {
                    System.out.println("get Lock 2");
                    Thread.sleep(100);
                }
                System.out.println("release 02");
            }
            System.out.println("release 01");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
