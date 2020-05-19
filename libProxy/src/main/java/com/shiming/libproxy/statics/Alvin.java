package com.shiming.libproxy.statics;


import com.shiming.libproxy.Massage;
import com.shiming.libproxy.Wash;

/**
 *  实实现类： 提供马杀鸡服务的Alvin
 */
public class Alvin implements Massage, Wash {

    @Override
    public void massage() {

        System.out.println("massage");
    }

    @Override
    public void wash() {

        System.out.println("wash");
    }
}
