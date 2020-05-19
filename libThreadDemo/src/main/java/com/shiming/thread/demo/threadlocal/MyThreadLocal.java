package com.shiming.thread.demo.threadlocal;

import java.util.HashMap;
import java.util.Map;

/**
 * 类说明：自己实现的ThreadLocal
 */
public class MyThreadLocal<T> {
    /*存放变量副本的map容器，以Thread为键，变量副本为value*/

    private Map<Thread,T> threadTMap = new HashMap<>();

    /**
     * 不去抢线程了，但是会去抢这个锁，对象锁，
     * 就好像很多的篮球放到很多的柜子里面，但是里面只有一把锁能开开
     *
     * @return
     */
    public synchronized T get(){
        return  threadTMap.get(Thread.currentThread());
    }

    /**
     * 这个锁很精华了
     * @param t
     */
    public synchronized void set(T t){
        threadTMap.put(Thread.currentThread(),t);
    }

}
