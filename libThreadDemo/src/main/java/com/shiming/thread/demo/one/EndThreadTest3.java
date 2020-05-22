package com.shiming.thread.demo.one;

// 第三版 和谐的停止
public class EndThreadTest3 {

    private static class UseThread implements Runnable {

        // 和谐的方式  类似于这个变量
        public static boolean isRunning;

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            // while (!isRunning) { // 默认是false  （true）
            while(!Thread.currentThread().isInterrupted()) {
                System.out.println(name + " ==== is run state:" + Thread.currentThread().isInterrupted());
            }
            System.out.println("while end flag:" + Thread.currentThread().isInterrupted());
        } // 如果结束 run 函数
    }

    public static void main(String[] args) throws InterruptedException {
        UseThread useThread = new UseThread();
        Thread thread = new Thread(useThread);
        thread.start();

        // 休眠
        Thread.sleep(10); // 10毫秒的时候 全部都是false
        thread.interrupt(); // 发起中断信号，如果能够停止下来，和暴力有什么区别
        // UseThread.isRunning = true;
    }
}
