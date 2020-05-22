package com.shiming.thread.demo.one;

// 第二版 和谐的停止
public class EndThreadTest2 {

    private static class UseThread extends Thread {

        // 和谐的方式  类似于这个变量
        public static boolean isRunning;

        @Override
        public void run() {
            super.run();
            String name = Thread.currentThread().getName();
            // while (!isRunning) { // 默认是false  （true）
            while(!isInterrupted()) {
                System.out.println(name + " ==== is run state:" + isInterrupted());
            }
            System.out.println("while end flag:" + isInterrupted());
        } // 如果结束 run 函数
    }

    public static void main(String[] args) throws InterruptedException {
        UseThread useThread = new UseThread();
        useThread.start();

        // 休眠
        Thread.sleep(10); // 10毫秒的时候 全部都是false
        useThread.interrupt(); // 发起中断信号，如果能够停止下来，和暴力有什么区别
        // UseThread.isRunning = true;
    }
}
