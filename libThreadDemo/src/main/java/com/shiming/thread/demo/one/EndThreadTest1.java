package com.shiming.thread.demo.one;

// 第一版 无法停止下来
public class EndThreadTest1 {

    private static class UseThread extends Thread {

        @Override
        public void run() {
            super.run();
            String name = Thread.currentThread().getName();
            while (true) {
                System.out.println(name + " ==== is run");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        UseThread useThread = new UseThread();
        useThread.start();

        // 休眠
        Thread.sleep(10);
        useThread.interrupt(); // 发起中断信号，如果能够停止下来，和暴力有什么区别
    }
}
