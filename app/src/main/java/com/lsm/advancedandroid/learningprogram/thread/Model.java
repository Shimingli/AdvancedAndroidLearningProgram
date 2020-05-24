package com.lsm.advancedandroid.learningprogram.thread;

import android.content.Context;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : ShiMing
 * @editor :
 * @description :
 * @created : 2020-05-08 17:36
 */
public class Model {
    private Context mContext;
    private ExecutorService globalThreadPool = Executors.newCachedThreadPool();

    private static Model model = new Model();

    private Model() {
//        ThreadLocal

//        AtomicInteger
//        ArrayBlockingQueue

        ArrayList

    }

    // 获得单例对象
    public static Model getInstance() {
        return model;
    }

    // 初始化
    public void init(Context context) {
        mContext = context;
    }

    // 获取全局线程池
    public ExecutorService getGlobalThreadPool() {
        return globalThreadPool;
    }
}
