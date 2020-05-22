package com.shiming.thread.demo.demo1.demo0;

public class EndRunnableDemo {

    private static class StudentRunnable implements Runnable {

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();

            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("循环运行中:" + threadName);
            }
            // 最后看看标记
            System.out.println(threadName = " interrput最后：" + Thread.currentThread().isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        StudentRunnable studentRunnable = new StudentRunnable();

        Thread thread = new Thread(studentRunnable, "Derry");
        thread.start();

        Thread.sleep(60);
        thread.interrupt(); // 发起中断信号
    }

}
