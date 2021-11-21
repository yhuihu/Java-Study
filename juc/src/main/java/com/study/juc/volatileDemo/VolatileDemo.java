package com.study.juc.volatileDemo;

import java.util.concurrent.TimeUnit;

/**
 * @author yhhu
 * @date 2021/11/21
 * @description 关于volatile关键字的一些用处
 */
class MyData {
    int number = 0;

    public void setNumberTo1() {
        number = 1;
    }
}

public class VolatileDemo {

    public static void main(String[] args) {
        visibilityDemo();
    }

    private static void visibilityDemo() {

        System.out.println("可见性测试");

        MyData myData = new MyData();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.setNumberTo1();
        }, "Thread-A").start();

        // 如果在下面不通过sout输出内容，那么这里会陷入死锁。因为println中使用了synchronized方法，会进行将cache中的值写入到主存中。
        // 这里的myData.number获取的一直是主线程的cacheLine中的值，而Thread-A中赋值的内容一直在它自己所属的线程中，并没有影响到主线程
        // 也就是没有达到可见性的要求。
        while (myData.number == 0) {
//            System.out.println("当前数值为：" + myData.number);
        }

        System.out.println("main 线程获取的值为：" + myData.number);
    }

}
