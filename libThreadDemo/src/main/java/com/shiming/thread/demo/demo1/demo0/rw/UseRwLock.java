package com.shiming.thread.demo.demo1.demo0.rw;


import com.shiming.thread.demo.demo1.demo1.SleepTools;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 类说明： TODO 同学们：这里开始采用 【读写锁】
 */
public class UseRwLock implements GoodsService{

    private GoodsInfo goodsInfo;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock getLock = lock.readLock(); // 读锁
    private final Lock setLock = lock.writeLock(); // 写锁

    public UseRwLock(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    // 读取
    @Override
    public GoodsInfo getNum() {
        getLock.lock();
        try {
            SleepTools.ms(5);
            return this.goodsInfo;
        }finally {
            getLock.unlock();
        }
    }

    // 写入
    @Override
    public void setNum(int number) {
        setLock.lock();
        try {
            SleepTools.ms(5);
            goodsInfo.changeNumber(number);
        }finally {
            setLock.unlock();
        }
    }

}
