package com.xiangxue.lib;

public class DaemonThread {

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(getName() + "---" + i);
                }
            }
        };
        t.setDaemon(true); // 设置了守护线程
        t.start(); // 谁调用的 main， main管了我 我就守护main

        // 主线程，是为了 等 Thread t 10秒钟
        Thread.sleep(10000);

        // 非守护线程  主线程一直在等 Thread t 到底执行完了没有

        // 走到这里，代表主线程结束，主线程结束，不管t线程有没有结束，t线程都必须结束，因为t线程是守护线程，守护了main
    }

}
