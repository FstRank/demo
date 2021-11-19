package com.itheima.thread.state;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: demo
 * @description:
 * @author: zhanghz001
 * @create: 2021-01-11 18:09
 **/
public class ThreadPoolState {
    public static void main(String[] args) {
        
        ThreadPoolExecutor pool = new MyThreadPool(3, 3,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(), new ThreadPoolExecutor.AbortPolicy());
        pool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println("finish task 1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("main");
        pool.shutdown();
    }
    
    public static void main1() {
        
        ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 3,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
        //在运行中runnable
        pool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1);
                    System.out.println("finish task 1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //shutdown状态,队列和正在执行的任务为空 就会tidying
        // pool.shutdown();
        pool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1);
                    System.out.println("finish task 2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("main");
        //stop 执行的任务为空 就会转换称为tidying 
        pool.shutdownNow();
        //terminated 执行完成后就变成termina状态
        
    }
}
