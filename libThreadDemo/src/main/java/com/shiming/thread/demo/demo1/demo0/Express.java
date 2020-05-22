package com.shiming.thread.demo.demo1.demo0;

/**
 * 类说明：快递实体类
 */
public class Express {
    public final static String CITY = "ShangHai";
    private int km;/*快递运输里程数*/
    private String site;/*快递到达地点*/

    public Express() {
    }

    public Express(int km, String site) {
        this.km = km;
        this.site = site;
    }

    /* 变化公里数，然后通知处于wait状态并需要处理公里数的线程进行业务处理*/
    public synchronized void changeKm() {
        this.km = 101;
        notifyAll();
    }

    /* 变化地点，然后通知处于wait状态并需要处理地点的线程进行业务处理*/
    public synchronized void changeSite() {
        this.site = "BeiJing";
        notifyAll();
    }

    public synchronized void waitKm() {
        while (this.km < 100) {
            try {
                wait();
                System.out.println("里程数被唤醒了：" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("当前里程数：" + this.km + " 里程数发生了改变，把这个数据写到数据库里面去");

    }

    public synchronized void waitSite() {
        while (CITY.equals(this.site)) {
            try {
                wait();
                System.out.println("地点被唤醒了：" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("当前地点：" + this.site + "里程数发生了改变，把这个数据写到数据库里面去");
    }
}
