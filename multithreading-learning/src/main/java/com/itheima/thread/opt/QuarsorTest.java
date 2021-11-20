package com.itheima.thread.opt;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.SuspendableRunnable;

public class QuarsorTest {
    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 5; i++) {
            Fiber<Void> fiber = new Fiber<Void>("fiber-"+i, new SuspendableRunnable() {
                @Override
                public void run() throws SuspendExecution, InterruptedException {
                    System.out.println(Fiber.currentFiber().getId() +
                            ":" + Fiber.currentFiber().getName());
                    Fiber.sleep(60000);
                }
            });
            fiber.start();
        }
    }
}
