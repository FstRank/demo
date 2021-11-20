package com.itheima.thread.buis;

/**
 * @program: demo
 * @description:
 * @author: zhanghz001
 * @create: 2021-01-18 20:37
 **/
public class JoinTest23 {
    static byte[] lock = new byte[0];
    
    public static void main(String[] args) {
        fn();
    }
    
    private static void fn() {
        synchronized (lock) {
            Thread t1 = new Thread(() -> {
                synchronized (lock) {
                    System.out.println("线程的过程");
                }
            });
            
            t1.start();
            // try {
            //     t1.join();
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }
            System.out.println("main结束了");
        }
    }
}
