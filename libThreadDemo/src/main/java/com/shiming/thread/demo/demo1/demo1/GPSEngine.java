package com.shiming.thread.demo.demo1.demo1;

/**
 * synchronized 内置🔒  JDK提供的 内部已经封装
 */
public class GPSEngine {

    private static GPSEngine gpsEngine;

    public static synchronized GPSEngine getInstance() {
        if (gpsEngine == null) {
            // Thread-1 释放CPU执行器   Thread-0来执行了 结果 new GPSEngine   此时Thread-1获得执行资格 有 new
            gpsEngine = new GPSEngine();
        }
        return gpsEngine;
    }

    /*public static GPSEngine getGpsEngine() {
        if (null == gpsEngine) {
            synchronized (GPSEngine.class) { // 类锁  还没有new GPSEngine  == 没有this
                if (null == gpsEngine) {
                    gpsEngine = new GPSEngine();
                }
            }
        }
        return gpsEngine;
    }*/


}
