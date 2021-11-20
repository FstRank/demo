package com.itheima.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrivateLock {
    Lock lock = new ReentrantLock();
    long start = System.currentTimeMillis();
    void read() {
        lock.lock();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        System.out.println("read time = "+(System.currentTimeMillis() - start));
    }

    public static void main(String[] args) {
        final PrivateLock lock = new PrivateLock();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    lock.read();
                }
            }).start();
        }
    }
}