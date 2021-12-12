package com.thread.test;

/**
 *  线程通过yeild方法礼让，礼让有可能不会成功
 */
public class TestYield {
    public static void main(String[] args) {
        MyYield myYield = new MyYield();
        new Thread(myYield,"a").start();
        new Thread(myYield,"b").start();
    }
}

class MyYield implements Runnable {
    @Override
    public void run() {
        System.out.println("线程" + Thread.currentThread().getName() + "开始执行");
        Thread.yield();
        System.out.println("线程" + Thread.currentThread().getName() + "停止执行");
    }
}