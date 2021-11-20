package com.itheima.thread.forkjoin;

import java.util.concurrent.*;

public class SumTask {

    private static final Integer MAX = 2;

    static class SubTask extends RecursiveTask<Integer> {
        // 子任务开始计算的值
        private Integer start;

        // 子任务结束计算的值
        private Integer end;

        public SubTask(Integer start , Integer end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if(end - start < MAX) {
                //小于边界，开始计算
                System.out.println("start = " + start + ";end = " + end);
                Integer totalValue = 0;
                for(int index = this.start ; index <= this.end  ; index++) {
                    totalValue += index;
                }
                return totalValue;
            }else {
                //否则，中间劈开继续拆分
                SubTask subTask1 = new SubTask(start, (start + end) / 2);
                subTask1.fork();
                SubTask subTask2 = new SubTask((start + end) / 2 + 1 , end);
                subTask2.fork();
                return subTask1.join() + subTask2.join();
            }
        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        Future<Integer> taskFuture =  pool.submit(new SubTask(1,1000));
        try {
            Integer result = taskFuture.get();
            System.out.println("result = " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace(System.out);
        }
    }
}