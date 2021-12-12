package com.thread.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *  创建2个线程，让两个线程都对count进行自增操作，要求实现有序自增
 */
public class SyncThreadTest {

    private int count = 0;

    public static void main(String args[]) {
        SyncThreadTest syncThreadTest = new SyncThreadTest();
        Thread thread1 = new Thread(() -> {
            while (true) {
                syncThreadTest.addSync2();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(() -> {
            while (true) {
                syncThreadTest.addSync2();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        thread2.start();
    }
    //普通方法
    private void add() {
        count++;
        System.out.println(count);
    }
    //同步方法
    private synchronized void addSync() {
        count++;
        System.out.println(count);
    }
    //同步方法
    private void addSync2() {
        synchronized (this){
            count++;
            System.out.println(count);
        }
    }



    //重入锁,注意释放锁
    private Lock lock = new ReentrantLock();
    private void addSync3() {
        lock.lock();
        try{
            count++;
            System.out.println(count);
        }finally {
            lock.unlock();
        }


    }
}
