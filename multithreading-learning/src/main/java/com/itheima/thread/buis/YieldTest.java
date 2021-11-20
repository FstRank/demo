package com.itheima.thread.buis;

public class YieldTest{

    public static void main(String[] args) throws InterruptedException {
        final byte[] lock = new byte[0];
        
        //让出执行权，但是锁不释放
        Thread t1 = new Thread(()->{
            synchronized (lock){
                System.out.println("t1 : before yield");
                Thread.yield();
                System.out.println("t1 : after yield");
            }
        });

        //可以抢t1，但是拿不到锁，白费
        Thread t2 = new Thread(()->{
            synchronized (lock){
                System.out.println("t2 : need lock");
            }
        });

        //不需要锁，可以抢t1的执行权，但是能不能抢得到，不一定
        //所以多执行几次，会看到不同的结果……
        Thread t3 = new Thread(()->{
            System.out.println("t3 : no lock");
        });


        t1.start();
        t2.start();
        t3.start();
    }
}