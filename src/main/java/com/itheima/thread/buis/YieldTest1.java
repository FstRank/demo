package com.itheima.thread.buis;

/**
 * @program: demo
 * @description:
 * @author: zhanghz001
 * @create: 2021-01-18 20:25
 **/
public class YieldTest1 {
    static byte[] lock = new byte[0];
    
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("notify1");
                Thread.yield();
                System.out.println("notify11");
                
            }
        });
        Thread t2 = new Thread(() -> {
            System.out.println("notify2");
            Thread.yield();
            System.out.println("notify22");
        });
        Thread t3 = new Thread(() -> {
            System.out.println("notify3");
            Thread.yield();
            System.out.println("notify33");
        });
        t1.start();
        t2.start();
        t3.start();
    }
}
