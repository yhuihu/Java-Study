package com.study.juc.book.chapter_3;

/**
 * @author yhhu
 * @date 2021/5/29
 * @description 程序清单3-1 在没有同步的情况下共享变量（请不要这么做）
 */
public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }
}
