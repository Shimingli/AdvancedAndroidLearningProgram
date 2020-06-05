package com.shiming.thread.demo;

public class MyClass {

    public static void main(String[] args) {
        Integer int1 = new Integer(1);
        Integer int2 = new Integer(1);
        //为什么你比较大小就是 比数字的大小，但是！=，,你却比较的是内存地址？？？ 不科学呀
        System.out.println(int1 >= int2); // 檢查兩者的值，     true
        System.out.println(int1 <= int2); // 檢查兩者的值，     true
        System.out.println(int1 != int2); // 檢查兩者的參考位置，true 我认为这里为false 1难道不等于1么
        System.out.println(int1 == int2); // 檢查兩者的參考位置，false
    }
}
