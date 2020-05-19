package com.shiming.libproxy.agent;


import com.shiming.libproxy.Massage;
import com.shiming.libproxy.Wash;

/**
 * 代理对象：马杀鸡经纪人
 */
public class Agent implements Massage, Wash {

    private final Massage massage;

    public Agent(Massage massage) {
        this.massage = massage;
    }

    //....前置处理
    public void before() {
        System.out.println("一条龙服务,包君满意");
    }

    //....后置处理
    public void after() {
        System.out.println("满意度调查");
    }

    @Override
    public void massage() {
        before();
        massage.massage();
        after();
    }

    @Override
    public void wash() {
        System.out.println("wash");
    }
}
