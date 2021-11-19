package com.itheima.thread.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @program: demo
 * @description:
 * @author: zhanghz001
 * @create: 2021-11-02 15:18
 **/
public class SumTask1 {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> taskFuture = pool.submit(new SubTask(1, 10000));
        try {
            Integer result = taskFuture.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    
    private static final Integer MAX = 100;
    
    static class SubTask extends RecursiveTask<Integer> {
        //子任务开始计算的值
        private Integer start;
        
        //子线程结束计算的值
        private Integer end;
        
        public SubTask(Integer start, Integer end) {
            this.start = start;
            this.end = end;
        }
        
        @Override
        protected Integer compute() {
            if (end - start < MAX) {
                //小于边际,开始计算
                int totalValue = 0;
                for (int i = start; i <= end; i++) {
                    totalValue += i;
                }
                System.out.println("start: " + start + " ; end=: " + end + " ;total: " + totalValue);
                return totalValue;
            } else {
                //否则,中间劈开继续拆分
                SubTask subTask1 = new SubTask(start, (start + end) >> 1);
                subTask1.fork();
                SubTask subTask2 = new SubTask(((start + end) >> 1) + 1, end);
                subTask2.fork();
                
                return subTask1.join() + subTask2.join();
            }
        }
    }
}
