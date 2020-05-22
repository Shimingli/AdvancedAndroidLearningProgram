package com.shiming.thread.demo.demo1.demo3;

/**
 * 描述资源
 */
class Res {

    private String name;
    private int id;

    // 生产
    public void put(String name) { // 生产一个面包
        id += 1;
        this.name = name + " 商品编号:" + id;
        System.out.println(Thread.currentThread().getName() + "生产者 生产了：" + this.name);
    }

    // 消费
    public void out() {
        id -= 1;
        System.out.println(Thread.currentThread().getName() + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>> 消费者 消费了：" + this.name);
    }

}

/**
 * 描述生产者任务
 */
class ProduceRunnable implements Runnable {

    /**
     * 此变量已经不是共享数据了，因为：
     * new Thread(produceRunnable).start();
     * new Thread(consumeRunnable).start();
     * <p>
     * 所以：Thread-0有自己的res     Thread-1也有自己的res
     */
    private Res res;

    ProduceRunnable(Res res) {
        this.res = res;
    }

    /**
     * 执行线程任务
     */
    @Override
    public void run() {
        res.put("面包🍞");
    }
}

/**
 * 描述消费者任务
 */
class ConsumeRunnable implements Runnable {

    /**
     * 此变量已经不是共享数据了，因为：
     * new Thread(produceRunnable).start();
     * new Thread(consumeRunnable).start();
     * <p>
     * 所以：Thread-0有自己的res     Thread-1也有自己的res
     */
    private Res res;

    ConsumeRunnable(Res res) {
        this.res = res;
    }

    /**
     * 执行线程任务
     */
    @Override
    public void run() {
        res.out();
    }
}

/**
 * 多线程通讯案例
 * <p>
 * 案例一存在安全问题： 分析以下程序是否存在安全🔐问题：
 */
public class ThreadCommunicationDemo {

    public static void main(String[] args) {
        // 创建资源对象
        Res res = new Res();

        // 创建生产者任务
        ProduceRunnable produceRunnable = new ProduceRunnable(res);

        // 创建消费者任务
        ConsumeRunnable consumeRunnable = new ConsumeRunnable(res);

        // 启动生产者任务
        new Thread(produceRunnable).start();

        // 启动消费者任务
        new Thread(consumeRunnable).start();
    }

}
