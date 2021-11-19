package com.itheima.thread.opt;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class TotalLock {
    //类创建的时间
    final long start = System.currentTimeMillis();
    //计数，总耗时
    AtomicLong totalTime = new AtomicLong(0);
    
    //缓存变量，思考：HashMap线程不安全，为啥这里可以用它？
    private Map<String, Long> map = new HashMap() {{
        put("count", 0L);
    }};
    // new ReentrantReadWriteLock
    ReentrantLock lock = new ReentrantLock();
    
    //查看map被写入了多少次
    public Map get() {
        
        lock.lock();
        try {
            
            Thread.currentThread().sleep(100);
            
            long end = System.currentTimeMillis();
            //最后操作完成的时间
            map.put("time", end);
            
            System.out.println(Thread.currentThread().getName() + ",read=" + (end - start));
            totalTime.addAndGet(end - start);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        
        return map;
    }
    
    //写入
    public Map set() {
        lock.lock();
        try {
            
            Thread.currentThread().sleep(100);
            
            //写入计数
            map.put("count", map.get("count") + 1);
            
            long end = System.currentTimeMillis();
            map.put("time", end);
            
            System.out.println(Thread.currentThread().getName() + ",write=" + (end - start));
            totalTime.addAndGet(end - start);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        
        return map;
    }
    
    public static void main(String[] args) throws InterruptedException {
        
        TotalLock count = new TotalLock();
        
        //读
        for (int i = 0; i < 9; i++) {
            new Thread(() -> {
                count.get();
            }).start();
        }
        //写
        for (int i = 0; i < 1; i++) {
            new Thread(() -> {
                count.set();
            }).start();
        }
        
        Thread.sleep(10000);
        System.out.println(count.map);
        System.out.println("读写总共耗时：" + count.totalTime.get());
    }
}
