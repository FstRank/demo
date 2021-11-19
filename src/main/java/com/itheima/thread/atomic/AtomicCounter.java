package com.itheima.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounter {
    private static AtomicInteger i = new AtomicInteger(0);
    public int get(){
        return i.get();
    }
    public void inc(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        i.getAndAdd(1);
    }

    public static void main(String[] args) throws InterruptedException {
        final AtomicCounter counter = new AtomicCounter();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    counter.inc();
                }
            }).start();
        }
        Thread.sleep(3000);
        //同样可以正确输出10
        System.out.println(counter.i.get());
    }
}