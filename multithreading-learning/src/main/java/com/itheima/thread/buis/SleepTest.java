package com.itheima.thread.buis;

public class SleepTest{

    public static void main(String[] args) {
        final byte[] lock = new byte[0];
        new Thread(()->{
            synchronized (lock){
                System.out.println("start");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end");
            }
        }).start();

        new Thread(()->{
            synchronized (lock){
                System.out.println("need lock");
            }
        }).start();
    }
}