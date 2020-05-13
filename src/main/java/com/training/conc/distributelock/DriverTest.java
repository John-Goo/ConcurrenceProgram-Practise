package com.training.conc.distributelock;

public class DriverTest {

    public static void main_sharedlock(String[] args) {
        CuratorDistributeLockDemo lockDemo = new CuratorDistributeLockDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                lockDemo.execSharedLock();
            }).start();
        }

    }


    public static void main(String[] args) {
        CuratorDistributeLockDemo lockDemo = new CuratorDistributeLockDemo();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                lockDemo.execSemaphoreLock();
            }).start();
        }
    }


    public static void main_writereadlock(String[] args) throws InterruptedException {
        Thread.sleep(10*1000);
        CuratorDistributeLockDemo lockDemo = new CuratorDistributeLockDemo();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                new Thread(() -> {
                    lockDemo.execWriteLock();
                }).start();
            }
        }).start();
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                new Thread(() -> {
                    lockDemo.execReadLock();
                }).start();
                try {
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
