package com.itheima.thread.lock;

public class BadCounter {
    private volatile static int i=0;
    public int get(){
        return i;
    }
    public synchronized void inc(){
        int j=get();
        try {
            Thread.sleep(100);
            j++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        i=j;
    }

    public static void main(String[] args) throws InterruptedException {
        final BadCounter counter = new BadCounter();
        //不使用线程10次，对比使用线程10次，看结果
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    counter.inc();
                }
            }).start();
//            counter.inc();
        }
        Thread.sleep(3000);
        //理论上10才对。可是....
        System.out.println(counter.i);
    }
}