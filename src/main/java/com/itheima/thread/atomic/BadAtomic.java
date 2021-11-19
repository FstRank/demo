package com.itheima.thread.atomic;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class BadAtomic {
    AtomicInteger i = new AtomicInteger(0);
    static int j=0;

    public void badInc(){
        int k = i.incrementAndGet();
        System.out.println("--->"+k);
        try {
            Thread.sleep(new Random().nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        j=k;
    }

    public static void main(String[] args) throws InterruptedException {
        BadAtomic atomic = new BadAtomic();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                atomic.badInc();
            }).start();
        }
        Thread.sleep(3000);
        System.out.println(atomic.j);
    }
}