package com.mt.user.thread.demo;

/**
 * @author ：mateng
 * @version ：
 * @description ：
 * @program ：springcloud_helloworld
 * @date ：Created in 2022/3/25 15:05
 * @since ：
 */
public class ThreadAlternating {

    private static int count = 0;
    private  static final Object lock = new Object();

    public static void turning() {
        Thread even = new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {
                    //只处理偶数
                    if ((count & 1) == 0) {
                        System.out.println(Thread.currentThread().getName() + ": " + count++);
                    }
                }
            }
        }, "偶数");
        Thread odd = new Thread(() -> {
            while (count < 100) {
                synchronized (lock) {//只处理奇数
                    if ((count & 1) == 1) {
                        System.out.println(Thread.currentThread().getName() + ": " + count++);
                    }
                }
            }
        }, "奇数");
        even.start();
        odd.start();
    }

    public static void main(String[] args) {
        turning();
    }
}