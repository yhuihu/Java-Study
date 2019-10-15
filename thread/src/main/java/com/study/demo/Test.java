package com.study.demo;

import java.util.concurrent.Callable;

/**
 * @author Tiger
 * @date 2019-09-25
 * @see com.study.demo
 **/
public class Test {
    public static void main(String[] args) throws Exception {
        Callable<String> work1=new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("测试方法1");
                return "方法1执行完毕";
            }
        };
        Callable<String> work2=new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("测试方法2");
                return "方法2执行完毕";
            }
        };
        MyFutureTask<String> task1=new MyFutureTask<>(work1);
        MyFutureTask<String> task2=new MyFutureTask<>(work2);
        new Thread(task1).start();
        new Thread(task2).start();
        String result1=task1.get();
        String result2=task2.get();
        System.out.println(result1);
        System.out.println(result2);

    }
}
