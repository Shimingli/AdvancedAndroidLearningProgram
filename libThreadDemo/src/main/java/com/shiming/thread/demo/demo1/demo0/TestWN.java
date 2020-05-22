package com.shiming.thread.demo.demo1.demo0;

/**
 * 类说明：测试wait/notify/notifyAll
 */
public class TestWN {
    private static Express express = new Express(0, Express.CITY);

    /*检查里程数变化的线程,不满足条件，线程一直等待*/
    private static class CheckKm extends Thread {
        @Override
        public void run() {
            express.waitKm();
        }
    }

    /*检查地点变化的线程,不满足条件，线程一直等待*/
    private static class CheckSite extends Thread {
        @Override
        public void run() {
            express.waitSite();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {//三个线程,等待快递到达地点的变化
            new CheckSite().start();
        }
        for (int i = 0; i < 3; i++) {//三个线程,等待里程数的变化
            new CheckKm().start();
        }

        Thread.sleep(1000);
        express.changeKm();//快递里程数变化

        express.changeSite();
    }
}
