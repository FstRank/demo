package com.itheima.thread.buis;

public class JoinTest implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("I am sub");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread sub = new Thread(new JoinTest());
        sub.start();
        sub.join();
        System.out.println("I am main");
    }
}