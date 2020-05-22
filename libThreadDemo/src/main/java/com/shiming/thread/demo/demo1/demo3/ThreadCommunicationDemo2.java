package com.shiming.thread.demo.demo1.demo3;

/**
 * 描述资源
 */
class Res2 {

    /**
     * name 是共享数据，被Thread-0 Thread-1公用使用
     */
    private String name;

    /**
     * id name 是共享数据，被Thread-0 Thread-1公用使用
     */
    private int id;

    /**
     * 对操作共享数据的地方加入同步锁的方式来解决安全问题
     * public synchronized(this) void put(String name) {
     */
    public synchronized void put(String name) {
        id += 1;
        // this.name = name + " 生成商品编号:" + id;
        System.out.println(Thread.currentThread().getName() + "生产者 生产了：" + this.id);
    }

    /**
     * 对操作共享数据的地方加入同步锁的方式来解决安全问题
     * public synchronized(this) void put(String name) {
     */
    public synchronized void out() {

        // this.name = name + " 消费商品编号:" + id;
        System.out.println(Thread.currentThread().getName() +  ">>>>>>>>>>>>>>>>>>>>>>>>>>>>> 消费者 消费了：" + this.id);

        id -= 1;
    }

}

/**
 * 描述生产者任务
 */
class ProduceRunnable2 implements Runnable {

    /**
     * 此变量已经不是共享数据了，因为：
     *              new Thread(produceRunnable).start();
     *              new Thread(consumeRunnable).start();
     *
     * 所以：Thread-0有自己的res     Thread-1也有自己的res
     */
    private Res2 res;

    ProduceRunnable2(Res2 res) {
        this.res = res;
    }

    /**
     * 执行线程任务
     */
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            res.put("面包🍞");
        }
    }
}

/**
 * 描述消费者任务
 */
class ConsumeRunnable2 implements Runnable {

    /**
     * 此变量已经不是共享数据了，因为：
     *              new Thread(produceRunnable).start();
     *              new Thread(consumeRunnable).start();
     *
     * 所以：Thread-0有自己的res     Thread-1也有自己的res
     */
    private Res2 res;

    ConsumeRunnable2(Res2 res) {
        this.res = res;
    }

    /**
     * 执行线程任务
     */
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            res.out();
        }
    }
}

/**
 * 多线程通讯案例（加入了synchronized解决了安全问题：）
 *
 * 由于以上程序本身就是多线程程序，所以寻找共享数据，然后对操作共享数据的地方加入同步锁的方式来解决安全问题 案例二
 *
 * 执行结果：每次不一样由CPU随机性决定的，CPU随机的执行：例如：这个线程执行一下，也有可能哪个线程执行一下，也可能这个线程执行完，等等：
 */
public class ThreadCommunicationDemo2 {

    public static void main(String[] args) {
        // 创建资源对象
        Res2 res = new Res2();

        // 创建生产者任务
        ProduceRunnable2 produceRunnable = new ProduceRunnable2(res);

        // 创建消费者任务
        ConsumeRunnable2 consumeRunnable = new ConsumeRunnable2(res);

        // 启动生产者任务
        new Thread(produceRunnable).start();

        // 启动消费者任务
        new Thread(consumeRunnable).start();
    }

}
