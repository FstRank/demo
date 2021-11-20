package com.itheima.thread.opt;

import java.util.concurrent.atomic.AtomicLong;

public class BadSync implements Runnable{
	//开始时间
    long start = System.currentTimeMillis();
    //计数器，统计所有线程执行完的总耗时
    AtomicLong atomicLong = new AtomicLong(0);
    
    int i=0;
    public synchronized void inc(){
        //...

        i=i+1;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        inc();
        atomicLong.getAndAdd(System.currentTimeMillis() - start);
    }


    public static void main(String[] args) throws InterruptedException {
        BadSync sync = new BadSync();

        for (int i = 0; i < 5; i++) {
            new Thread(sync).start();
        }

        Thread.sleep(3000);

        System.out.println("最终计数：i="+ sync.i);
        System.out.println("最终耗时：time="+sync.atomicLong.get());

    }
}
