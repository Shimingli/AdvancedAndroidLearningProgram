package com.shiming.thread.demo.one;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类 说明：抛出InterruptedException异常的时候，要注意中断标志位
 */
public class HasInterrputException {

    private static SimpleDateFormat formater
            = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss_SSS");

    private static class UseThread extends Thread {

        public UseThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            String threadName = Thread.currentThread().getName();
            while (!isInterrupted()) { // 一直是false  true
                // TODO 第一版
                // System.out.println("线程内循环运行....");

                // TODO 第二版
                try {
                    System.out.println("UseThread:" + formater.format(new Date()));
                    Thread.sleep(3000);
                } catch (InterruptedException e) { // sleep 会把中断信号清除
                    System.out.println(threadName + " catch interrput flag is "
                            + isInterrupted() + " at "
                            + (formater.format(new Date())));
                    // TODO interrupt 需要在此内部 调用才能中断了，才能把被清楚的标记修改成true
                    interrupt();
                    e.printStackTrace();
                }
                System.out.println(threadName);
            }
            System.out.println(threadName + " interrput flag is " + isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread useThread = new UseThread("HasInterrputEx");
        useThread.start();
        System.out.println("Main:" + formater.format(new Date()));
        Thread.sleep(800);
        System.out.println("Main begin interrupt thread:" + formater.format(new Date()));
        useThread.interrupt(); // 被 InterruptedException e 人家清除
    }

}
