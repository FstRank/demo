package com.itheima.thread.opt;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorTest {
    
    public static void main(String[] args) {
        ArrayBlockingQueue queue = new ArrayBlockingQueue(2);
        
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                3,
                5,
                20, TimeUnit.SECONDS,
                queue,
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        System.out.println("reject");
                    }
                });
        
        while (true) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            System.out.println("123");
        }
        
    }
    
}
