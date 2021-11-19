package com.itheima.thread.state;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @program: demo
 * @description:
 * @author: zhanghz001
 * @create: 2021-01-11 17:09
 **/
public class ThreadState {
    @Test
    public void test1() {
        Thread thread = new Thread();
        //NEW
        System.out.println(thread.getState());
        thread.start();
        //RUNNABLE
        System.out.println(thread.getState());
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.getState());
    }
    
    @Test
    public void test2() {
        byte[] lock = new byte[0];
        Thread thread1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock) {
                
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        thread2.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Blocked
        System.out.println(thread1.getState());
        //Timed_WAITING
        System.out.println(thread2.getState());
    }
    
    //waiting
    @Test
    public void test3() {
        byte[] lock = new byte[0];
        
        //
        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        //
        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                lock.notify();
            }
        });
        thread1.start();
        
        //
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread1.getState());
        thread2.start();
    }
}
