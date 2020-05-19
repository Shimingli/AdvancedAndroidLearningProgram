package com.shiming.libproxy;


import com.shiming.libproxy.agent.Agent;
import com.shiming.libproxy.statics.Alvin;
import com.shiming.libproxy.statics.Lucy;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import sun.misc.ProxyGenerator;

/**
 * https://www.cnblogs.com/MOBIN/p/5597215.html
 */
public class MyClass {

    public static void main(String[] args) throws Exception {
        //静态代理
        Massage message = new Lucy();
        Agent agent = new Agent(message);

        agent.massage();
        agent.wash();





        System.out.println("动态代理的开始 --------");
        //动态代理
        final Alvin alvin = new Alvin();


        Object o = Proxy.newProxyInstance(MyClass.class.getClassLoader(),
                new Class[]{Massage.class,Wash.class}, new InvocationHandler() {
                    @Override
                    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
//                        System.out.println(o.toString());
                        return method.invoke(alvin,objects);
                    }
                });

        Massage massage = (Massage) o;
        massage.massage();
//
        Wash wash = (Wash) o;
        wash.wash();


        proxy();
    }


    private static void proxy() throws Exception {
        String name = Massage.class.getName() + "$Proxy0";
        //生成代理指定接口的Class数据
        byte[] bytes = ProxyGenerator.generateProxyClass(name, new Class[]{Massage.class});
        FileOutputStream fos = new FileOutputStream("libProxy/" + name + ".class");
        fos.write(bytes);
        fos.close();
    }
}
